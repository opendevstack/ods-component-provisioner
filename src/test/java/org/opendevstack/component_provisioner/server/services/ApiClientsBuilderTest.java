package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApiClientsBuilderTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ApiClientsBuilder builder;

    @Test
    void givenIdTokenAndBaseUrl_whenProjectsInfoServiceApiClient_thenClientConfiguredCorrectly() {
        // given
        String idToken = "test-token";
        String baseUrl = "http://example.com";

        // when
        ApiClient client = builder.projectsInfoServiceApiClient(idToken, baseUrl);

        // then
        assertThat(client).isNotNull();
        assertThat(client.getBasePath()).isEqualTo(baseUrl);

        var auth = (org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth) client.getAuthentication("bearerAuth");
        assertThat(auth.getBearerToken()).isEqualTo(idToken);
    }

    @Test
    void givenIdTokenAndBaseUrl_whenComponentCatalogApiClient_thenClientConfiguredCorrectly() {
        // given
        String idToken = "test-token";
        String baseUrl = "http://component-catalog";

        // when
        var client = builder.componentCatalogApiClient(idToken, baseUrl);

        // then
        assertThat(client).isNotNull();
        assertThat(client.getBasePath()).isEqualTo(baseUrl);

        var auth = (org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth) client.getAuthentication("bearerAuth");
        assertThat(auth.getBearerToken()).isEqualTo(idToken);
    }

    @Test
    void givenApiClient_whenProjectsApi_thenReturnProjectsApiInstance() {
        // given
        ApiClient client = new ApiClient();

        // when
        ProjectsApi api = builder.projectsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenApiClient_whenAzureGroupsApi_thenReturnAzureGroupsApiInstance() {
        // given
        ApiClient client = new ApiClient();

        // when
        AzureGroupsApi api = builder.azureGroupsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenApiClient_whenCatalogItemsApi_thenReturnCatalogItemsApiInstance() {
        // given
        var client = new org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient();

        // when
        CatalogItemsApi api = builder.catalogItemsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenApiClient_whenProvisionerActionsApi_thenReturnProvisionerActionsApiInstance() {
        // given
        String idToken = "test-token";
        String baseUrl = "http://component-catalog";

        // when
        ProvisionerActionsApi api = builder.provisionerActionsApi(idToken, baseUrl);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isNotNull();
        assertThat(api.getApiClient().getBasePath()).isEqualTo(baseUrl);
    }
}