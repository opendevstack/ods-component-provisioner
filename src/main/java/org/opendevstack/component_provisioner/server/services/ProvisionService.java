package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProvisionService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    public void notifyProvisioningStatusUpdate(String projectKey, ProjectComponentStatus status, String componentId,
                                               String catalogItemId, String componentUrl, String accessToken) {
        log.info("Notifying provisioning completed");

        var provisionerActionsApi = apiClientsBuilder.provisionerActionsApi(accessToken, componentCatalogServiceProps.getBaseRestUrl().toString());

        var notifyProvisioningCompletedRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .accessToken(accessToken)
                .build();

        log.debug("Calling provisionerActionsApi.notifyProvisioningStatusUpdatePartially. ProjectKey: {}, status: {}, notifyProvisioningCompletedRequest: {}",
                projectKey, status.name(), notifyProvisioningCompletedRequest);

        provisionerActionsApi.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), notifyProvisioningCompletedRequest);
    }

    public void deleteProvisioningStatus(String projectKey, String componentId, String accessToken) {
        log.info("Deleting provisioning completed. Project Key: {}, componentId: {}", projectKey, componentId);

        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        var provisionerActionsApi = apiClientsBuilder.provisionerActionsApi(accessToken, componentCatalogServiceProps.getBaseRestUrl().toString());

        provisionerActionsApi.deleteProvisioningStatus(projectKey, provisioningDeleteRequest);
    }
}
