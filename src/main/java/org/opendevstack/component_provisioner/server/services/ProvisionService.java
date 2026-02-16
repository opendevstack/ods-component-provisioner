package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProvisionService {

    private final ProvisionerActionsApi provisionerActionsApi;

    public ProvisionService(@Qualifier("provisionerActionsBasicAuthApi") ProvisionerActionsApi provisionerActionsApi) {
        this.provisionerActionsApi = provisionerActionsApi;
    }

    public void notifyProvisioningStatusUpdate(String projectKey, ProjectComponentStatus status, String componentId, String catalogItemId, String componentUrl) {
        log.info("Notifying provisioning completed");

        var notifyProvisioningCompletedRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .build();

        provisionerActionsApi.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), notifyProvisioningCompletedRequest);
    }

    public void deleteProvisioningStatus(String projectKey, String componentId) {
        log.info("Deleting provisioning completed. Project Key: {}, componentId: {}", projectKey, componentId);

        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        provisionerActionsApi.deleteProvisioningStatus(projectKey, provisioningDeleteRequest);
    }
}
