package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames={ApplicationPropertiesConfiguration.ProjectsInfoServicesCacheProps.CACHE_NAME})
@AllArgsConstructor
@Slf4j
public class ProjectsInfoService {

    @Qualifier("projectsInfoServiceConfig")
    private final ApplicationPropertiesConfiguration.ExternalServiceProps projectsInfoServiceProps;

    private ApiClientsBuilder apiClientsBuilder;

    @Cacheable
    public List<String> getProjectGroups(String idToken, String accessToken) {
        var apiClient = apiClientsBuilder.projectsInfoServiceApiClient(idToken, projectsInfoServiceProps.getBaseRestUrl().toString());
        var azureGroupsApi = apiClientsBuilder.azureGroupsApi(apiClient);

        return azureGroupsApi.getAzureGroups(accessToken);
    }

}
