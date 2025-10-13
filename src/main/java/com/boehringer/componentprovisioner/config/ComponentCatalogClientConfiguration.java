package com.boehringer.componentprovisioner.config;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ComponentCatalogClientConfiguration {

    @Bean(name = "componentCatalogApiClient")
    @Primary
    public ApiClient componentCatalogApiClient(RestTemplateBuilder restTemplateBuilder,
                                               ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps) {
        var uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        var restTemplate = restTemplateBuilder
                .uriTemplateHandler(uriTemplateHandler).build();

        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(componentCatalogServiceProps.getBaseRestUrl().toString());

        return apiClient;
    }

    @Bean(name = "itemUserActionMessagesDefinitionsApi")
    @Primary
    public CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi(
            @Qualifier("componentCatalogApiClient") ApiClient componentCatalogApiClient) {
        return new CatalogItemUserActionMessageDefinitionsApi(componentCatalogApiClient);
    }
}
