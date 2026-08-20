package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.ApiClient;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.api.ProjectsApi;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.model.CreateProjectResponseMother;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.net.MalformedURLException;
import java.net.URI;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        var projectKey = "test-project";
        var accessToken = "test-token";
        var baseUrl = "http://test.url";

        var expectedResponse = CreateProjectResponseMother.of("test-project");

        ReflectionTestUtils.setField(odsApiService, "clientId", clientId);
        ReflectionTestUtils.setField(odsApiService, "clientSecret", clientSecret);
        ReflectionTestUtils.setField(odsApiService, "scope", scope);

        when(azureAdTokenService.getAccessToken(clientId, clientSecret, scope)).thenReturn(accessToken);
        when(odsApiServerServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseUrl).toURL());

        when(apiClientsBuilder.odsApiServerApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient)).thenReturn(projectsApi);
        when(projectsApi.getProject(projectKey)).thenReturn(expectedResponse);

        // when
        var result = odsApiService.getProject(projectKey);

        // then
        assertThat(result).isEqualTo(expectedResponse);
    }

    @Test
    void givenProjectNotFound_whenGetProject_thenReturnsNull() throws MalformedURLException {
        // given
        var clientId = "test-client-id";
        var clientSecret = "test-client-secret";
        var scope = "test-scope";

        var projectKey = "non-existent-project";
        var accessToken = "test-token";
        var baseUrl = "http://test.url";
        var exception = mock(HttpClientErrorException.class);

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
        var result = odsApiService.getProject(projectKey);

        // then
        assertThat(result).isNull();
    }

    @Test
    void givenApiError_whenGetProject_thenThrowsException() throws MalformedURLException {
        // given
        var clientId = "test-client-id";
        var clientSecret = "test-client-secret";
        var scope = "test-scope";

        var projectKey = "test-project";
        var accessToken = "test-token";
        var baseUrl = "http://test.url";
        var exception = mock(HttpClientErrorException.class);

        ReflectionTestUtils.setField(odsApiService, "clientId", clientId);
        ReflectionTestUtils.setField(odsApiService, "clientSecret", clientSecret);
        ReflectionTestUtils.setField(odsApiService, "scope", scope);

        when(azureAdTokenService.getAccessToken(clientId, clientSecret, scope)).thenReturn(accessToken);
        when(odsApiServerServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseUrl).toURL());

        when(apiClientsBuilder.odsApiServerApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient)).thenReturn(projectsApi);
        when(exception.getStatusCode()).thenReturn(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        when(projectsApi.getProject(projectKey)).thenThrow(exception);

        // when / then
        assertThatThrownBy(() -> odsApiService.getProject(projectKey))
                .isInstanceOf(HttpClientErrorException.class);
    }
}
