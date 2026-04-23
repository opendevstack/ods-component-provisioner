package org.opendevstack.component_provisioner.server.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.ApiClient;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.api.ProjectsApi;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.model.CreateProjectResponse;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.model.CreateProjectResponseMother;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.net.MalformedURLException;
import java.net.URI;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OdsApiServiceTest {

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApplicationPropertiesConfiguration.OdsApiServerServiceProps odsApiServerServiceProps;

    @Mock
    private ApiClient apiClient;

    @Mock
    private ProjectsApi projectsApi;

    @Mock
    private AzureAdTokenService azureAdTokenService;

    @InjectMocks
    private OdsApiService odsApiService;

    @Test
    void givenValidProjectKey_whenGetProject_thenReturnsProjectResponse() throws MalformedURLException {
        // given
        var clientId = "test-client-id";
        var clientSecret = "test-client-secret";
        var scope = "test-scope";

        String projectKey = "test-project";
        String accessToken = "test-token";
        String baseUrl = "http://test.url";

        CreateProjectResponse expectedResponse = CreateProjectResponseMother.of("test-project");

        ReflectionTestUtils.setField(odsApiService, "clientId", clientId);
        ReflectionTestUtils.setField(odsApiService, "clientSecret", clientSecret);
        ReflectionTestUtils.setField(odsApiService, "scope", scope);

        when(azureAdTokenService.getAccessToken(clientId, clientSecret, scope)).thenReturn(accessToken);
        when(odsApiServerServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseUrl).toURL());

        when(apiClientsBuilder.odsApiServerApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient)).thenReturn(projectsApi);
        when(projectsApi.getProject(projectKey)).thenReturn(expectedResponse);

        // when
        CreateProjectResponse result = odsApiService.getProject(projectKey);

        // then
        Assertions.assertThat(result).isEqualTo(expectedResponse);
    }

    @Test
    void givenProjectNotFound_whenGetProject_thenReturnsNull() throws MalformedURLException {
        // given
        var clientId = "test-client-id";
        var clientSecret = "test-client-secret";
        var scope = "test-scope";

        String projectKey = "non-existent-project";
        String accessToken = "test-token";
        String baseUrl = "http://test.url";
        HttpClientErrorException exception = mock(HttpClientErrorException.class);

        ReflectionTestUtils.setField(odsApiService, "clientId", clientId);
        ReflectionTestUtils.setField(odsApiService, "clientSecret", clientSecret);
        ReflectionTestUtils.setField(odsApiService, "scope", scope);

        when(azureAdTokenService.getAccessToken(clientId, clientSecret, scope)).thenReturn(accessToken);
        when(odsApiServerServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseUrl).toURL());

        when(apiClientsBuilder.odsApiServerApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient)).thenReturn(projectsApi);
        when(exception.getStatusCode()).thenReturn(org.springframework.http.HttpStatus.NOT_FOUND);
        when(projectsApi.getProject(projectKey)).thenThrow(exception);

        // when
        CreateProjectResponse result = odsApiService.getProject(projectKey);

        // then
        Assertions.assertThat(result).isNull();
    }

    @Test
    void givenApiError_whenGetProject_thenThrowsException() throws MalformedURLException {
        // given
        var clientId = "test-client-id";
        var clientSecret = "test-client-secret";
        var scope = "test-scope";

        String projectKey = "test-project";
        String accessToken = "test-token";
        String baseUrl = "http://test.url";
        HttpClientErrorException exception = mock(HttpClientErrorException.class);

        ReflectionTestUtils.setField(odsApiService, "clientId", clientId);
        ReflectionTestUtils.setField(odsApiService, "clientSecret", clientSecret);
        ReflectionTestUtils.setField(odsApiService, "scope", scope);

        when(azureAdTokenService.getAccessToken(clientId, clientSecret, scope)).thenReturn(accessToken);
        when(odsApiServerServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseUrl).toURL());

        when(apiClientsBuilder.odsApiServerApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient)).thenReturn(projectsApi);
        when(exception.getStatusCode()).thenReturn(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        when(projectsApi.getProject(projectKey)).thenThrow(exception);

        // when & then
        Assertions.assertThatThrownBy(() -> odsApiService.getProject(projectKey))
                .isInstanceOf(HttpClientErrorException.class);
    }
}
