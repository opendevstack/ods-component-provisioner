package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProvisionService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;
    private final ProvisionerActionsApi provisionerActionsBasicAuthApi;

    public ProvisionService(ApiClientsBuilder apiClientsBuilder,
                            ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps,
                            @Qualifier("provisionerActionsBasicAuthApi") ProvisionerActionsApi provisionerActionsBasicAuthApi) {
        this.apiClientsBuilder = apiClientsBuilder;
        this.componentCatalogServiceProps = componentCatalogServiceProps;
        this.provisionerActionsBasicAuthApi = provisionerActionsBasicAuthApi;
    }

    public void notifyProvisioningStatusUpdate(String projectKey,
                                               ProjectComponentStatus status,
                                               ProvisioningStatusUpdateRequest clientRequest,
                                               String accessToken) {
        log.debug("PUT component-catalog /provision/{}/{} body={}", projectKey, status.name(), clientRequest);

        catalogProvisionerActionsApi(accessToken)
                .notifyProvisioningStatusUpdate(projectKey, status.name(), clientRequest);
    }

    public void notifyProvisioningStatusUpdatePartially(String projectKey,
                                                        ProjectComponentStatus status,
                                                        ProvisioningStatusUpdateRequest clientRequest,
                                                        String accessToken) {
        log.debug("PATCH component-catalog /provision/{}/{} body={}", projectKey, status.name(), clientRequest);

        catalogProvisionerActionsApi(accessToken)
                .notifyProvisioningStatusUpdatePartially(projectKey, status.name(), clientRequest);
    }

    public void deleteProvisioningStatus(String projectKey, String componentId) {
        log.info("Deleting provisioning status. Project Key: {}, componentId: {}", projectKey, componentId);

        var deleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        catalogProvisionerActionsBasicAuthApi()
                .deleteProvisioningStatus(projectKey, deleteRequest);
    }

    private ProvisionerActionsApi catalogProvisionerActionsApi(String accessToken) {
        return apiClientsBuilder.provisionerActionsApi(accessToken,
                componentCatalogServiceProps.getBaseRestUrl().toString());
    }

    private ProvisionerActionsApi catalogProvisionerActionsBasicAuthApi() {
        return provisionerActionsBasicAuthApi;
    }
}
