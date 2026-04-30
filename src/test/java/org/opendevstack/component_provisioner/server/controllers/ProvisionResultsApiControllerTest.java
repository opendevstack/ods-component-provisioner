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
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
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
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ProvisionResultsApiFacade provisionResultsApiFacade;

    @InjectMocks
    private ProvisionResultsApiController provisionResultsApiController;

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdateIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var catalogItemSlug = "catalogItemSlug";
        var componentUrl = "componentUrl";
        var accessToken = "accessToken";

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when
        var response = provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), request);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionResultsApiFacade).notifyProvisioningStatusUpdate(projectKey, status, request, accessToken);
        verify(provisionResultsApiFacade).validate(projectKey, status.name(), request);
    }

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var accessToken = "accessToken";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.componentUrl(componentUrl);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when
        var response = provisionResultsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), request);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionResultsApiFacade).notifyProvisioningStatusUpdatePartially(projectKey, status, request, accessToken);
        verify(provisionResultsApiFacade).validate(projectKey, status.name(), request);
    }

    @Test
    void givenInvalidStatus_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var invalidStatus = "NOT_A_STATUS";
        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.componentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg))
                .when(provisionResultsApiFacade)
                .validate(any(String.class), any(String.class), any(ProvisioningStatusPartialUpdateRequest.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () ->
                provisionResultsApiController.notifyProvisioningStatusUpdatePartially(projectKey, invalidStatus, request);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);
        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
        verify(provisionResultsApiFacade, never()).notifyProvisioningStatusUpdatePartially(any(), any(), any(), any());
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenDeleteProvisioningStatusIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var accessToken = "accessToken";

        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder().componentId(componentId).build();

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when
        var response = provisionResultsApiController.deleteProvisioningStatus(projectKey, provisioningDeleteRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionResultsApiFacade).deleteProvisioningStatus(projectKey, componentId, accessToken);
    }

    @Test
    void givenAProjectKeyAndAComponentIdAndCreateIncidentAction_whenCreateIncidentIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var accessToken = "accessToken";
        var createIncidentAction = CreateIncidentActionMother.of();

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(provisionResultsApiFacade.isInDeletingState(any(), any(), any())).thenReturn(false);
        var actionResponse = new ProvisionActionResponse();
        var awxResponse = AwxResponse.builder().httpStatusCode(HttpStatus.OK).awxResponseBody(actionResponse).build();
        when(provisionResultsApiFacade.requestProvisionToAwx(any(), any(), any())).thenReturn(awxResponse);

        // when
        var response = provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actionResponse, response.getBody());
        verify(provisionResultsApiFacade).validate(projectKey, componentId, createIncidentAction);
        verify(provisionResultsApiFacade).addSystemParametersToAction(projectKey, createIncidentAction);
        verify(provisionResultsApiFacade).requestProvisionToAwx(projectKey, componentId, createIncidentAction);
        verify(provisionResultsApiFacade).notifyProvisioningStatusUpdate(eq(projectKey), eq(ProjectComponentStatus.DELETING), any(ProvisioningStatusUpdateRequest.class), eq(accessToken));
    }

    @Test
    void givenInvalidComponentId_whenCreateIncidentIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        String projectKey = "PRJ";
        String componentId = "";

        var action = CreateIncidentActionMother.of();

        doThrow(new InvalidRestEntityException("project_key, component_id are required.")).when(provisionResultsApiFacade).validate(any(String.class), any(String.class), any(CreateIncidentAction.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> provisionResultsApiController.createIncident(projectKey, componentId, action);

        // then
        var ex = assertThrows(InvalidRestEntityException.class, call);
        assertThat(ex.getMessage()).isEqualTo("project_key, component_id are required.");
    }

    @Test
    void givenAProjectKeyAndAComponentIdAndCreateIncidentAction_whenCreateIncidentIsCalledAndComponentAlreadyInDeletingState_thenReturnsOkAndIgnoreAWXCall() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var accessToken = "accessToken";
        var createIncidentAction = CreateIncidentActionMother.of();

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(provisionResultsApiFacade.isInDeletingState(any(), any(), any())).thenReturn(true);

        // when
        var response = provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(provisionResultsApiFacade).addSystemParametersToAction(projectKey, createIncidentAction);
        verify(provisionResultsApiFacade, never()).requestProvisionToAwx(any(), any(), any());
    }

    @Test
    void givenInvalidStatus_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var invalidStatus = "NOT_A_STATUS";
        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.componentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class), any(ProvisioningStatusUpdateRequest.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, invalidStatus, request);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);

        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
    }

    @Test
    void givenLowercaseStatus_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var statusLowercase = "created";
        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.componentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class), any(ProvisioningStatusUpdateRequest.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, statusLowercase, request);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);

        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
    }

    @Test
    void givenBothCatalogItemIdAndCatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var statusLowercase = "created";
        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setCatalogItemId("cat-1");
        request.setCatalogItemSlug("slug");
        request.componentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class), any(ProvisioningStatusUpdateRequest.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, statusLowercase, request);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);

        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
    }

    @Test
    void givenNeitherCatalogItemIdNorCatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var statusLowercase = "FAILED";
        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.componentUrl("http://example");

        doThrow(new InvalidRestEntityException(exceptionMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class), any(ProvisioningStatusUpdateRequest.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, statusLowercase, request);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);

        assertThat(exception.getMessage()).isEqualTo(exceptionMsg);
    }

    @Test
    void givenInvalidExtraParams_whenCreateIncidentIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();
        var errorMsg = "is_deployed, change_number and reason are required.";

        doThrow(new InvalidRestEntityException(errorMsg)).when(provisionResultsApiFacade).validate(any(String.class), any(String.class), any(CreateIncidentAction.class));

        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> provisionResultsApiController.createIncident(projectKey, componentId, createIncidentAction);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);
        assertThat(exception.getMessage()).isEqualTo(errorMsg);
    }
}