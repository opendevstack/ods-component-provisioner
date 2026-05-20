package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.model.CreateProjectResponse;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j
public class OdsApiService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ApplicationPropertiesConfiguration.OdsApiServerServiceProps odsApiServerServiceProps;
    private final AzureAdTokenService azureAdTokenService;

    private final String clientId;
    private final String clientSecret;
    private final String scope;

    public OdsApiService(ApiClientsBuilder apiClientsBuilder, ApplicationPropertiesConfiguration.OdsApiServerServiceProps odsApiServerServiceProps,
                         AzureAdTokenService azureAdTokenService,
                         @Value("${component-provisioner.ods-api-service.params.client_id}") String clientId,
                         @Value("${component-provisioner.ods-api-service.params.client_secret}") String clientSecret,
                         @Value("${component-provisioner.ods-api-service.params.scope}") String scope) {
        this.apiClientsBuilder = apiClientsBuilder;
        this.odsApiServerServiceProps = odsApiServerServiceProps;
        this.azureAdTokenService = azureAdTokenService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    public CreateProjectResponse getProject(String projectKey) {
        var odsAccessToken = azureAdTokenService.getAccessToken(clientId, clientSecret, scope);
        var apiClient = apiClientsBuilder.odsApiServerApiClient(odsAccessToken, odsApiServerServiceProps.getBaseRestUrl().toString());
        var projectsApi = apiClientsBuilder.projectsApi(apiClient);

        try {
            var response = projectsApi.getProject(projectKey);

            log.debug("Received response from ODS API for project {}: {}", projectKey, response);

            return response;
        } catch (HttpClientErrorException e) {
            if (HttpStatus.FORBIDDEN == e.getStatusCode()) {
                log.error("Invalid credentials while calling ODS API for project '{}': {}", projectKey, e.getMessage());
                throw e;
            } else if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                log.warn("Project with key '{}' not found in ODS API", projectKey);
                return null;
            } else {
                log.error("Error while calling ODS API for project '{}': {}", projectKey, e.getMessage());
                throw e;
            }
        }

    }
}
