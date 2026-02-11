package org.opendevstack.component_provisioner.server.controllers;

import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentActionMother;
import org.opendevstack.component_provisioner.server.model.NotifyProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiControllerTest {

    @Mock
    private AwxService awxService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private ProvisionService provisionService;

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
        var status = "status";
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";

        NotifyProvisioningStatusUpdateRequest notifyProvisioningCompletedRequest = new NotifyProvisioningStatusUpdateRequest();
        notifyProvisioningCompletedRequest.setComponentId(componentId);
        notifyProvisioningCompletedRequest.setCatalogItemId(catalogItemId);
        notifyProvisioningCompletedRequest.setComponentUrl(componentUrl);

        // when
        var response = provisionResultsApiController.notifyProvisioningStatusUpdate(projectKey, status, notifyProvisioningCompletedRequest);

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

}
