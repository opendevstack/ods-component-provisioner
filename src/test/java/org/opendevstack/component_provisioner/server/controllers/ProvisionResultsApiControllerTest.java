package org.opendevstack.component_provisioner.server.controllers;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfoMother;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentActionMother;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.NotifyProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiControllerTest {

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

    @InjectMocks
    private ProvisionResultsApiController provisionResultsApiController;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(provisionResultsApiController, "workflowJobId", "WORKFLOW_123");
    }

    @Test
    void givenAProvisionService_whenNotifyProvisioningCompletedIsCalled_thenReturnsOk( ) {
        // given
        var projectKey = "project-key";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";

        NotifyProvisioningStatusUpdateRequest notifyProvisioningCompletedRequest = new NotifyProvisioningStatusUpdateRequest();
        notifyProvisioningCompletedRequest.setComponentId(componentId);
        notifyProvisioningCompletedRequest.setCatalogItemId(catalogItemId);
        notifyProvisioningCompletedRequest.setComponentUrl(componentUrl);

        // when
        var response = provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), notifyProvisioningCompletedRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl);
    }

    @Test
    void givenAProjectKey_AndAComponentId_whenDeleteProvisioningStatus_thenReturnsOk( ) {
        // given
        var projectKey = "project-key";
        var componentId =  "componentId";

        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        // when
        var response = provisionResultsApiController.deleteProvisioningStatus(projectKey, provisioningDeleteRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(provisionService).deleteProvisioningStatus(projectKey, componentId);
    }

    @Test
    void givenAProjectKey_AndAComponentId_AndCreateIncidentAction_whenCreateIncident_thenReturnsOk( ) {
        // given
        var projectKey = "project-key";
        var componentId =  "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();

        var awxWorkflowJobLaunch = new AwxWorkflowJobLaunch();
        var awxStatusCode = HttpStatus.OK;
        var awxBody = new AwxWorkflowJob();
        Pair<HttpStatusCode, Optional<AwxWorkflowJob>> awxResult = Pair.of(awxStatusCode, Optional.of(awxBody));

        when(entitiesMapper.asAwxWorkflowJobLaunch(createIncidentAction)).thenReturn(awxWorkflowJobLaunch);
        when(awxService.triggerWorkflowJob("CREATE_INCIDENT", awxWorkflowJobLaunch)).thenReturn(awxResult);

        // when
        var response = provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(entitiesMapper).asAwxWorkflowJobLaunch(createIncidentAction);
        verify(awxService).triggerWorkflowJob("CREATE_INCIDENT", awxWorkflowJobLaunch);
    }


    @Test
    void givenInvalidComponentId_whenCreateIncident_thenThrowsInvalidRestEntityException() {
        // given
        String projectKey = "PRJ";
        String componentId = "";

        CreateIncidentAction action = CreateIncidentActionMother.of();

        // when
        InvalidRestEntityException ex = assertThrows(InvalidRestEntityException.class,
                () -> provisionResultsApiController.createIncident(projectKey, componentId, action));

        // then
        assertThat(ex.getMessage()).isEqualTo("project_key, component_id are required.");
    }

    @Test
    void givenInvalidParameter_whenCreateIncident_thenThrowsInvalidRestEntityException() {
        // given
        String projectKey = "PRJ";
        String componentId = "cmp-123";

        CreateIncidentAction action = CreateIncidentAction.builder()
                .build();

        // when
        InvalidRestEntityException ex = assertThrows(InvalidRestEntityException.class,
                () -> provisionResultsApiController.createIncident(projectKey, componentId, action));

        // then
        assertThat(ex.getMessage()).isEqualTo("caller, cluster_location, is_deployed, change_number and reason are required.");
    }

    @Test
    void givenAProjectKey_AndAComponentId_AndCreateIncidentAction_whenCreateIncident_AndComponentAlreadyInDeletingState_thenReturnsOk_andIgnoreAWXCall( ) {
        // given
        var projectKey = "project-key";
        var componentId =  "componentId";
        var idToken = "idToken";
        var createIncidentAction = CreateIncidentActionMother.of();

        var accessToken = createIncidentAction.getParameters().stream()
                .filter(parameter -> parameter.getName().equals("access_token"))
                .map(CreateIncidentParameter::getValue)
                .map(Object::toString)
                .findFirst().orElseThrow();

        var projectComponentInfo = ProjectComponentInfoMother.of(ProjectComponentStatus.DELETING);
        var projectComponents = List.of(projectComponentInfo);

        when(authenticationProvider.getIdToken()).thenReturn(idToken);
        when(componentCatalogService.getProjectComponents(projectKey, idToken, accessToken)).thenReturn(projectComponents);

        // when
        var response = provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verifyNoInteractions(entitiesMapper);
        verifyNoInteractions(awxService);
    }

    @Test
    void givenInvalidStatus_whenNotifyProvisioningStatusUpdate_then400OrInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var invalidStatus = "NOT_A_STATUS";
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.setComponentUrl("http://example");

        // when
        var exception = assertThrows(InvalidRestEntityException.class,
                () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, invalidStatus, request));

        // then
        assertThat(exception.getMessage()).isEqualTo("Status is not valid. It can only be CREATING, CREATED, DELETING, UNKNOWN");
    }

    @Test
    void givenLowercaseStatus_whenNotifyProvisioningStatusUpdate_thenEitherOkOrReject() {
        // given
        var projectKey = "project-key";
        var statusLowercase = "created"; // lower-case vs ProjectComponentStatus.CREATED
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.setComponentUrl("http://example");

        // when
        var exception = assertThrows(InvalidRestEntityException.class,
                () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, statusLowercase, request));

        // then
        assertThat(exception.getMessage()).isEqualTo("Status is not valid. It can only be CREATING, CREATED, DELETING, UNKNOWN");
    }

}
