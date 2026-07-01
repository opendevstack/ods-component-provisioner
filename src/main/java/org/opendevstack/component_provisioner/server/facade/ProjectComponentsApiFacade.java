package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetail;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration.OdsApiServerServiceProps;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.mappers.ProjectComponentsMetricsMapper;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.opendevstack.component_provisioner.server.model.ProjectComponentsMetrics;
import org.opendevstack.component_provisioner.server.services.ApplicationAuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.util.JwtUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectComponentsApiFacade {

    private final AuthenticationProvider authenticationProvider;
    private final ComponentCatalogService componentCatalogService;
    private final AwxService awxService;
    private final EntitiesMapper entitiesMapper;
    private final OdsApiServerServiceProps odsApiServerServiceProps;
    private final ApplicationAuthenticationProvider applicationAuthenticationProvider;
    private final ProjectComponentsMetricsMapper projectComponentsMetricsMapper;

    public ProjectComponentExtendedInfo getProjectComponentById(String projectKey, String componentId) {
        var accessToken = authenticationProvider.getAccessToken();

        return componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId);
    }

    public ProjectComponentProvisionStatus enrichWithAapInfo(String projectKey, ProjectComponentExtendedInfo projectComponentInfo) {
        JobDetail jobDetail = null;

        if (projectComponentInfo.getStatus() != null &&
                (projectComponentInfo.getStatus().equals("FAILED") || projectComponentInfo.getStatus().equals("UNKNOWN"))
        ) {
            jobDetail = awxService.getWorkflowJobById(projectComponentInfo.getWorkflowJobId()).orElseThrow( () -> new InvalidRestEntityException(
                    String.format("Workflow job template with id %s not found for project component %s",
                            projectComponentInfo.getWorkflowJobId(), projectComponentInfo.getComponentId())
            ));
        } else {
            log.debug("Project component with id {} has status {}, skipping AWX job detail retrieval", projectComponentInfo.getComponentId(), projectComponentInfo.getStatus());
        }

        var provisionStatus = entitiesMapper.asProjectComponentProvisionStatus(projectKey, projectComponentInfo, jobDetail);

        log.debug("Generated project component provision status: {}", provisionStatus);

        return provisionStatus;
    }

    public ProjectComponentsMetrics getPaginatedProjectComponents(Integer page, Integer size) {
        String accessToken = authenticationProvider.getAccessToken();
        if (!validateTokenBelongsToOdsApiService(accessToken)) {
            throw new UserNotAllowedException("Invalid caller. Please, provide a valid token within the request.");
        }

        String marketplaceAccessToken = applicationAuthenticationProvider.getAccessToken();

        var response = componentCatalogService.getPaginatedProjectComponents(marketplaceAccessToken, page, size);
        return projectComponentsMetricsMapper.map(response);
    }

    private boolean validateTokenBelongsToOdsApiService(String accessToken) {
        var oid = JwtUtils.extractClaim(accessToken, "oid");
        return oid.map(odsApiServerServiceProps.getOid()::equals).orElse(false);
    }
}
