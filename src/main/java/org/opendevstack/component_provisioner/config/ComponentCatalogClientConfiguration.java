package org.opendevstack.component_provisioner.config;

import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProjectComponentsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@Slf4j
public class ComponentCatalogClientConfiguration {

    @Bean(name = "componentCatalogApiClient")
    public ApiClient componentCatalogApiClient(RestTemplateBuilder restTemplateBuilder,
                                               ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps) {
        var uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        var restTemplate = restTemplateBuilder
                .uriTemplateHandler(uriTemplateHandler).build();

        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(componentCatalogServiceProps.getBaseRestUrl().toString());

        log.trace("componentCatalogApiClient created. {}", apiClient);

        return apiClient;
    }

    @Bean(name = "itemUserActionMessagesDefinitionsApi")
    public CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi(
            @Qualifier("componentCatalogApiClient") ApiClient componentCatalogApiClient) {
        var itemUserActionMessagesDefinitionsApi = new CatalogItemUserActionMessageDefinitionsApi(componentCatalogApiClient);

        log.trace("itemUserActionMessagesDefinitionsApi created. {}. ApiClient: {}.", itemUserActionMessagesDefinitionsApi, componentCatalogApiClient);

        return itemUserActionMessagesDefinitionsApi;
    }

    @Bean(name = "projectComponentsApi")
    public ProjectComponentsApi projectComponentsApi(
            @Qualifier("componentCatalogApiClient") ApiClient componentCatalogApiClient
    ) {
        var projectComponentsApi = new ProjectComponentsApi(componentCatalogApiClient);

        log.trace("ProjectComponentsApi created {}. ApiClient {}.", projectComponentsApi, projectComponentsApi);

        return projectComponentsApi;
    }
}
