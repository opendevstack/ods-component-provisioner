package org.opendevstack.component_provisioner.server.facade;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.NotifyProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.services.*;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;

@Service
@Slf4j
public class ProvisionResultsApiFacade {

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final ProvisionService provisionService;
    private final AuthenticationProvider authenticationProvider;
    private final ProjectsInfoService projectsInfoService;


    @Value("${component-provisioner.support.create-incident-workflow-id:WORKFLOW}")
    private String workflowJobId;

    public ProvisionResultsApiFacade(AwxService awxService,
                                     ComponentCatalogService componentCatalogService,
                                     EntitiesMapper entitiesMapper,
                                     ProvisionService provisionService,
                                     AuthenticationProvider authenticationProvider,
                                     ProjectsInfoService projectsInfoService) {
        this.awxService = awxService;
        this.componentCatalogService = componentCatalogService;
        this.entitiesMapper = entitiesMapper;
        this.provisionService = provisionService;
        this.authenticationProvider = authenticationProvider;
        this.projectsInfoService = projectsInfoService;
    }

    public boolean isInDeletingState(String projectKey, String componentId, String accessToken) {

        var projectComponents = componentCatalogService.getProjectComponents(projectKey, accessToken);

        return projectComponents.stream()
                .filter(component -> component.getComponentId() != null)
                .filter(component -> ProjectComponentStatus.DELETING.name().equals(component.getStatus()))
                .anyMatch(component -> component.getComponentId().equals(componentId));
    }

    public AwxResponse requestProvisionToAwx(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var workflowJobLaunch = buildAwxWorkflowJobLaunch(projectKey, componentId, createIncidentAction);

        var result = awxService.triggerWorkflowJob("CREATE_INCIDENT", workflowJobLaunch);

        var awxHttpStatus = result.getLeft();
        var awxResponseBody = result.getRight()
                .map(entitiesMapper::asProvisionActionResponse)
                .orElse(null);

        return AwxResponse.builder()
                .httpStatusCode(awxHttpStatus)
                .awxResponseBody(awxResponseBody)
                .build();
    }

    public void notifyProvisioningStatusUpdate(String projectKey,
                                               ProjectComponentStatus status,
                                               NotifyProvisioningStatusUpdateRequest notifyProvisioningStatusUpdateRequest,
                                               String accessToken) {
        String resolvedCatalogItemId = resolveCatalogItemId(accessToken,
                notifyProvisioningStatusUpdateRequest.getCatalogItemId(),
                notifyProvisioningStatusUpdateRequest.getCatalogItemSlug());

        provisionService.notifyProvisioningStatusUpdate(projectKey,
                status,
                notifyProvisioningStatusUpdateRequest.getComponentId(),
                resolvedCatalogItemId,
                notifyProvisioningStatusUpdateRequest.getComponentUrl(),
                accessToken);
    }

    private String resolveCatalogItemId(String accessToken,
                                        String catalogItemId,
                                        String catalogItemSlug) {
        String resolvedCatalogItemId = catalogItemId;
        if (StringUtils.isNotBlank(catalogItemSlug) && StringUtils.isBlank(catalogItemId)) {
            log.debug("Resolving catalogItemId for catalogItemSlug: {}", catalogItemSlug);
            CatalogItem catalogItem;
            try {
                catalogItem = componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug);
            } catch (RestClientException e) {
                throw new SlugNotFoundException("Catalog item slug not found: " + catalogItemSlug);
            }
            resolvedCatalogItemId = catalogItem.getId();
            log.debug("Resolved catalogItemSlug {} to catalogItemId: {}", catalogItemSlug, resolvedCatalogItemId);
        }
        return resolvedCatalogItemId;
    }

    public void deleteProvisioningStatus(String projectKey, String componentId, String accessToken) {
        var projectComponents = componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId, accessToken);

        provisionService.deleteProvisioningStatus(projectKey, componentId, projectComponents, accessToken);
    }


    public void validate(String projectKey, String status, NotifyProvisioningStatusUpdateRequest request) {
        validate(projectKey, status);
        if (StringUtils.isNotBlank(request.getCatalogItemId()) && StringUtils.isNotBlank(request.getCatalogItemSlug())) {
            throw new InvalidRestEntityException("Both catalogItemId and catalogItemSlug cannot be defined at the same time.");
        }

        if (StringUtils.isBlank(request.getCatalogItemId()) && StringUtils.isBlank(request.getCatalogItemSlug())) {
            throw new InvalidRestEntityException("Either catalogItemId or catalogItemSlug must be defined.");
        }
    }

    public void validate(String projectKey, String status) {
        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(status);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, status are required.");
        }

        if (Arrays.stream(ProjectComponentStatus.values())
                .noneMatch(e -> e.name().equals(status))) {
            throw new InvalidRestEntityException("Status is not valid. It can only be " + ProjectComponentStatus.valuesToString());
        }
    }

    public void validate(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var isDeployed = getParameterString(createIncidentAction, "is_deployed");
        var changeNumber = getParameterString(createIncidentAction, "change_number");
        var reason = getParameterString(createIncidentAction, "reason");

        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(componentId);
        var extraParamsAreEmtpy = StringUtils.isBlank(isDeployed)
                || StringUtils.isBlank(changeNumber) || StringUtils.isBlank(reason);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, component_id are required.");
        }

        if (extraParamsAreEmtpy) {
            throw new InvalidRestEntityException("is_deployed, change_number and reason are required.");
        }
    }

    public void addSystemParametersToAction(String projectKey, CreateIncidentAction action) {
        addClusterLocationParameter(projectKey, action);
        addCallerParameter(action);
    }

    private void addClusterLocationParameter(String projectKey, CreateIncidentAction action) {
        var accessToken = authenticationProvider.getAccessToken();
        var clusters = projectsInfoService.getProjectClusters(accessToken, projectKey).getClusters();
        if (clusters.isEmpty()) {
            throw new ProjectConfigurationException("Cannot retrieve the current project location for project: " + projectKey);
        }
        var clusterLocation = clusters.getFirst();
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("cluster_location")
                .type(ParameterType.STRING.getValue())
                .value(clusterLocation)
                .build());
    }

    private void addCallerParameter(CreateIncidentAction action) {
        var caller = authenticationProvider.getUserPrincipalName();
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("caller")
                .type(ParameterType.STRING.getValue())
                .value(caller)
                .build());
    }

    public String getParameterString(CreateIncidentAction createIncidentAction, String parameterName) {
        return createIncidentAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(CreateIncidentParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

    private AwxWorkflowJobLaunch buildAwxWorkflowJobLaunch(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        log.debug("Setting project_key parameter to: {}", projectKey);

        var projectKeyParameterItem = CreateIncidentParameter.builder()
                .name("project_key")
                .type(ParameterType.STRING.getValue())
                .value(projectKey)
                .build();

        var componentIdParameterItem = CreateIncidentParameter.builder()
                .name("component_id")
                .type(ParameterType.STRING.getValue())
                .value(componentId)
                .build();

        var workflowParameterItem = CreateIncidentParameter.builder()
                .name("workflow")
                .type(ParameterType.STRING.getValue())
                .value(workflowJobId)
                .build();

        createIncidentAction.addParametersItem(projectKeyParameterItem);
        createIncidentAction.addParametersItem(componentIdParameterItem);
        createIncidentAction.addParametersItem(workflowParameterItem);

        return entitiesMapper.asAwxWorkflowJobLaunch(createIncidentAction);
    }
}
