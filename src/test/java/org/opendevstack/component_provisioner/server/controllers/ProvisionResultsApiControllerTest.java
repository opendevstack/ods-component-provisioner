package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.facade.ProvisionResultsApiFacade;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiControllerTest {
    String exceptionMsg = "Status is not valid. It can only be CREATING, CREATED, FAILED, DELETING, UNKNOWN";

    @Mock
    private ProvisionService provisionService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ProvisionResultsApiFacade provisionResultsApiFacade;

    @InjectMocks
    private ProvisionResultsApiController provisionResultsApiController;

    @Test
    void givenAProvisionService_whenNotifyProvisioningCompletedIsCalled_thenReturnsOk() {
        var projectKey = "project-key";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";

        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.setComponentUrl(componentUrl);

        var response = provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl,null);
        verify(provisionResultsApiFacade).validate(projectKey, status.name());
    }

    @Test
    void givenAProjectKey_AndAComponentId_whenDeleteProvisioningStatus_thenReturnsOk() {
        var projectKey = "project-key";
        var componentId = "componentId";

        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder().componentId(componentId).build();

        var response = provisionResultsApiController.deleteProvisioningStatus(projectKey, provisioningDeleteRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionService).deleteProvisioningStatus(projectKey, componentId);
    }

    @Test
    void givenAProjectKey_AndAComponentId_AndCreateIncidentAction_whenCreateIncident_thenReturnsOk() {
        var projectKey = "project-key";
        var componentId = "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();

        when(authenticationProvider.getIdToken()).thenReturn("id-token");
        when(provisionResultsApiFacade.isInDeletingState(any(), any(), any(), any())).thenReturn(false);
        var actionResponse = new ProvisionActionResponse();
        var awxResponse = AwxResponse.builder().httpStatusCode(HttpStatus.OK).awxResponseBody(actionResponse).build();
        when(provisionResultsApiFacade.requestProvisionToAwx(any(), any(), any())).thenReturn(awxResponse);

        var response = provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actionResponse, response.getBody());
        verify(provisionResultsApiFacade).validate(projectKey, componentId, createIncidentAction);
        verify(provisionResultsApiFacade).requestProvisionToAwx(projectKey, componentId, createIncidentAction);
    }

    @Test
    void givenInvalidComponentId_whenCreateIncident_thenThrowsInvalidRestEntityException() {
        String projectKey = "PRJ";
        String componentId = "";

        var action = CreateIncidentActionMother.of();

        doThrow(new InvalidRestEntityException("project_key, component_id are required.")).when(provisionResultsApiFacade).validate(any(), any(), any());

        var ex = assertThrows(InvalidRestEntityException.class, () -> provisionResultsApiController.createIncident(projectKey, componentId, action));
        assertThat(ex.getMessage()).isEqualTo("project_key, component_id are required.");
    }

    @Test
    void givenAProjectKey_AndAComponentId_AndCreateIncidentAction_whenCreateIncident_AndComponentAlreadyInDeletingState_thenReturnsOk_andIgnoreAWXCall() {
        var projectKey = "project-key";
        var componentId = "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();

        when(authenticationProvider.getIdToken()).thenReturn("id-token");
        when(provisionResultsApiFacade.isInDeletingState(any(), any(), any(), any())).thenReturn(true);

        var response = provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionResultsApiFacade, never()).requestProvisionToAwx(any(), any(), any());
    }

    @Test
    void givenInvalidStatus_whenNotifyProvisioningStatusUpdate_then400OrInvalidRestEntityException() {
        var projectKey = "project-key";
        var invalidStatus = "NOT_A_STATUS";
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.setComponentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class));

        var exception = assertThrows(InvalidRestEntityException.class, () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, invalidStatus, request));

        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
    }

    @Test
    void givenLowercaseStatus_whenNotifyProvisioningStatusUpdate_thenEitherOkOrReject() {
        var projectKey = "project-key";
        var statusLowercase = "created";
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.setComponentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class));

        var exception = assertThrows(InvalidRestEntityException.class, () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, statusLowercase, request));

        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
    }
}