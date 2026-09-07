package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameterLocation;
import org.opendevstack.component_provisioner.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.opendevstack.component_provisioner.server.controllers.validators.MandatoryFieldType;
import org.opendevstack.component_provisioner.server.controllers.validators.MandatoryFieldsValidator;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.controllers.validators.UserPermissionsValidator;
import org.opendevstack.component_provisioner.server.controllers.validators.VisibleParametersValidator;
import org.opendevstack.component_provisioner.server.controllers.validators.WorkflowsValidator;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.*;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunchMother;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobMother;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiFacadeTest {

    @Mock
    private AwxService awxService;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @Mock
    private PlaceholderPostProcessor placeholderPostProcessor;

    @Mock
    private ReplaceParametersService replaceParametersService;

    @Mock
    private ProjectsInfoService projectsInfoService;

    @Mock
    private WorkflowsValidator workflowsValidator;

    @Mock
    private UserPermissionsValidator userPermissionsValidator;

    @Mock
    private MandatoryFieldsValidator mandatoryFieldsValidator;

    @Mock
    private VisibleParametersValidator visibleParametersValidator;

    @Spy
    @InjectMocks
    private ProvisionerActionsApiFacade facade;

    @BeforeEach
    void bypassAddMissingMandatoryParamsByDefault() {
        // We don't need to mock this method's logic everytime, only when testing it
        lenient()
                .doAnswer(invocation -> invocation.getArgument(0))
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());
        lenient()
                .when(componentCatalogService.getCatalogItem(any(), any(), any(), anyBoolean()))
                .thenReturn(new CatalogItem());
    }

    @Test
    void givenProvisionAction_whenRequestProvisionToAwx_thenMapsResponseCorrectly() {
        // given
        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        var action = ProvisionActionMother.of(params);

        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        ArgumentCaptor<ProvisionAction> actionCaptor = ArgumentCaptor.forClass(ProvisionAction.class);
        when(entitiesMapper.asAwxWorkflowJobLaunch(actionCaptor.capture())).thenReturn(launch);
        when(awxService.triggerWorkflowJob(action.getId(), launch))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        // when
        var result = facade.requestProvisionToAwx(action);

        // then
        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.awxResponseBody()).isEqualTo(response);

        var capturedAction = actionCaptor.getValue();
        assertThat(capturedAction.getParameters())
                .anyMatch(p -> "action_id".equals(p.getName()) && action.getId().equals(p.getValue()));
    }

    @Test
    void givenListParams_whenNotifyComponentCatalogProvisionStarts_thenSendsParametersAsListOfStrings() {
        // given
        var accessToken = "BEARER-TOKEN";

        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        params.add(ProvisionActionParameterMother.of("component_id", "CID"));
        params.add(ProvisionActionParameterMother.of("catalog_item_id", "CAT"));
        params.add(ProvisionActionParameterMother.of("component_url", "http://comp"));
        params.add(ProvisionActionParameterMother.of("access_token", accessToken));
        params.add(ProvisionActionParameterMother.of("list_param", List.of("a", "b")));
        params.add(ProvisionActionParameterMother.of("null_param", null));
        var action = ProvisionActionWrapperMother.of(params);

        // when
        facade.notifyComponentCatalogProvisionStarts(action);

        // then
        ArgumentCaptor<Map<String, List<String>>> captor = ArgumentCaptor.forClass(Map.class);
        verify(componentCatalogService).notifyComponentCatalogProvisionStarts(eq("PRJ"), eq("CID"), eq("CAT"), eq("http://comp"), eq(accessToken), captor.capture());
        var map = captor.getValue();
        assertThat(map.get("list_param")).containsExactly("a", "b");
        assertThat(map.get("null_param")).containsExactly("");
    }

    @Test
    void givenValidClusters_whenAddSystemParametersToAction_thenAddsRequiredSystemParameters() {
        // given
        var accessToken = "BEARER-TOKEN";

        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        params.add(ProvisionActionParameterMother.of("access_token", accessToken));
        var action = ProvisionActionWrapperMother.of(params);

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster-eu-west"));
        when(projectsInfoService.getProjectClusters(accessToken, "PRJ")).thenReturn(projectInfo);
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user@example.com");
        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when
        var resultingAction = facade.addSystemParametersToAction(action);

        // then
        var paramNames = resultingAction.getParametersMap().values().stream()
                .map(ProvisionActionParameter::getName)
                .toList();
        assertThat(paramNames).contains("cluster_location", "caller", "access_token", "notifications_group_id");

        var clusterLocation = resultingAction.getParametersMap().values().stream()
                .filter(p -> "cluster_location".equals(p.getName()))
                .map(p -> p.getValue().toString())
                .findFirst().orElseThrow();
        assertThat(clusterLocation).isEqualTo("cluster-eu-west");

        var caller = resultingAction.getParametersMap().values().stream()
                .filter(p -> "caller".equals(p.getName()))
                .map(p -> p.getValue().toString())
                .findFirst().orElseThrow();
        assertThat(caller).isEqualTo("user@example.com");

        var bearerToken = resultingAction.getParametersMap().values().stream()
                .filter(p -> "access_token".equals(p.getName()))
                .map(p -> p.getValue().toString())
                .findFirst().orElseThrow();
        assertThat(bearerToken).isEqualTo(accessToken);

        var notificationsGroupId = resultingAction.getParametersMap().values().stream()
                .filter(p -> "notifications_group_id".equals(p.getName()))
                .map(p -> p.getValue().toString())
                .findFirst().orElseThrow();
        assertThat(notificationsGroupId).isEqualTo("PRJ");
    }

    @Test
    void givenEmptyClusters_whenAddSystemParametersToAction_thenThrowsProjectConfigurationException() {
        // given
        var bearerToken = "BEARER";

        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        params.add(ProvisionActionParameterMother.of("access_token", bearerToken));
        var action = ProvisionActionWrapperMother.of(params);

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of());
        when(authenticationProvider.getAccessToken()).thenReturn(bearerToken);
        when(projectsInfoService.getProjectClusters(bearerToken, "PRJ")).thenReturn(projectInfo);

        // when / then
        assertThatThrownBy(() -> facade.addSystemParametersToAction(action))
                .isInstanceOf(ProjectConfigurationException.class)
                .hasMessageContaining("PRJ");
    }

    @Test
    void givenMultipleClusters_whenAddSystemParametersToAction_thenUsesFirstCluster() {
        // given
        var bearerToken = "bearer-token";
        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        params.add(ProvisionActionParameterMother.of("access_token", "ACCESS"));
        var action = ProvisionActionWrapperMother.of(params);

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster-primary", "cluster-secondary"));
        when(projectsInfoService.getProjectClusters(bearerToken, "PRJ")).thenReturn(projectInfo);
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user@example.com");
        when(authenticationProvider.getAccessToken()).thenReturn(bearerToken);

        // when
        var resultingAction = facade.addSystemParametersToAction(action);

        // then
        var clusterLocation = resultingAction.getParametersMap().values().stream()
                .filter(p -> "cluster_location".equals(p.getName()))
                .map(p -> p.getValue().toString())
                .findFirst().orElseThrow();
        assertThat(clusterLocation).isEqualTo("cluster-primary");
    }

    @Test
    void givenNoCatalogItemIdNorSlug_whenTriggerProvisionAction_thenThrowsBadRequestException() {
        // given
        var action = ProvisionActionMother.of(List.of(ProvisionActionParameterMother.of("project_key", "PRJ")));

        // when / then
        assertThatThrownBy(() -> facade.triggerProvisionAction(action))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("catalog_item_id")
                .hasMessageContaining("catalog_item_slug");
    }

    @Test
    void givenBothCatalogItemIdAndSlug_whenTriggerProvisionAction_thenThrowsBadRequestException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "cat-id"),
                ProvisionActionParameterMother.of("catalog_item_slug", "my-slug")
        ));

        // when / then
        assertThatThrownBy(() -> facade.triggerProvisionAction(action))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("catalog_item_id")
                .hasMessageContaining("catalog_item_slug");
    }

    @Test
    void givenOnlyCatalogItemId_whenTriggerProvisionAction_thenDoesNotCallGetCatalogItemBySlug() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "cat-id")
        ));
        var awxWorkflowJobLaunch = AwxWorkflowJobLaunchMother.of();
        var awxWorkflowJob = AwxWorkflowJobMother.of();
        var provisionActionResponse = ProvisionActionResponseMother.of();
        setupSystemParameterMocks();
        when(placeholderPostProcessor.process(any())).thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any())).thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any())).thenReturn(awxWorkflowJobLaunch);
        when(entitiesMapper.asProvisionActionResponse(awxWorkflowJob)).thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.OK, Optional.of(awxWorkflowJob)));

        // when
        facade.triggerProvisionAction(action);

        // then
        verify(componentCatalogService, never()).getCatalogItemBySlug(any(), any());
    }

    @Test
    void givenOnlyCatalogItemSlug_whenTriggerProvisionAction_thenResolvesCatalogItemIdAndRenamesParameter() {
        // given
        var catalogItemSlug = "my-catalog-slug";
        var resolvedCatalogItemId = "resolved-catalog-id";
        var accessToken = "token";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_slug", catalogItemSlug)
        ));
        setupSystemParameterMocks();
        var awxWorkflowJobLaunch = AwxWorkflowJobLaunchMother.of();
        var awxWorkflowJob = AwxWorkflowJobMother.of();
        var provisionActionResponse = ProvisionActionResponseMother.of();

        var catalogItem = new CatalogItem();
        catalogItem.setId(resolvedCatalogItemId);
        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenReturn(catalogItem);
        when(placeholderPostProcessor.process(any())).thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any())).thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any())).thenReturn(awxWorkflowJobLaunch);
        when(entitiesMapper.asProvisionActionResponse(awxWorkflowJob)).thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.OK, Optional.of(awxWorkflowJob)));

        // when
        facade.triggerProvisionAction(action);

        // then
        verify(componentCatalogService).getCatalogItemBySlug(accessToken, catalogItemSlug);
        ArgumentCaptor<ProvisionActionWrapper> wrapperCaptor = ArgumentCaptor.forClass(ProvisionActionWrapper.class);
        verify(replaceParametersService).replaceProvisioningParametersFromOdsApi(wrapperCaptor.capture());
        var capturedWrapper = wrapperCaptor.getValue();
        assertThat(capturedWrapper.getCatalogItemId()).isEqualTo(resolvedCatalogItemId);
        assertThat(capturedWrapper.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenOnlyCatalogItemSlug_whenTriggerProvisionAction_thenAddsMandatoryCatalogItemParamsUsingResolvedId() {
        // given
        var catalogItemSlug = "my-catalog-slug";
        var resolvedCatalogItemId = "resolved-catalog-id";
        var accessToken = "token";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_slug", catalogItemSlug)
        ));
        setupSystemParameterMocks();
        var awxWorkflowJobLaunch = AwxWorkflowJobLaunchMother.of();
        var awxWorkflowJob = AwxWorkflowJobMother.of();
        var provisionActionResponse = ProvisionActionResponseMother.of();

        var catalogItem = new CatalogItem();
        catalogItem.setId(resolvedCatalogItemId);
        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenReturn(catalogItem);
        when(placeholderPostProcessor.process(any())).thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any())).thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any())).thenReturn(awxWorkflowJobLaunch);
        when(entitiesMapper.asProvisionActionResponse(awxWorkflowJob)).thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.OK, Optional.of(awxWorkflowJob)));

        // when
        facade.triggerProvisionAction(action);

        // then
        ArgumentCaptor<ProvisionActionWrapper> wrapperCaptor = ArgumentCaptor.forClass(ProvisionActionWrapper.class);
        verify(facade).addMandatoryCatalogItemParamsIfMissing(wrapperCaptor.capture(), any());
        var capturedWrapper = wrapperCaptor.getValue();
        assertThat(capturedWrapper.getCatalogItemId()).isEqualTo(resolvedCatalogItemId);
        assertThat(capturedWrapper.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenCatalogItemSlugNotFound_whenTriggerProvisionAction_thenThrowsSlugNotFoundException() {
        // given
        var catalogItemSlug = "unknown-slug";
        var accessToken = "token";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_slug", catalogItemSlug)
        ));
        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug))
                .thenThrow(new RestClientException("Not found"));

        // when / then
        assertThatThrownBy(() -> facade.triggerProvisionAction(action))
                .isInstanceOf(SlugNotFoundException.class)
                .hasMessageContaining(catalogItemSlug);
    }

    @Test
    void givenProvisionActionWithCatalogItem_whenTriggerProvisionAction_thenNotifiesCatalogAfterReplaceParameters() {
        // given
        var provisionAction = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "cat-id")
        ));
        var awxWorkflowJobLaunch = AwxWorkflowJobLaunchMother.of();
        var awxWorkflowJob = AwxWorkflowJobMother.of();
        var provisionActionResponse = ProvisionActionResponseMother.of();

        setupSystemParameterMocks();
        when(placeholderPostProcessor.process(any())).thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any())).thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any())).thenReturn(awxWorkflowJobLaunch);
        when(entitiesMapper.asProvisionActionResponse(awxWorkflowJob)).thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(provisionAction.getId(), awxWorkflowJobLaunch)).thenReturn(Pair.of(HttpStatus.OK, Optional.of(awxWorkflowJob)));

        // when
        facade.triggerProvisionAction(provisionAction);

        // then
        var order = inOrder(replaceParametersService, componentCatalogService);
        order.verify(replaceParametersService).replaceProvisioningParametersFromOdsApi(any());
        order.verify(componentCatalogService).notifyComponentCatalogProvisionStarts(any(), any(), any(), any(), any(), any());
    }

    private void setupSystemParameterMocks() {
        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster-eu-west"));
        when(authenticationProvider.getAccessToken()).thenReturn("token");
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user@example.com");
        when(projectsInfoService.getProjectClusters("token", "PRJ")).thenReturn(projectInfo);
    }

    @Test
    void givenNullParameters_whenRequestProvisionToAwx_thenAddsActionIdParameter() {
        // given
        var action = ProvisionActionMother.of(null);

        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        ArgumentCaptor<ProvisionAction> actionCaptor = ArgumentCaptor.forClass(ProvisionAction.class);
        when(entitiesMapper.asAwxWorkflowJobLaunch(actionCaptor.capture())).thenReturn(launch);
        when(awxService.triggerWorkflowJob(action.getId(), launch))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        // when
        var result = facade.requestProvisionToAwx(action);

        // then
        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.awxResponseBody()).isEqualTo(response);

        var capturedAction = actionCaptor.getValue();
        assertThat(capturedAction.getParameters())
                .hasSize(1)
                .anyMatch(p -> "action_id".equals(p.getName()) && action.getId().equals(p.getValue()));
    }

    @Test
    void givenMissingRequiredParams_whenAddMandatoryParamsIfMissing_thenAddsMissingParameters() {
        // given
        var actionId = "action-id";

        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("catalog_item_id", "CAT-1"));
        params.add(ProvisionActionParameterMother.of("project_key", "MY-PROJECT"));
        var action = ProvisionActionWrapperMother.of(params);

        var requiredParam = new CatalogItemUserActionParameter()
                .name("required_param")
                .type("string")
                .required(true)
                .defaultValue("default");

        var userAction = new CatalogItemUserAction()
                .id(actionId)
                .parameters(List.of(requiredParam));

        var catalogItem = new CatalogItem()
                .userActions(List.of(userAction));

        doCallRealMethod()
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());

        // when
        var modifiedAction = facade.addMandatoryCatalogItemParamsIfMissing(action, catalogItem);

        // then
        var addedParam = modifiedAction.getParametersMap().values().stream()
                .filter(p -> "required_param".equals(p.getName()))
                .findFirst()
                .orElseThrow();

        assertThat(addedParam.getValue()).isEqualTo("default");
        assertThat(addedParam.getType()).isEqualTo("string");
    }

    @Test
    void givenRequiredParamAlreadyPresent_whenAddMandatoryParamsIfMissing_thenDoesNothing() {
        // given
        var actionId = "ACTION_ID";

        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("catalog_item_id", "CAT-1"));
        params.add(ProvisionActionParameterMother.of("project_key", "MY-PROJECT"));
        params.add(ProvisionActionParameterMother.of("required_param", "custom"));
        var action = ProvisionActionWrapperMother.of(params);

        var requiredParam = new CatalogItemUserActionParameter()
                .name("required_param")
                .type("string")
                .required(true)
                .defaultValue("default");

        var userAction = new CatalogItemUserAction()
                .id(actionId)
                .parameters(List.of(requiredParam));

        var catalogItem = new CatalogItem()
                .userActions(List.of(userAction));

        doCallRealMethod()
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());

        // when
        var modifiedAction = facade.addMandatoryCatalogItemParamsIfMissing(action, catalogItem);

        // then
        var values = modifiedAction.getParametersMap().values().stream()
                .filter(p -> "required_param".equals(p.getName()))
                .map(p -> p.getValue().toString())
                .toList();

        assertThat(values).containsExactly("custom");
    }

    @Test
    void givenStringTypeWithDefault_whenAddMandatoryParamsIfMissing_thenUsesDefaultValue() {
        var action = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "CAT-1"),
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        var requiredParam = CatalogItemUserActionParameter.builder()
                .name("param_string")
                .type("string")
                .required(true)
                .defaultValue("default-value")
                .build();

        var userAction = CatalogItemUserAction.builder()
                .id(action.getProvisionActionId())
                .parameters(List.of(requiredParam))
                .build();

        var catalogItem = CatalogItem.builder()
                .userActions(List.of(userAction))
                .build();

        doCallRealMethod()
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());

        var result = facade.addMandatoryCatalogItemParamsIfMissing(action, catalogItem);

        var addedParam = result.getParametersMap().get("param_string");
        assertThat(addedParam.getValue()).isEqualTo("default-value");
    }

    @Test
    void givenStringTypeWithLocationNoDefault_whenAddMandatoryParamsIfMissing_thenUsesLocationValue() {
        var action = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "CAT-1"),
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("cluster_location", "eu-west")
        ));

        var location = CatalogItemUserActionParameterLocation.builder()
                .location("eu-west")
                .value("location-value")
                .build();

        var requiredParam = CatalogItemUserActionParameter.builder()
                .name("param_string")
                .type("string")
                .required(true)
                .locations(List.of(location))
                .build();

        var userAction = CatalogItemUserAction.builder()
                .id(action.getProvisionActionId())
                .parameters(List.of(requiredParam))
                .build();

        var catalogItem = CatalogItem.builder()
                .userActions(List.of(userAction))
                .build();

        doCallRealMethod()
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());

        var result = facade.addMandatoryCatalogItemParamsIfMissing(action, catalogItem);

        var addedParam = result.getParametersMap().get("param_string");
        assertThat(addedParam.getValue()).isEqualTo("location-value");
    }

    @Test
    void givenStringTypeWithoutDefaults_whenAddMandatoryParamsIfMissing_thenLeavesValueNull() {
        var action = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "CAT-1"),
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        var requiredParam = CatalogItemUserActionParameter.builder()
                .name("param_string")
                .type("string")
                .required(true)
                .build();

        var userAction = CatalogItemUserAction.builder()
                .id(action.getProvisionActionId())
                .parameters(List.of(requiredParam))
                .build();

        var catalogItem = CatalogItem.builder()
                .userActions(List.of(userAction))
                .build();

        doCallRealMethod()
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());

        var result = facade.addMandatoryCatalogItemParamsIfMissing(action, catalogItem);

        var addedParam = result.getParametersMap().get("param_string");
        assertThat(addedParam.getValue()).isNull();
    }

    @Test
    void givenMultipleListType_whenAddMandatoryParamsIfMissing_thenUsesDefaultValues() {
        var action = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "CAT-1"),
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        var requiredParam = CatalogItemUserActionParameter.builder()
                .name("param_multi")
                .type(MandatoryFieldType.MULTIPLELIST.getValue())
                .required(true)
                .defaultValues(List.of("v1", "v2"))
                .build();

        var userAction = CatalogItemUserAction.builder()
                .id(action.getProvisionActionId())
                .parameters(List.of(requiredParam))
                .build();

        var catalogItem = CatalogItem.builder()
                .userActions(List.of(userAction))
                .build();

        doCallRealMethod()
                .when(facade)
                .addMandatoryCatalogItemParamsIfMissing(any(), any());

        var result = facade.addMandatoryCatalogItemParamsIfMissing(action, catalogItem);

        var addedParam = result.getParametersMap().get("param_multi");
        assertThat(addedParam.getValue()).isEqualTo(List.of("v1", "v2"));
    }

    @Test
    void givenWorkflowNameProvided_whenAddSystemParametersToAction_thenDoesNotAddProvisionWorkflowParams() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("workflow_name", "custom-wf")
        ));

        setupSystemParameterMocks();

        ReflectionTestUtils.setField(facade, "provisionWrapperWorkflowId", "WRAPPER_WF");

        // when
        var result = facade.addSystemParametersToAction(wrapper);

        // then
        assertThat(result.getParameterValue("provision_workflow_name")).isNull();
        assertThat(result.getParameterValue("workflow")).isNull();
    }

    @Test
    void givenWorkflowIdProvided_whenAddSystemParametersToAction_thenDoesNotAddProvisionWorkflowId() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("workflow", "custom-id")
        ));

        setupSystemParameterMocks();
        ReflectionTestUtils.setField(facade, "provisionWrapperWorkflowId", "WRAPPER_WF");

        // when
        var result = facade.addSystemParametersToAction(wrapper);

        // then
        assertThat(result.getParameterValue("provision_workflow_id")).isNull();
    }

    @Test
    void givenTimeoutProvided_whenAddSystemParametersToAction_thenDoesNotAddProvisionWorkflowTimeout() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("workflow_timeout_seconds", "120")
        ));

        setupSystemParameterMocks();
        ReflectionTestUtils.setField(facade, "provisionWrapperWorkflowId", "WRAPPER_WF");

        // when
        var result = facade.addSystemParametersToAction(wrapper);

        // then
        assertThat(result.getParameterValue("provision_workflow_timeout_seconds")).isNull();
    }

    @Test
    void givenWorkflowParams_whenAddSystemParametersToAction_thenDoesNotRemoveWorkflowParameters() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("workflow", "old"),
                ProvisionActionParameterMother.of("workflow_name", "old-name"),
                ProvisionActionParameterMother.of("workflow_timeout_seconds", "120")
        ));

        setupSystemParameterMocks();
        ReflectionTestUtils.setField(facade, "provisionWrapperWorkflowId", "WRAPPER_WF");

        // when
        var result = facade.addSystemParametersToAction(wrapper);

        // then
        assertThat(result.getParameterValue("workflow")).isEqualTo("old");
        assertThat(result.getWorkflowName()).isEqualTo("old-name");
        assertThat(result.getParameterValue("workflow_timeout_seconds")).isEqualTo("120");
    }

    @Test
    void givenDispatchedParams_whenAddSystemParametersToAction_thenDoesNotAddDispatchedWorkflowParams() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("custom_param", "value"),
                ProvisionActionParameterMother.of("workflow", "wf-id"),
                ProvisionActionParameterMother.of("workflow_name", "wf-name"),
                ProvisionActionParameterMother.of("workflow_timeout_seconds", "120")
        ));

        setupSystemParameterMocks();
        ReflectionTestUtils.setField(facade, "provisionWrapperWorkflowId", "WRAPPER_WF");

        // when
        var result = facade.addSystemParametersToAction(wrapper);

        // then
        assertThat(result.getParameterValue("dispatched_workflow_params")).isNull();
    }

    @Test
    void givenCustomWorkflowName_whenTriggerProvisionAction_thenAddsProvisionWorkflowParameters() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("workflow_name", "custom-wf")
        ));

        setupSystemParameterMocks();

        var awxWorkflowJobLaunch = AwxWorkflowJobLaunchMother.of();
        var awxWorkflowJob = AwxWorkflowJobMother.of();

        var provisionActionResponse = ProvisionActionResponseMother.of();
        provisionActionResponse.setId(123);

        when(placeholderPostProcessor.process(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        ArgumentCaptor<ProvisionActionWrapper> captor = ArgumentCaptor.forClass(ProvisionActionWrapper.class);

        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(captor.capture()))
                .thenAnswer(inv -> inv.getArgument(0));

        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any()))
                .thenReturn(awxWorkflowJobLaunch);

        when(entitiesMapper.asProvisionActionResponse(awxWorkflowJob))
                .thenReturn(provisionActionResponse);

        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(awxWorkflowJob)));

        // when
        facade.triggerProvisionAction(action);

        // then
        var wrapper = captor.getValue();

        var provisionWorkflowName = wrapper.getParametersMap().values().stream()
                .filter(p -> "provision_workflow_name".equals(p.getName()))
                .findFirst()
                .orElseThrow();

        assertThat(provisionWorkflowName.getValue()).isEqualTo("custom-wf");

        assertThat(wrapper.getParametersMap()).containsKey("workflow");
    }

    @Test
    void givenValidAction_whenTriggerProvisionAction_thenCallsValidateWorkflowPresence() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT")
        ));

        setupSystemParameterMocks();

        var provisionActionResponse = ProvisionActionResponseMother.of();
        provisionActionResponse.setId(123);

        when(placeholderPostProcessor.process(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any()))
                .thenReturn(AwxWorkflowJobLaunchMother.of());
        when(entitiesMapper.asProvisionActionResponse(any()))
                .thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(AwxWorkflowJobMother.of())));

        // when
        facade.triggerProvisionAction(action);

        // then
        verify(workflowsValidator).validate(any());
    }

    @Test
    void givenValidAction_whenTriggerProvisionAction_thenCallsUserPermissionsValidation() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT")
        ));

        setupSystemParameterMocks();

        var provisionActionResponse = ProvisionActionResponseMother.of();
        provisionActionResponse.setId(123);

        when(placeholderPostProcessor.process(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any()))
                .thenReturn(AwxWorkflowJobLaunchMother.of());
        when(entitiesMapper.asProvisionActionResponse(any()))
                .thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(AwxWorkflowJobMother.of())));

        // when
        facade.triggerProvisionAction(action);

        // then
        verify(userPermissionsValidator).validate(any(CatalogItem.class));
    }

    @Test
    void givenValidAction_whenTriggerProvisionAction_thenCallsVisibleParametersValidationBeforeSystemParameterEnrichment() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT")
        ));
        var catalogItem = CatalogItem.builder().title("My Catalog Item").build();

        setupSystemParameterMocks();

        var provisionActionResponse = ProvisionActionResponseMother.of();
        provisionActionResponse.setId(123);

        when(componentCatalogService.getCatalogItem(any(), any(), any(), anyBoolean()))
                .thenReturn(catalogItem);
        when(placeholderPostProcessor.process(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any()))
                .thenReturn(AwxWorkflowJobLaunchMother.of());
        when(entitiesMapper.asProvisionActionResponse(any()))
                .thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(AwxWorkflowJobMother.of())));

        var provisionActionCaptor = ArgumentCaptor.forClass(ProvisionAction.class);

        // when
        facade.triggerProvisionAction(action);

        // then
        verify(visibleParametersValidator)
                .validate(provisionActionCaptor.capture(), same(catalogItem));

        var validatedAction = provisionActionCaptor.getValue();
        assertThat(validatedAction.getParameters())
                .extracting(ProvisionActionParameter::getName)
                .containsExactlyInAnyOrder("project_key", "catalog_item_id");

        var order = inOrder(userPermissionsValidator, visibleParametersValidator, workflowsValidator);
        order.verify(userPermissionsValidator).validate(catalogItem);
        order.verify(visibleParametersValidator)
                .validate(any(ProvisionAction.class), same(catalogItem));
        order.verify(workflowsValidator).validate(any());
    }

    @Test
    void givenValidAction_whenTriggerProvisionAction_thenAppliesWorkflowWrapperBeforePlaceholderProcessing() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT")
        ));

        setupSystemParameterMocks();

        var provisionActionResponse = ProvisionActionResponseMother.of();
        provisionActionResponse.setId(123);

        when(placeholderPostProcessor.process(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(any()))
                .thenAnswer(inv -> inv.getArgument(0));
        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any()))
                .thenReturn(AwxWorkflowJobLaunchMother.of());
        when(entitiesMapper.asProvisionActionResponse(any()))
                .thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(AwxWorkflowJobMother.of())));

        // when
        facade.triggerProvisionAction(action);

        // then
        var order = inOrder(provisionerActionsApiValidator, placeholderPostProcessor, workflowsValidator);

        order.verify(workflowsValidator).validate(any());
        order.verify(placeholderPostProcessor).process(any());
    }

    @Test
    void givenHiddenMandatoryWorkflowName_whenTriggerProvisionAction_thenValidatesBeforeWorkflowWrapperTransformation() {
        // given
        var workflowName = "hidden-required-workflow-name";
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "CAT"),
                ProvisionActionParameterMother.of("workflow_name", workflowName)
        ));

        setupSystemParameterMocks();
        ReflectionTestUtils.setField(facade, "provisionWrapperWorkflowId", "WRAPPER_WF");

        var mandatoryWorkflowNameParam = CatalogItemUserActionParameter.builder()
                .name("workflow_name")
                .type("string")
                .required(true)
                .visible(false)
                .build();
        var provisionUserAction = CatalogItemUserAction.builder()
                .id(action.getId())
                .parameters(List.of(mandatoryWorkflowNameParam))
                .build();
        var catalogItem = CatalogItem.builder()
                .userActions(List.of(provisionUserAction))
                .build();

        var awxWorkflowJobLaunch = AwxWorkflowJobLaunchMother.of();
        var awxWorkflowJob = AwxWorkflowJobMother.of();
        var provisionActionResponse = ProvisionActionResponseMother.of();
        provisionActionResponse.setId(123);

        when(componentCatalogService.getCatalogItem("token", "CAT", "PRJ", true)).thenReturn(catalogItem);
        when(placeholderPostProcessor.process(any())).thenAnswer(inv -> inv.getArgument(0));

        ArgumentCaptor<ProvisionActionWrapper> wrapperCaptor = ArgumentCaptor.forClass(ProvisionActionWrapper.class);
        when(replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapperCaptor.capture()))
                .thenAnswer(inv -> inv.getArgument(0));

        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any()))
                .thenReturn(awxWorkflowJobLaunch);
        when(entitiesMapper.asProvisionActionResponse(awxWorkflowJob))
                .thenReturn(provisionActionResponse);
        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(awxWorkflowJob)));

        doAnswer(inv -> {
            var provisionActionArg = inv.getArgument(0, ProvisionAction.class);
            boolean hasWorkflowName = provisionActionArg.getParameters().stream()
                    .anyMatch(param -> "workflow_name".equals(param.getName()) && workflowName.equals(param.getValue()));
            if (!hasWorkflowName) {
                throw new IllegalStateException("workflow_name should be present during mandatory fields validation");
            }
            return null;
        }).when(mandatoryFieldsValidator).validate(any(), eq(catalogItem));

        // when
        facade.triggerProvisionAction(action);

        // then
        ArgumentCaptor<ProvisionAction> mandatoryValidationCaptor = ArgumentCaptor.forClass(ProvisionAction.class);
        verify(mandatoryFieldsValidator).validate(mandatoryValidationCaptor.capture(), eq(catalogItem));
        assertThat(mandatoryValidationCaptor.getValue().getParameters())
                .anyMatch(param -> "workflow_name".equals(param.getName()) && workflowName.equals(param.getValue()));

        var wrapperAfterWorkflowWrapping = wrapperCaptor.getValue();
        assertThat(wrapperAfterWorkflowWrapping.getParameterValue("workflow_name")).isNull();
        assertThat(wrapperAfterWorkflowWrapping.getParameterValue("provision_workflow_name")).isEqualTo(workflowName);
    }

}
