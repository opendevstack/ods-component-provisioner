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
    void givenAccessTokenAndBaseUrl_whenProjectsInfoServiceApiClientIsCalled_thenClientIsConfiguredCorrectly() {
        // given
        String accessToken = "test-token";
        String baseUrl = "http://example.com";

        // when
        ApiClient client = builder.projectsInfoServiceApiClient(accessToken, baseUrl);

        // then
        assertThat(client).isNotNull();
        assertThat(client.getBasePath()).isEqualTo(baseUrl);

        var auth = (org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth) client.getAuthentication("bearerAuth");
        assertThat(auth.getBearerToken()).isEqualTo(accessToken);
    }

    @Test
    void givenBearerTokenAndBaseUrl_whenComponentCatalogApiClientIsCalled_thenClientIsConfiguredCorrectly() {
        // given
        String accessToken = "test-token";
        String baseUrl = "http://component-catalog";

        // when
        var client = builder.componentCatalogApiClient(accessToken, baseUrl);

        // then
        assertThat(client).isNotNull();
        assertThat(client.getBasePath()).isEqualTo(baseUrl);

        var auth = (org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth) client.getAuthentication("bearerAuth");
        assertThat(auth.getBearerToken()).isEqualTo(accessToken);
    }

    @Test
    void givenApiClient_whenProjectsApiIsCalled_thenReturnsProjectsApiInstance() {
        // given
        ApiClient client = new ApiClient();

        // when
        ProjectsApi api = builder.projectsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenApiClient_whenAzureGroupsApiIsCalled_thenReturnsAzureGroupsApiInstance() {
        // given
        ApiClient client = new ApiClient();

        // when
        AzureGroupsApi api = builder.azureGroupsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenApiClient_whenCatalogItemsApiIsCalled_thenReturnsCatalogItemsApiInstance() {
        // given
        var client = new org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient();

        // when
        CatalogItemsApi api = builder.catalogItemsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenAccessTokenAndBaseUrl_whenProvisionerActionsApiIsCalled_thenReturnsProvisionerActionsApiInstance() {
        // given
        String accessToken = "test-token";
        String baseUrl = "http://component-catalog";

        // when
        ProvisionerActionsApi api = builder.provisionerActionsApi(accessToken, baseUrl);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isNotNull();
        assertThat(api.getApiClient().getBasePath()).isEqualTo(baseUrl);
    }

    @Test
    void givenAccessTokenAndBaseUrl_whenOdsApiServerApiClient_thenReturnsConfiguredApiClient() {
        // given
        String accessToken = "test-token";
        String baseRestUrl = "http://test.url";

        // when
        var result = builder.odsApiServerApiClient(accessToken, baseRestUrl);

        // then
        assertThat(result.getBasePath()).isEqualTo(baseRestUrl);
    }

    @Test
    void givenApiClient_whenProjectsApiForOds_thenReturnsProjectsApi() {
        // given
        var client = new org.opendevstack.component_provisioner.client.ods_api_server.v1.ApiClient();

        // when
        var result = builder.projectsApi(client);

        // then
        assertThat(result).isNotNull();
    }

}