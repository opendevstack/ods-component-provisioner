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
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.*;
import org.opendevstack.component_provisioner.server.model.AwxWorkflowJobLaunchMother;
import org.opendevstack.component_provisioner.server.model.AwxWorkflowJobMother;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponseMother;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.PlaceholderPostProcessor;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.ReplaceParametersService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private MandatoryFieldsValidator mandatoryFieldsValidator;

    @Mock
    private ProjectsInfoService projectsInfoService;

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
                .when(componentCatalogService.getCatalogItem(any(), any(), any()))
                .thenReturn(new CatalogItem());
    }

    @Test
    void requestProvisionToAwx_mapsResponseCorrectly() {
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
        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());

        var capturedAction = actionCaptor.getValue();
        assertThat(capturedAction.getParameters())
                .anyMatch(p -> "action_id".equals(p.getName()) && action.getId().equals(p.getValue()));
    }

    @Test
    void notifyComponentCatalogProvisionStarts_sendsParametersAsListOfStrings() {
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
    void addSystemParametersToAction_addsClusterLocationCallerAndAccessToken() {
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
        assertThat(paramNames).contains("cluster_location", "caller", "access_token");

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
    }

    @Test
    void addSystemParametersToAction_throwsIllegalStateException_whenClustersIsEmpty() {
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
    void addSystemParametersToAction_usesFirstCluster_whenMultipleClustersAreReturned() {
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
    void triggerProvisionAction_givenNoCatalogItemIdNorSlug_thenThrowsBadRequestException() {
        // given
        var action = ProvisionActionMother.of(List.of(ProvisionActionParameterMother.of("project_key", "PRJ")));

        // when / then
        assertThatThrownBy(() -> facade.triggerProvisionAction(action))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("catalog_item_id")
                .hasMessageContaining("catalog_item_slug");
    }

    @Test
    void triggerProvisionAction_givenBothCatalogItemIdAndSlug_thenThrowsBadRequestException() {
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
    void triggerProvisionAction_givenOnlyCatalogItemId_thenDoesNotCallGetCatalogItemBySlug() {
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
    void triggerProvisionAction_givenOnlyCatalogItemSlug_thenResolvesCatalogItemIdAndRenamesParameter() {
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
    void triggerProvisionAction_givenOnlyCatalogItemSlug_thenAddMandatoryCatalogItemParamsIfMissingReceivesResolvedId() {
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
    void triggerProvisionAction_givenCatalogItemSlugNotFound_thenThrowsSlugNotFoundException() {
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
    void triggerProvisionAction_notifiesCatalogAfterReplaceParameters() {
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
    void requestProvisionToAwx_addsActionIdParameter_whenParametersIsNull() {
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
        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());

        var capturedAction = actionCaptor.getValue();
        assertThat(capturedAction.getParameters())
                .hasSize(1)
                .anyMatch(p -> "action_id".equals(p.getName()) && action.getId().equals(p.getValue()));
    }

    @Test
    void addMandatoryParamsIfMissing_addsMissingRequiredParameters() {
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
    void addMandatoryParamsIfMissing_doesNothingWhenRequiredParamAlreadyPresent() {
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
    void addMandatoryParamsIfMissing_stringType_usesDefaultValue() {
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
    void addMandatoryParamsIfMissing_stringType_usesLocationValue_whenNoDefault() {
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
    void addMandatoryParamsIfMissing_stringType_withoutDefaults_leavesValueNull() {
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
    void addMandatoryParamsIfMissing_multipleListType_usesDefaultValues() {
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
    void addSystemParametersToAction_whenWorkflowNameProvided_thenUsesProvisionWorkflowName() {
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
        assertThat(result.getParameterValue("provision_workflow_name")).isEqualTo("custom-wf");
        assertThat(result.getParameterValue("provision_workflow_id")).isNull();
    }

    @Test
    void addSystemParametersToAction_whenWorkflowIdProvided_thenUsesProvidedWorkflowId() {
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
        assertThat(result.getParameterValue("provision_workflow_id")).isEqualTo("custom-id");
    }

    @Test
    void addSystemParametersToAction_whenTimeoutProvided_thenAddsProvisionWorkflowTimeout() {
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
        assertThat(result.getParameterValue("provision_workflow_timeout_seconds")).isEqualTo("120");
    }

    @Test
    void addSystemParametersToAction_removesWorkflowNameAndWorkflowTimeoutParameters() {
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
        assertThat(result.getParameterValue("workflow_timeout_seconds")).isNull();
        assertThat(result.getWorkflowName()).isNull();
    }
}
