package org.opendevstack.component_provisioner.config;

import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@Slf4j
public class ProvisionerBasicAuthClientConfiguration {

    @Value("${component-provisioner.component-catalog.project-components.username}")
    private String projectComponentsUsername;
    @Value("${component-provisioner.component-catalog.project-components.password}")
    private String projectComponentsPassword;

    @Bean(name = "provisionerActionsBasicAuthApiClient")
    public ApiClient provisionerActionsBasicAuthApiClient(RestTemplateBuilder restTemplateBuilder,
                                                          ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps) {
        var uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        var restTemplate = restTemplateBuilder
                .uriTemplateHandler(uriTemplateHandler).build();

        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(componentCatalogServiceProps.getBaseRestUrl().toString());

        // basic auth
        apiClient.setUsername(projectComponentsUsername);
        apiClient.setPassword(projectComponentsPassword);

        log.trace("provisionerActionsBasicAuthApiClient created. {}",  apiClient);

        return apiClient;
    }


    @Bean(name = "provisionerActionsBasicAuthApi")
    public ProvisionerActionsApi provisionerActionsBasicAuthApi(
            @Qualifier("provisionerActionsBasicAuthApiClient") ApiClient provisionerActionsApiClient) {
        var provisionerActionsApi = new ProvisionerActionsApi(provisionerActionsApiClient);

        log.trace("provisionerActionsBasicAuthApi created. {}. ApiClient: {}", provisionerActionsApi, provisionerActionsApiClient);

        return provisionerActionsApi;
    }
}
