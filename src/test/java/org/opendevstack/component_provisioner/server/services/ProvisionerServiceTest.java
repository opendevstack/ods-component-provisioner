package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProvisionerServiceTest {

    @Mock
    private ProvisionerActionsApi provisionerActionsApi;

    @InjectMocks
    private ProvisionService provisionService;

    @Test
    void givenAProvisionClient_WhenNotifyProvisioningCompleted_ThenProvisioningIsNotified() {
        // given
        var projectKey = "projectKey";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl);

        // then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdatePartially(projectKey, status.name(), ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .build());
    }

    @Test
    void givenAProjectKey_andAComponentId_whenDeleteProvisioningStatus_thenProvisioningApiIsCalled() {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

        var provisionDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId);

        // then
        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, provisionDeleteRequest);
    }
}
