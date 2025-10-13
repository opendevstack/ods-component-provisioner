package com.boehringer.componentprovisioner.config;

import com.boehringer.componentprovisioner.server.mappers.EntitiesMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfiguration {

    @Bean
    public EntitiesMapper entitiesMapper(ObjectMapper objectMapper) {
        // Initialize the EntitiesMapper with the provided ObjectMapper
        return new EntitiesMapper(objectMapper);
    }
}
