package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class ApiClientsBuilder {
    private static final String BEARER_TOKEN = "bearerAuth";

    private final RestTemplate restTemplate;

    public org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient projectsInfoServiceApiClient(String accessToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient(restTemplate);

        apiClient.setBasePath(baseRestUrl);

        var auth = (org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth) apiClient.getAuthentication(BEARER_TOKEN);
        auth.setBearerToken(accessToken);

        return apiClient;
    }

    public org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient componentCatalogApiClient(String accessToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient(restTemplate);

        apiClient.setBasePath(baseRestUrl);

        var auth = (org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth) apiClient.getAuthentication(BEARER_TOKEN);
        auth.setBearerToken(accessToken);

        return apiClient;
    }

    public org.opendevstack.component_provisioner.client.ods_api_server.v1.ApiClient odsApiServerApiClient(String accessToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_provisioner.client.ods_api_server.v1.ApiClient(restTemplate);

        apiClient.setBasePath(baseRestUrl);

        var auth = (org.opendevstack.component_provisioner.client.ods_api_server.v1.auth.HttpBearerAuth) apiClient.getAuthentication(BEARER_TOKEN);
        auth.setBearerToken(accessToken);

        return apiClient;
    }

    public org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi projectsApi(org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient apiClient) {
        return new org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi(apiClient);
    }

    public org.opendevstack.component_provisioner.client.ods_api_server.v1.api.ProjectsApi projectsApi(org.opendevstack.component_provisioner.client.ods_api_server.v1.ApiClient apiClient) {
        return new org.opendevstack.component_provisioner.client.ods_api_server.v1.api.ProjectsApi(apiClient);
    }

    public AzureGroupsApi azureGroupsApi(org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient apiClient) {
        return new AzureGroupsApi(apiClient);
    }

    public CatalogItemsApi catalogItemsApi(org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient apiClient) {
        return new CatalogItemsApi(apiClient);
    }

    public ProvisionerActionsApi provisionerActionsApi(String accessToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient(restTemplate);
        apiClient.setBasePath(baseRestUrl);

        var auth = (org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth) apiClient.getAuthentication(BEARER_TOKEN);
        auth.setBearerToken(accessToken);

        return new ProvisionerActionsApi(apiClient);
    }
}