package com.boehringer.componentprovisioner.config;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.api.WorkflowJobTemplatesApi;
import com.boehringer.componentprovisioner.client.awx.v2.auth.HttpBasicAuth;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class AwxClientConfiguration {

    @Bean(name="awxApiClient")
    @Primary
    public ApiClient awxApiClient(RestTemplateBuilder restTemplateBuilder,
                                  ApplicationPropertiesConfiguration.AWXServiceProps awxServiceProps) {
        var uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        var restTemplate = restTemplateBuilder
                .uriTemplateHandler(uriTemplateHandler).build();

        var apiClient = new ApiClient(restTemplate);
        var auth = (HttpBasicAuth) apiClient.getAuthentication("Basic");

        auth.setUsername(awxServiceProps.getUsername());
        auth.setPassword(awxServiceProps.getPassword());

        apiClient.setBasePath(awxServiceProps.getBaseRestUrl().toString());

        return apiClient;
    }

    @Bean(name="awxWorkflowJobTemplatesApi")
    @Primary
    public WorkflowJobTemplatesApi workflowJobTemplatesApi(@Qualifier("awxApiClient") ApiClient awxApiClient) {
        return new WorkflowJobTemplatesApi(awxApiClient);
    }
}
