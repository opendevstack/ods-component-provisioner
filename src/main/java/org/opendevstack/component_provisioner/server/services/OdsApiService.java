package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.model.CreateProjectResponse;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OdsApiService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ApplicationPropertiesConfiguration.OdsApiServerServiceProps odsApiServerServiceProps;

    public CreateProjectResponse getProject(String accessToken, String projectKey) {
        var apiClient = apiClientsBuilder.odsApiServerApiClient(accessToken, odsApiServerServiceProps.getBaseRestUrl().toString());
        var projectsApi = apiClientsBuilder.projectsApi(apiClient);

        var response = projectsApi.getProject(projectKey);

        log.debug("Received response from ODS API for project {}: {}", projectKey, response);

        return response;
    }
}
