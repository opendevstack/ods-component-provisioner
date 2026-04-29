package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectComponentsApiFacade {

    private final AuthenticationProvider authenticationProvider;
    private final ComponentCatalogService componentCatalogService;
    private final AwxService awxService;
    private final EntitiesMapper entitiesMapper;

    public ProjectComponentExtendedInfo getProjectComponentById(String projectKey, String componentId) {
        var accessToken = authenticationProvider.getAccessToken();

        return componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId);
    }

    public ProjectComponentProvisionStatus enrichWithAapInfo(String projectKey, ProjectComponentExtendedInfo projectComponentInfo) {
        var workflowJobTemplate = awxService.getWorkflowJobById(projectComponentInfo.getWorkflowJobId()).orElseThrow( () -> new InvalidRestEntityException(
                String.format("Workflow job template with id %s not found for project component %s",
                        projectComponentInfo.getWorkflowJobId(), projectComponentInfo.getComponentId())
        ));

        var provisionStatus = entitiesMapper.asProjectComponentProvisionStatus(projectKey, projectComponentInfo, workflowJobTemplate);

        log.debug("Generated project component provision status: {}", provisionStatus);

        return provisionStatus;
    }
}
