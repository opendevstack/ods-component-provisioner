package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.facade.ProvisionResultsApiFacade;
import org.opendevstack.component_provisioner.server.model.CreateIncidentActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiControllerTest {

    private static final String EXCEPTION_MSG =
            "Status is not valid. It can only be CREATING, CREATED, FAILED, DELETING, UNKNOWN";

    @Mock
    private ProvisionResultsApiFacade provisionResultsApiFacade;

    @InjectMocks
    private ProvisionResultsApiController provisionResultsApiController;

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdateIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var status = ProvisioningStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var catalogItemSlug = "catalogItemSlug";
        var componentUrl = "componentUrl";

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.setComponentUrl(JsonNullable.of(componentUrl));

        // when
        var response = provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, status, request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(provisionResultsApiFacade).notifyProvisioningStatusUpdate(projectKey, status, request);
        verify(provisionResultsApiFacade).validate(projectKey, status, catalogItemId, catalogItemSlug);
    }

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var status = ProvisioningStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.setComponentUrl(JsonNullable.of(componentUrl));

        // when
        var response = provisionResultsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status, request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(provisionResultsApiFacade).notifyProvisioningStatusUpdatePartially(projectKey, status, request);
        verify(provisionResultsApiFacade).validate(projectKey, status, catalogItemId, null);
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenDeleteProvisioningStatusIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder().componentId(componentId).build();

        // when
        var response = provisionResultsApiController.deleteProjectComponent(projectKey, provisioningDeleteRequest);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(provisionResultsApiFacade).deleteProvisioningStatus(projectKey, componentId);
    }

    @Test
    void givenAProjectKeyAndAComponentIdAndCreateIncidentAction_whenCreateIncidentIsCalled_thenReturnsOk() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();

        var actionResponse = new ProvisionActionResponse();
        var awxResponse = AwxResponse.builder().httpStatusCode(HttpStatus.OK).awxResponseBody(actionResponse).build();
        when(provisionResultsApiFacade.requestDeletion(projectKey, componentId, createIncidentAction))
                .thenReturn(awxResponse);

        // when
        var response = provisionResultsApiController.requestDeletion(projectKey, componentId, createIncidentAction);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actionResponse);
        verify(provisionResultsApiFacade).requestDeletion(projectKey, componentId, createIncidentAction);
    }

    @Test
    void givenInvalidComponentId_whenCreateIncidentIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "PRJ";
        var componentId = "";
        var action = CreateIncidentActionMother.of();

        when(provisionResultsApiFacade.requestDeletion(any(), any(), any()))
                .thenThrow(new InvalidRestEntityException("project_key, component_id are required."));

        // when / then
        assertThatThrownBy(() -> provisionResultsApiController.requestDeletion(projectKey, componentId, action))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage("project_key, component_id are required.");
    }

    @Test
    void givenAProjectKeyAndAComponentIdAndCreateIncidentAction_whenCreateIncidentIsCalledAndComponentAlreadyInDeletingState_thenReturnsOkAndIgnoreAWXCall() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();

        var awxResponse = AwxResponse.builder()
                .httpStatusCode(HttpStatus.OK)
                .awxResponseBody(new ProvisionActionResponse())
                .build();
        when(provisionResultsApiFacade.requestDeletion(projectKey, componentId, createIncidentAction))
                .thenReturn(awxResponse);

        // when
        var response = provisionResultsApiController.requestDeletion(projectKey, componentId, createIncidentAction);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(provisionResultsApiFacade).requestDeletion(projectKey, componentId, createIncidentAction);
    }

    @Test
    void givenNeitherCatalogItemIdNorCatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var statusLowercase = ProvisioningStatus.FAILED;
        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId("comp-1");
        request.setComponentUrl(JsonNullable.of("http://example"));

        doThrow(new InvalidRestEntityException(EXCEPTION_MSG))
                .when(provisionResultsApiFacade)
                .validate(any(String.class), any(ProvisioningStatus.class), nullable(String.class), nullable(String.class));

        // when / then
        assertThatThrownBy(
                () -> provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, statusLowercase, request))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage(EXCEPTION_MSG);
    }

    @Test
    void givenInvalidExtraParams_whenCreateIncidentIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "project-key";
        var componentId = "componentId";
        var createIncidentAction = CreateIncidentActionMother.of();
        var errorMsg = "is_deployed, change_number and reason are required.";

        when(provisionResultsApiFacade.requestDeletion(any(), any(), any()))
                .thenThrow(new InvalidRestEntityException(errorMsg));

        // when / then
        assertThatThrownBy(
                () -> provisionResultsApiController.requestDeletion(projectKey, componentId, createIncidentAction))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage(errorMsg);
    }
}

