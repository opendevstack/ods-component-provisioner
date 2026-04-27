package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.util.JwtUtils;
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

    private static final String OID_CLAIM = "oid";

    @Qualifier("projectsInfoServiceConfig")
    private final ApplicationPropertiesConfiguration.ExternalServiceProps projectsInfoServiceProps;

    private ApiClientsBuilder apiClientsBuilder;

    @Cacheable(key = "#root.methodName + T(org.opendevstack.component_provisioner.util.util.JwtUtils).extractClaim(#accessToken, 'oid').orElse(#accessToken)")
    public List<String> getProjectGroups(String accessToken) {
        log.debug("Fetching Azure groups for token oid: {}", JwtUtils.extractClaim(accessToken, OID_CLAIM).orElse("unknown"));
        var apiClient = apiClientsBuilder.projectsInfoServiceApiClient(accessToken, projectsInfoServiceProps.getBaseRestUrl().toString());
        var azureGroupsApi = apiClientsBuilder.azureGroupsApi(apiClient);

        return azureGroupsApi.getAzureGroups();
    }

    @Cacheable(key = "#root.methodName + #projectKey")
    public ProjectInfo getProjectClusters(String accessToken, String projectKey) {
        var apiClient = apiClientsBuilder.projectsInfoServiceApiClient(accessToken, projectsInfoServiceProps.getBaseRestUrl().toString());
        var projectsApi = apiClientsBuilder.projectsApi(apiClient);

        return projectsApi.getProjectClusters(projectKey);
    }

}
