package org.opendevstack.component_provisioner.server.facade;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.*;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Objects;

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
    private String workflowId;

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

    public AwxResponse requestDeletion(
            String projectKey,
            String componentId,
            CreateIncidentAction createIncidentAction) {

        log.debug("Processing deletion. ProjectKey: {}, componentId: {}", projectKey, componentId);

        String deletionWorkflow = getDeletionWorkflow(projectKey, componentId);
        validate(projectKey, componentId, deletionWorkflow, createIncidentAction);
        addSystemParametersToAction(projectKey, createIncidentAction);

        var accessToken = authenticationProvider.getAccessToken();
        var projectComponent = componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId);

        if (isInDeletingState(projectComponent)) {
            log.debug("Project component already in DELETING state, skipping AWX call");
            return AwxResponse.builder()
                    .httpStatusCode(HttpStatus.OK)
                    .awxResponseBody(ProvisionActionResponse
                            .builder()
                            .build())
                    .build();
        }

        var catalogItemId = provisionService.composeCatalogItemId(projectComponent);
        setDeletingState(projectKey, componentId, catalogItemId);

        AwxResponse awxResponse = triggerDeletion(projectKey, componentId, deletionWorkflow, createIncidentAction);

        log.debug("AWX response: {}", awxResponse);
        return awxResponse;
    }

    public boolean isInDeletingState(ProjectComponentExtendedInfo projectComponent) {
        if (projectComponent == null) return false;
        return ProjectComponentStatus.DELETING.name().equals(projectComponent.getStatus());
    }

    public AwxResponse triggerAwxWorkflow(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var workflowJobLaunch = buildAwxWorkflowJobLaunch(projectKey, componentId, createIncidentAction);

        var result = awxService.triggerWorkflowJob(ActionType.CREATE_INCIDENT.getValue(), workflowJobLaunch);

        var awxHttpStatus = result.getLeft();
        var awxResponseBody = result.getRight()
                .map(entitiesMapper::asProvisionActionResponse)
                .orElse(null);

        return AwxResponse.builder()
                .httpStatusCode(awxHttpStatus)
                .awxResponseBody(awxResponseBody)
                .build();
    }

    public AwxResponse triggerAwxDeletionWorkflow(String projectKey, String componentId, String deletionWorkflow, CreateIncidentAction createIncidentAction) {
        var workflowJobLaunch = buildAwxDeletionWorkflowJobLaunch(projectKey, componentId, deletionWorkflow, createIncidentAction);

        var result = awxService.triggerWorkflowJob(ActionType.DELETE.getValue(), workflowJobLaunch);

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
                                               ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        var accessToken = authenticationProvider.getAccessToken();
        var resolvedCatalogItemId = resolveCatalogItemId(accessToken,
                provisioningStatusUpdateRequest.getCatalogItemId(),
                provisioningStatusUpdateRequest.getCatalogItemSlug());

        provisioningStatusUpdateRequest.setCatalogItemId(resolvedCatalogItemId);
        provisioningStatusUpdateRequest.setCatalogItemSlug(null);

        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(provisioningStatusUpdateRequest);

        provisionService.notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);
    }

    public void notifyProvisioningStatusUpdatePartially(String projectKey,
                                                        ProjectComponentStatus status,
                                                        ProvisioningStatusPartialUpdateRequest provisioningStatusPartialUpdateRequest) {
        var accessToken = authenticationProvider.getAccessToken();
        String resolvedCatalogItemId = resolveCatalogItemId(accessToken,
                provisioningStatusPartialUpdateRequest.getCatalogItemId(),
                provisioningStatusPartialUpdateRequest.getCatalogItemSlug());

        provisioningStatusPartialUpdateRequest.setCatalogItemId(resolvedCatalogItemId);
        provisioningStatusPartialUpdateRequest.setCatalogItemSlug(null);

        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(provisioningStatusPartialUpdateRequest);

        provisionService.notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);
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

    public void deleteProvisioningStatus(String projectKey, String componentId) {

        provisionService.deleteProvisioningStatus(projectKey, componentId);
    }


    public void validate(String projectKey, String status, String catalogItemId, String catalogItemSlug) {
        validate(projectKey, status);

        if (StringUtils.isNotBlank(catalogItemId) && StringUtils.isNotBlank(catalogItemSlug)) {
            throw new InvalidRestEntityException("Both catalogItemId and catalogItemSlug cannot be defined at the same time.");
        }

        if (StringUtils.isBlank(catalogItemId) && StringUtils.isBlank(catalogItemSlug)) {
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

    public void validate(String projectKey, String componentId, String deletionWorkflow, CreateIncidentAction createIncidentAction) {
        var isDeployed = getParameterString(createIncidentAction, "is_deployed");
        var changeNumber = getParameterString(createIncidentAction, "change_number");
        var reason = getParameterString(createIncidentAction, "reason");

        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(componentId);
        var extraParamsAreEmtpy = StringUtils.isBlank(isDeployed)
                || StringUtils.isBlank(changeNumber) || StringUtils.isBlank(reason);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, component_id are required.");
        }

        if (StringUtils.isBlank(deletionWorkflow) && extraParamsAreEmtpy) {
            throw new InvalidRestEntityException("Without deletion_workflow, params is_deployed, change_number and reason are required.");
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

    private void addSendOnDeletionParameters(String projectKey, String componentId, CreateIncidentAction action) {
        var sendOnDeletionParameters = provisionService.getDeletionParameters(projectKey, componentId);
        sendOnDeletionParameters.stream()
                .filter(p -> action.getParameters() == null || action.getParameters().stream()
                        .filter(Objects::nonNull)
                        .noneMatch(existing -> existing.getName() != null && existing.getName().equals(p.getName())))
                .forEach(action::addParametersItem);
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
        if (createIncidentAction == null || createIncidentAction.getParameters() == null) {
            return Strings.EMPTY;
        }
        return createIncidentAction.getParameters().stream()
                .filter(parameter -> parameter != null && parameter.getName() != null && parameterName.equals(parameter.getName()))
                .map(CreateIncidentParameter::getValue)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

    public String getDeletionWorkflow(String projectKey, String componentId) {
        return provisionService.getDeletionWorkflow(projectKey, componentId);
    }

    private AwxWorkflowJobLaunch buildAwxWorkflowJobLaunch(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {

        addDefaultParameters(projectKey, componentId, workflowId, createIncidentAction);

        return entitiesMapper.asAwxWorkflowJobLaunch(createIncidentAction);
    }

    private AwxWorkflowJobLaunch buildAwxDeletionWorkflowJobLaunch(String projectKey,
                                                                   String componentId,
                                                                   String deletionWorkflow,
                                                                   CreateIncidentAction createIncidentAction) {

        addDefaultParameters(projectKey, componentId, deletionWorkflow, createIncidentAction);
        addSendOnDeletionParameters(projectKey, componentId, createIncidentAction);

        return entitiesMapper.asAwxWorkflowJobLaunch(createIncidentAction);
    }

    private static void addDefaultParameters(String projectKey, String componentId, String workflow, CreateIncidentAction createIncidentAction) {
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
                .value(workflow)
                .build();

        createIncidentAction.addParametersItem(projectKeyParameterItem);
        createIncidentAction.addParametersItem(componentIdParameterItem);
        createIncidentAction.addParametersItem(workflowParameterItem);
    }


    private void setDeletingState(String projectKey, String componentId, String catalogItemId) {
        log.debug("Setting state to DELETING");

        ProvisioningStatusPartialUpdateRequest request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);

        notifyProvisioningStatusUpdatePartially(
                projectKey,
                ProjectComponentStatus.DELETING,
                request
        );
    }

    private AwxResponse triggerDeletion(
            String projectKey,
            String componentId,
            String deletionWorkflow,
            CreateIncidentAction action) {

        if (StringUtils.isBlank(deletionWorkflow)) {
            log.debug("Workflow not found for deletion. Creating incident via AWX");
            return triggerAwxWorkflow(projectKey, componentId, action);
        }

        log.debug("Workflow found for deletion. Triggering deletion workflow");
        return triggerAwxDeletionWorkflow(
                projectKey,
                componentId,
                deletionWorkflow,
                action
        );
    }
}
