package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.springframework.stereotype.Service;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;

@Service
@Slf4j
public class ApiClientsBuilder {
    public org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient projectsInfoServiceApiClient(String idToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient();

        apiClient.setBasePath(baseRestUrl);

        var auth = (org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return apiClient;
    }

    public org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient componentCatalogApiClient(String idToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient();

        apiClient.setBasePath(baseRestUrl);

        var auth = (org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return apiClient;
    }

    public ProjectsApi projectsApi(org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient apiClient) {
        return new ProjectsApi(apiClient);
    }

    public AzureGroupsApi azureGroupsApi(org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient apiClient) {
        return new AzureGroupsApi(apiClient);
    }

    public CatalogItemsApi catalogItemsApi(org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient apiClient) {
        return new CatalogItemsApi(apiClient);
    }

    public ProvisionerActionsApi provisionerActionsApi(org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient apiClient) {
        return new ProvisionerActionsApi(apiClient);
    }
}