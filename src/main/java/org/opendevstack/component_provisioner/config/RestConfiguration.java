package org.opendevstack.component_provisioner.config;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.http.HttpClient;

@Configuration
@Slf4j
public class RestConfiguration implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        var uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        var requestFactory =
                new JdkClientHttpRequestFactory(
                        HttpClient.newHttpClient()
                );

        return restTemplateBuilder
                .requestFactory(() -> requestFactory)
                .uriTemplateHandler(uriTemplateHandler)
                .build();
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        // Add extra converters and formatters for REST controllers API parameters
        ApplicationConversionService.configure(registry);
    }
}
