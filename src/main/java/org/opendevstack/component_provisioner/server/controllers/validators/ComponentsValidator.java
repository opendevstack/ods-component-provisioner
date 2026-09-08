package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.stereotype.Service;

import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getComponentId;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getProjectKey;

/**
 * Validates that a component is not already provisioned for a given project.
 */
@AllArgsConstructor
@Service
@Slf4j
public class ComponentsValidator {

    private final AuthenticationProvider authenticationProvider;
    private final ComponentCatalogService componentCatalogService;

    public void validate(ProvisionAction provisionAction) {
        var projectKey = getProjectKey(provisionAction);
        var componentId = getComponentId(provisionAction);

        log.debug("Validating component is not provisioned. projectKey: {}, componentId: {}", projectKey, componentId);
        var accessToken = authenticationProvider.getAccessToken();
        var projectComponents = componentCatalogService.getProjectComponents(accessToken, projectKey);

        var componentIdAlreadyProvisioned = projectComponents.stream()
                .filter(projectComponent -> projectComponent.getComponentId() != null)
                .anyMatch(projectComponent -> projectComponent.getComponentId().equals(componentId));

        if (componentIdAlreadyProvisioned) {
            throw new ProjectComponentAlreadyProvisionedException("This component name already exists, please choose another name.");
        }
    }
}
