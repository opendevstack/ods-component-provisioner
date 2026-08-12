package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.*;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProvisionResultsApiFacadeTest {

    @Mock
    private AwxService awxService;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private ProvisionService provisionService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ApplicationAuthenticationProvider applicationAuthenticationProvider;

    @Mock
    private ProjectsInfoService projectsInfoService;

    @InjectMocks
    private ProvisionResultsApiFacade facade;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String workflowJobId = "WORKFLOW_123";
        ReflectionTestUtils.setField(facade, "createIncidentWorkflowId", workflowJobId);
        ReflectionTestUtils.setField(facade, "deletionWrapperWorkflowId", workflowJobId);
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenTriggerAwxIncidentWorkflowIsCalled_thenMapsResponseCorrectly() {
        // given
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        when(entitiesMapper.asAwxWorkflowJobLaunch(any(CreateIncidentAction.class))).thenReturn(launch);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(any())).thenReturn(response);

        // when
        var result = facade.triggerAwxIncidentWorkflow("PRJ", "CID", action);

        // then
        assertNotNull(result, "Result should not be null");
        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());
    }

    @Test
    void givenNullAction_whenGetParameterStringIsCalled_thenReturnsEmptyString() {
        // given
        CreateIncidentAction action = null;

        // when
        var result = facade.getParameterString(action, "any");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenNullParameters_whenGetParameterStringIsCalled_thenReturnsEmptyString() {
        // given
        var action = CreateIncidentAction.builder().parameters(null).build();

        // when
        var result = facade.getParameterString(action, "any");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenNullParameterValue_whenGetParameterStringIsCalled_thenReturnsEmptyString() {
        // given
        var action = CreateIncidentAction.builder().parameters(List.of(
                CreateIncidentParameter.builder().name("p1").value(null).build()
        )).build();

        // when
        var result = facade.getParameterString(action, "p1");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenIsInDeletingStateIsCalled_thenReturnsTrueWhenMatchingComponentFound() {
        // given
        var pc = ProjectComponentExtendedInfo.builder()
                .componentId("componentId")
                .status(ProjectComponentStatus.DELETING.name())
                .build();

        // when
        var result = facade.isInDeletingState(pc);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenAnInvalidStatus_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "PRJ";
        var status = "invalid";

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate(projectKey, status));
        assertThat(ex.getMessage()).contains("Status is not valid");
    }

    @Test
    void givenAMissingProjectKeyOrStatus_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = (String) null;
        var status = "CREATED";

        // when / then
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(projectKey, status));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null));
    }

    @Test
    void givenAMissingMainParams_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var action = CreateIncidentActionMother.of();

        // when / then
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(null, "CID", "", "", action));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null, "", "", action));
    }

    @Test
    void givenAMissingParameter_whenGetParameterStringIsCalled_thenReturnsEmptyString() {
        // given
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        // when
        var result = facade.getParameterString(action, "missing");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenIsInDeletingStateIsCalled_thenReturnsFalseWhenComponentNotFound() {
        // when
        var result = facade.isInDeletingState(null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenIsInDeletingStateIsCalled_thenReturnsFalseWhenComponentNotDeleting() {
        // given
        var pc = ProjectComponentExtendedInfo.builder()
                .componentId("componentId")
                .status(ProjectComponentStatus.CREATED.name())
                .build();

        // when
        var result = facade.isInDeletingState(pc);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenAValidStatus_whenValidateIsCalled_thenDoesNotThrow() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED.name();

        // when / then
        assertDoesNotThrow(() -> facade.validate(projectKey, status));
    }

    @Test
    void givenAMissingExtraParams_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CID", "", "", action));
        assertThat(ex.getMessage()).contains("is_deployed, change_number and reason");
    }

    @Test
    void givenAnEmptyAwxResponse_whenTriggerAwxIncidentWorkflowIsCalled_thenReturnsNullBody() {
        // given
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();

        when(entitiesMapper.asAwxWorkflowJobLaunch(any(CreateIncidentAction.class))).thenReturn(launch);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.ACCEPTED, Optional.empty()));

        // when
        var result = facade.triggerAwxIncidentWorkflow("PRJ", "CID", action);

        // then
        assertNotNull(result, "Result should not be null");
        assertEquals(HttpStatus.ACCEPTED, result.httpStatusCode());
        assertThat(result.awxResponseBody()).isNull();
    }

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdateIsCalled_thenDelegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemId = "CAT";
        var catalogItemSlug = "SLUG";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        when(applicationAuthenticationProvider.getAccessToken()).thenReturn(accessToken);

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);

        // when
        facade.notifyProvisioningStatusUpdate(projectKey, status, request);

        // then
        verifyNoInteractions(authenticationProvider);
        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(catalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenACatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenResolvesSlugToId() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "SLUG";
        var resolvedCatalogItemId = "RESOLVED_ID";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        when(applicationAuthenticationProvider.getAccessToken()).thenReturn(accessToken);

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        var catalogItem = new CatalogItem();
        catalogItem.setId(resolvedCatalogItemId);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();

        when(componentCatalogService.getCatalogItemBySlug(any(), any())).thenReturn(catalogItem);
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);

        // when
        facade.notifyProvisioningStatusUpdate(projectKey, status, request);

        // then
        verifyNoInteractions(authenticationProvider);
        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(resolvedCatalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenAnInvalidCatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsSlugNotFoundException() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "INVALID_SLUG";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        when(componentCatalogService.getCatalogItemBySlug(any(), any())).thenThrow(new RestClientException("Not found"));

        // when / then
        assertThrows(SlugNotFoundException.class, () -> facade.notifyProvisioningStatusUpdate(projectKey, status, request));
    }

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenDelegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemId = "CAT";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.componentUrl(componentUrl);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);
        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when
        facade.notifyProvisioningStatusUpdatePartially(projectKey, status, request);

        // then
        verify(provisionService).notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(catalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenACatalogItemSlug_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenResolvesSlugToId() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "SLUG";
        var resolvedCatalogItemId = "RESOLVED_ID";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        var catalogItem = new CatalogItem();
        catalogItem.setId(resolvedCatalogItemId);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();

        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenReturn(catalogItem);
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);
        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when
        facade.notifyProvisioningStatusUpdatePartially(projectKey, status, request);

        // then
        verify(provisionService).notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(resolvedCatalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenAnInvalidCatalogItemSlug_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenThrowsSlugNotFoundException() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "INVALID_SLUG";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenThrow(new RestClientException("Not found"));

        // when / then
        assertThrows(SlugNotFoundException.class, () -> facade.notifyProvisioningStatusUpdatePartially(projectKey, status, request));
    }

    @Test
    void givenBothCatalogItemIdAndSlug_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CREATED", "ID", "SLUG"));
        assertThat(ex.getMessage()).contains("Both catalogItemId and catalogItemSlug cannot be defined at the same time");
    }

    @Test
    void givenOnlyCatalogItemId_whenValidateIsCalled_thenDoesNotThrow() {
        // when / then
        assertDoesNotThrow(() -> facade.validate("PRJ", "CREATED", "ID", ""));
    }

    @Test
    void givenOnlyCatalogItemSlug_whenValidateIsCalled_thenDoesNotThrow() {
        // when / then
        assertDoesNotThrow(() -> facade.validate("PRJ", "CREATED", null, "SLUG"));
    }

    @Test
    void givenNeitherCatalogItemIdNorCatalogItemSlug_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> facade.validate("PRJ", "CREATED", null, "");

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);
        assertThat(exception.getMessage()).isEqualTo("Either catalogItemId or catalogItemSlug must be defined.");
    }

    @Test
    void givenAProjectKeyAndComponentIdAndAction_whenValidateIsCalled_thenDoesNotThrowIfValid() {
        // given
        var action = CreateIncidentActionMother.of();

        // when / then
        assertDoesNotThrow(() -> facade.validate("PRJ", "CID", "", "", action));
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenBuildAwxWorkflowJobLaunchIsCalled_thenAddsRequiredParameters() {
        // given
        var action = CreateIncidentActionMother.of();
        action.setParameters(new ArrayList<>());
        var projectKey = "PRJ";
        var componentId = "CID";
        var launch = new AwxWorkflowJobLaunch();
        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);

        // when
        var result = ReflectionTestUtils.invokeMethod(facade, "buildAwxWorkflowJobLaunch", projectKey, componentId, action);

        // then
        assertThat(result).isEqualTo(launch);
        assertThat(facade.getParameterString(action, "project_key")).isEqualTo(projectKey);
        assertThat(facade.getParameterString(action, "component_id")).isEqualTo(componentId);
        assertThat(facade.getParameterString(action, "workflow")).isEqualTo("WORKFLOW_123");
    }

    @Test
    void givenProjectKeyAndStatusAndRequestWithBothIdAndSlug_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED.name();

        // when / then
        assertThatThrownBy(() -> facade.validate(projectKey, status, "ID", "SLUG"))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage("Both catalogItemId and catalogItemSlug cannot be defined at the same time.");
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenDeleteProvisioningStatusIsCalled_thenDelegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";

        // when
        facade.deleteProvisioningStatus(projectKey, componentId);

        // then
        verify(provisionService).deleteProvisioningStatus(projectKey, componentId);
    }

    @Test
    void givenAProjectKeyAndAnAction_whenAddSystemParametersToActionIsCalled_thenAddsClusterAndCallerToAction() {
        // given
        var projectKey = "PRJ";
        var accessToken = "token123";
        var clusterLocation = "cluster-a";
        var caller = "user@example.com";
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of(clusterLocation));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(authenticationProvider.getUserPrincipalName()).thenReturn(caller);
        when(projectsInfoService.getProjectClusters(accessToken, projectKey)).thenReturn(projectInfo);

        // when
        facade.addSystemParametersToAction(projectKey, action);

        // then
        assertThat(facade.getParameterString(action, "cluster_location")).isEqualTo(clusterLocation);
        assertThat(facade.getParameterString(action, "caller")).isEqualTo(caller);
    }

    @Test
    void givenAProjectWithNoClusters_whenAddSystemParametersToActionIsCalled_thenThrowsProjectConfigurationException() {
        // given
        var projectKey = "PRJ";
        var accessToken = "token123";
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(Collections.emptyList());

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(projectsInfoService.getProjectClusters(accessToken, projectKey)).thenReturn(projectInfo);

        // when / then
        var ex = assertThrows(ProjectConfigurationException.class, () -> facade.addSystemParametersToAction(projectKey, action));
        assertThat(ex.getMessage()).contains("PRJ");
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenRequestDeletionIsCalledAndComponentIsAlreadyDeleting_thenReturnsOkWithoutAwxCall() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var action = CreateIncidentActionMother.of();

        var pc = ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(ProjectComponentStatus.DELETING.name())
                .build();
        when(authenticationProvider.getAccessToken()).thenReturn("token");
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user");
        when(componentCatalogService.getProjectComponentById("token", projectKey, componentId)).thenReturn(pc);
        when(provisionService.getDeletionWorkflowId(projectKey, componentId)).thenReturn("");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster"));
        when(projectsInfoService.getProjectClusters(any(), any())).thenReturn(projectInfo);

        // when
        var result = facade.requestDeletion(projectKey, componentId, action);

        // then
        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.OK);
        verify(awxService, never()).triggerWorkflowJob(any(), any());
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenRequestDeletionIsCalledAndWorkflowNotFound_thenTriggersDefaultAwxWorkflow() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();

        var pc = ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(ProjectComponentStatus.CREATED.name())
                .build();
        when(authenticationProvider.getAccessToken()).thenReturn("token");
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user");
        when(componentCatalogService.getProjectComponentById("token", projectKey, componentId)).thenReturn(pc);
        when(provisionService.getDeletionWorkflowId(projectKey, componentId)).thenReturn("");
        when(provisionService.getDeletionWorkflowName(projectKey, componentId)).thenReturn("");
        when(provisionService.composeCatalogItemId(pc)).thenReturn("catalogItemId");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster"));
        when(projectsInfoService.getProjectClusters(any(), any())).thenReturn(projectInfo);
        when(entitiesMapper.asAwxWorkflowJobLaunch(any(CreateIncidentAction.class))).thenReturn(launch);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(any())).thenReturn(new ProvisionActionResponse());

        // when
        var result = facade.requestDeletion(projectKey, componentId, action);

        // then
        assertNotNull(result, "Result should not be null");
        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.OK);
        verify(awxService).triggerWorkflowJob(anyString(), any());
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenRequestDeletionIsCalledAndWorkflowFound_thenTriggersDeletionWorkflow() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var deletionWorkflow = "DELETION_WF";
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();

        var pc = ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(ProjectComponentStatus.CREATED.name())
                .build();
        when(authenticationProvider.getAccessToken()).thenReturn("token");
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user");
        when(componentCatalogService.getProjectComponentById("token", projectKey, componentId)).thenReturn(pc);
        when(provisionService.getDeletionWorkflowId(projectKey, componentId)).thenReturn(deletionWorkflow);
        when(provisionService.getDeletionWorkflowName(projectKey, componentId)).thenReturn(null);
        when(provisionService.getDeletionWorkflowTimeoutSeconds(projectKey, componentId)).thenReturn(null);
        when(provisionService.composeCatalogItemId(pc)).thenReturn("catalogItemId");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster"));
        when(projectsInfoService.getProjectClusters(any(), any())).thenReturn(projectInfo);
        when(entitiesMapper.asAwxWorkflowJobLaunch(any(CreateIncidentAction.class))).thenReturn(launch);
        when(provisionService.getDeletionParameters(projectKey, componentId)).thenReturn(List.of());
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(any())).thenReturn(new ProvisionActionResponse());

        // when
        var result = facade.requestDeletion(projectKey, componentId, action);

        // then
        assertNotNull(result, "Result should not be null");
        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.OK);
        verify(awxService).triggerWorkflowJob(anyString(), any());
    }

    @Test
    void givenOnlyDeletionWorkflowName_whenRequestDeletion_thenTriggersWrapperWorkflow() {
        var projectKey = "PRJ";
        var componentId = "CID";
        var action = CreateIncidentActionMother.of();

        var pc = ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(ProjectComponentStatus.CREATED.name())
                .build();

        when(authenticationProvider.getAccessToken()).thenReturn("token");
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user");

        when(componentCatalogService.getProjectComponentById("token", projectKey, componentId)).thenReturn(pc);

        when(provisionService.getDeletionWorkflowId(projectKey, componentId)).thenReturn("");
        when(provisionService.getDeletionWorkflowName(projectKey, componentId)).thenReturn("WF_NAME");
        when(provisionService.getDeletionWorkflowTimeoutSeconds(projectKey, componentId)).thenReturn(null);

        when(provisionService.composeCatalogItemId(pc)).thenReturn("catalogItemId");

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster"));
        when(projectsInfoService.getProjectClusters(any(), any())).thenReturn(projectInfo);

        when(entitiesMapper.asAwxWorkflowJobLaunch((ProvisionAction) any())).thenReturn(new AwxWorkflowJobLaunch());
        when(awxService.triggerWorkflowJob(any(), any()))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.empty()));

        var result = facade.requestDeletion(projectKey, componentId, action);

        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.OK);
        verify(awxService).triggerWorkflowJob(eq("DELETE"), any());
    }

    @Test
    void givenWrapperWorkflow_whenRequestDeletion_thenAddsWrapperParameters() {
        var action = CreateIncidentActionMother.of();
        action.setParameters(new ArrayList<>());

        when(authenticationProvider.getAccessToken()).thenReturn("token");

        ReflectionTestUtils.invokeMethod(
                facade,
                "addDeletionWrapperWorkflowParameters",
                "catalogId",
                "componentUrl",
                "wfId",
                null,
                "60",
                action
        );

        assertThat(facade.getParameterString(action, "catalog_item_id")).isEqualTo("catalogId");
        assertThat(facade.getParameterString(action, "component_url")).isEqualTo("componentUrl");
        assertThat(facade.getParameterString(action, "deletion_workflow_id")).isEqualTo("wfId");
        assertThat(facade.getParameterString(action, "deletion_workflow_timeout_seconds")).isEqualTo("60");
    }

    @Test
    void givenWrapperWorkflow_whenRequestDeletion_thenAddsMandatoryStaticParameters() {
        var action = CreateIncidentActionMother.of();
        action.setParameters(new ArrayList<>());

        when(authenticationProvider.getAccessToken()).thenReturn("token");

        ReflectionTestUtils.invokeMethod(
                facade,
                "addDeletionWrapperWorkflowParameters",
                "catalogId",
                "wfId",
                "componentUrl",
                null,
                "60",
                action
        );

        assertThat(facade.getParameterString(action, "dispatched_workflow_params")).contains("project_key");
        assertThat(facade.getParameterString(action, "dispatched_workflow_params")).contains("cluster_location");
        assertThat(facade.getParameterString(action, "dispatched_workflow_params")).contains("component_id");
        assertThat(facade.getParameterString(action, "dispatched_workflow_params")).contains("is_deployed");
        assertThat(facade.getParameterString(action, "dispatched_workflow_params")).contains("change_number");
        assertThat(facade.getParameterString(action, "dispatched_workflow_params")).contains("reason");
    }

    @Test
    void givenBothCatalogItemIdAndSlugEmpty_whenResolveCatalogItemIdIsCalled_thenReturnsNull() {
        // when
        var result = ReflectionTestUtils.invokeMethod(facade, "resolveCatalogItemId", "token", null, null);

        // then
        assertThat(result).isNull();
    }

    @Test
    void givenCatalogItemIdDefined_whenResolveCatalogItemIdIsCalled_thenReturnsId() {
        // when
        var result = ReflectionTestUtils.invokeMethod(facade, "resolveCatalogItemId", "token", "ID", "");

        // then
        assertThat(result).isEqualTo("ID");
    }

    @Test
    void givenCatalogItemSlugDefined_whenResolveCatalogItemIdIsCalled_thenReturnsResolvedId() {
        // given
        var catalogItem = new CatalogItem();
        catalogItem.setId("RESOLVED_ID");
        when(componentCatalogService.getCatalogItemBySlug("token", "SLUG")).thenReturn(catalogItem);

        // when
        var result = ReflectionTestUtils.invokeMethod(facade, "resolveCatalogItemId", "token", null, "SLUG");

        // then
        assertThat(result).isEqualTo("RESOLVED_ID");
    }

    @Test
    void givenSlugNotFound_whenResolveCatalogItemIdIsCalled_thenThrowsSlugNotFoundException() {
        // given
        when(componentCatalogService.getCatalogItemBySlug("token", "SLUG")).thenThrow(new RestClientException("Not Found"));

        // when / then
        assertThrows(SlugNotFoundException.class, () -> ReflectionTestUtils.invokeMethod(facade, "resolveCatalogItemId", "token", null, "SLUG"));
    }

    @Test
    void givenAwxTriggerFails_whenRequestDeletionIsCalled_thenReturnsErrorStatus() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();

        var pc = ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(ProjectComponentStatus.CREATED.name())
                .build();
        when(authenticationProvider.getAccessToken()).thenReturn("token");
        when(authenticationProvider.getUserPrincipalName()).thenReturn("user");
        when(componentCatalogService.getProjectComponentById("token", projectKey, componentId)).thenReturn(pc);
        when(provisionService.getDeletionWorkflowId(projectKey, componentId)).thenReturn("");
        when(provisionService.composeCatalogItemId(pc)).thenReturn("catalogItemId");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of("cluster"));
        when(projectsInfoService.getProjectClusters(any(), any())).thenReturn(projectInfo);
        when(entitiesMapper.asAwxWorkflowJobLaunch(any(CreateIncidentAction.class))).thenReturn(launch);
        when(awxService.triggerWorkflowJob(any(), any())).thenReturn(Pair.of(HttpStatus.INTERNAL_SERVER_ERROR, Optional.empty()));

        // when
        var result = facade.requestDeletion(projectKey, componentId, action);

        // then
        assertNotNull(result, "Result should not be null");
        assertThat(result.httpStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void givenWrapperWorkflow_whenAddDeletionWrapperWorkflowParametersIsCalled_thenDispatchedWorkflowParamsContainsAllParametersAndNotification() {
        // given
        var action = CreateIncidentActionMother.of();
        action.setParameters(new ArrayList<>());

        when(authenticationProvider.getAccessToken()).thenReturn("token");

        // when
        ReflectionTestUtils.invokeMethod(
                facade,
                "addDeletionWrapperWorkflowParameters",
                "catalogId",
                "componentUrl",
                "wfId",
                "wfName",
                "60",
                action
        );

        // then
        var dispatchedParam = action.getParameters().stream()
                .filter(p -> "dispatched_workflow_params".equals(p.getName()))
                .findFirst()
                .orElse(null);

        assertThat(dispatchedParam).isNotNull();
        assertThat(dispatchedParam.getValue()).isInstanceOf(java.util.Set.class);

        @SuppressWarnings("unchecked")
        var dispatchedSet = (java.util.Set<String>) dispatchedParam.getValue();

        var expectedParamNames = action.getParameters().stream()
                .map(CreateIncidentParameter::getName)
                .filter(name -> !"dispatched_workflow_params".equals(name))
                .toList();

        assertThat(dispatchedSet).containsAll(expectedParamNames);
    }


}
