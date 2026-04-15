package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectsInfoServiceTest {

    @Mock
    private ApplicationPropertiesConfiguration.ExternalServiceProps projectsInfoServiceProps;

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApiClient apiClient;

    @Mock
    private AzureGroupsApi azureGroupsApi;

    @Mock
    private ProjectsApi projectsApi;

    @InjectMocks
    private ProjectsInfoService projectsInfoService;

    @Test
    void givenTokens_whenGetProjectGroups_thenAzureGroupsReturned() throws MalformedURLException {
        // given
        String accessToken = "access-token";
        URL baseUrl = URI.create("http://projects-info").toURL();

        List<String> expectedGroups = List.of("group-a", "group-b");

        when(projectsInfoServiceProps.getBaseRestUrl()).thenReturn(baseUrl);
        when(apiClientsBuilder.projectsInfoServiceApiClient(accessToken, baseUrl.toString()))
                .thenReturn(apiClient);
        when(apiClientsBuilder.azureGroupsApi(apiClient))
                .thenReturn(azureGroupsApi);
        when(azureGroupsApi.getAzureGroups())
                .thenReturn(expectedGroups);

        // when
        List<String> result = projectsInfoService.getProjectGroups(accessToken);

        // then
        assertThat(result).isEqualTo(expectedGroups);

        verify(apiClientsBuilder)
                .projectsInfoServiceApiClient(accessToken, baseUrl.toString());
        verify(apiClientsBuilder)
                .azureGroupsApi(apiClient);
        verify(azureGroupsApi)
                .getAzureGroups();

        verifyNoMoreInteractions(apiClientsBuilder, azureGroupsApi);
    }

    @Test
    void givenTokenAndProjectKey_whenGetProjectClusters_thenProjectInfoReturned() throws MalformedURLException {
        // given
        String accessToken = "access-token";
        String projectKey = "MY-PROJECT";
        URL baseUrl = URI.create("http://projects-info").toURL();

        ProjectInfo expectedProjectInfo = new ProjectInfo();

        when(projectsInfoServiceProps.getBaseRestUrl()).thenReturn(baseUrl);
        when(apiClientsBuilder.projectsInfoServiceApiClient(accessToken, baseUrl.toString()))
                .thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient))
                .thenReturn(projectsApi);
        when(projectsApi.getProjectClusters(projectKey))
                .thenReturn(expectedProjectInfo);

        // when
        ProjectInfo result = projectsInfoService.getProjectClusters(accessToken, projectKey);

        // then
        assertThat(result).isEqualTo(expectedProjectInfo);

        verify(apiClientsBuilder)
                .projectsInfoServiceApiClient(accessToken, baseUrl.toString());
        verify(apiClientsBuilder)
                .projectsApi(apiClient);
        verify(projectsApi)
                .getProjectClusters(projectKey);

        verifyNoMoreInteractions(apiClientsBuilder, projectsApi);
    }
}