package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectComponentsApiFacade {

    private final AuthenticationProvider authenticationProvider;
    private final ComponentCatalogService componentCatalogService;

    public org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo getProjectComponentById(String projectKey, String componentId) {
        var accessToken = authenticationProvider.getAccessToken();

        return componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId);
    }

    public org.opendevstack.component_provisioner.server.model.ProjectComponentExtendedInfo enrichWithAapInfo(org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo projectComponentInfo) {
        return null;
    }
}
