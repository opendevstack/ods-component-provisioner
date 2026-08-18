package org.opendevstack.component_provisioner.server.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.services.ApplicationAuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProvisionResultsApiFacade {

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final ProvisionService provisionService;
    private final AuthenticationProvider authenticationProvider;
    private final ProjectsInfoService projectsInfoService;

    private final ApplicationAuthenticationProvider applicationAuthenticationProvider;

    @Value("${component-provisioner.awx.workflows.create-incident-workflow-id}")
    private String createIncidentWorkflowId;

    @Value("${component-provisioner.awx.workflows.deletion-wrapper-workflow-id}")
    private String deletionWrapperWorkflowId;

    public AwxResponse requestDeletion(
            String projectKey,
            String componentId,
            CreateIncidentAction createIncidentAction) {

        log.debug("Processing deletion. ProjectKey: {}, componentId: {}", projectKey, componentId);

        String deletionWorkflowId = getDeletionWorkflowId(projectKey, componentId);
        String deletionWorkflowName = getDeletionWorkflowName(projectKey, componentId);
        String deletionWorkflowTimeoutSeconds = getDeletionWorkflowTimeoutSeconds(projectKey, componentId);
        validate(projectKey, componentId, deletionWorkflowId, deletionWorkflowName);
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

        var triggerDeletionWrapperWorkflow = Strings.isNotBlank(deletionWorkflowId) || Strings.isNotBlank(deletionWorkflowName);
        if (triggerDeletionWrapperWorkflow) {
            addDeletionWrapperWorkflowParameters(catalogItemId, projectComponent.getComponentUrl(), deletionWorkflowId, deletionWorkflowName, deletionWorkflowTimeoutSeconds, createIncidentAction);
        }

        AwxResponse awxResponse = triggerDeletion(projectKey, componentId, triggerDeletionWrapperWorkflow, createIncidentAction);

        log.debug("AWX response: {}", awxResponse);
        return awxResponse;
    }

    public boolean isInDeletingState(ProjectComponentExtendedInfo projectComponent) {
        if (projectComponent == null) return false;
        return ProvisioningStatus.DELETING.getValue().equals(projectComponent.getStatus().getValue());
    }

    public AwxResponse triggerAwxIncidentWorkflow(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
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

    public AwxResponse triggerAwxDeletionWorkflow(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var workflowJobLaunch = buildAwxDeletionWorkflowJobLaunch(projectKey, componentId, createIncidentAction);

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
                                               ProvisioningStatus status,
                                               ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        var accessToken = applicationAuthenticationProvider.getAccessToken();
        var resolvedCatalogItemId = resolveCatalogItemId(accessToken,
                provisioningStatusUpdateRequest.getCatalogItemId(),
                provisioningStatusUpdateRequest.getCatalogItemSlug());

        provisioningStatusUpdateRequest.setCatalogItemId(resolvedCatalogItemId);
        provisioningStatusUpdateRequest.setCatalogItemSlug(null);

        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(provisioningStatusUpdateRequest);

        provisionService.notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);
    }

    public void notifyProvisioningStatusUpdatePartially(String projectKey,
                                                        ProvisioningStatus status,
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

    public void validate(String projectKey, ProvisioningStatus status, String catalogItemId, String catalogItemSlug) {
        validate(projectKey, status);

        if (StringUtils.isNotBlank(catalogItemId) && StringUtils.isNotBlank(catalogItemSlug)) {
            throw new InvalidRestEntityException("Both catalogItemId and catalogItemSlug cannot be defined at the same time.");
        }

        if (StringUtils.isBlank(catalogItemId) && StringUtils.isBlank(catalogItemSlug)) {
            throw new InvalidRestEntityException("Either catalogItemId or catalogItemSlug must be defined.");
        }
    }

    public void validate(String projectKey, ProvisioningStatus status) {
        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || status == null;

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, status are required.");
        }

        if (Arrays.stream(ProvisioningStatus.values())
                .noneMatch(e -> e.name().equals(status.getValue()))) {
            throw new InvalidRestEntityException("Status is not valid. It can only be " + Arrays.toString(ProvisioningStatus.values()));
        }
    }

    public void validate(String projectKey, String componentId, String deletionWorkflowId, String deletionWorkflowName) {
        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(componentId);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, component_id are required.");
        }

        if (StringUtils.isBlank(deletionWorkflowId) && StringUtils.isBlank(deletionWorkflowName)) {
            throw new InvalidRestEntityException("The component has no deletion_workflow nor deletion_workflow_name configured, so params is_deployed, change_number and reason are required in the request.");
        }
    }

    public void addSystemParametersToAction(String projectKey, CreateIncidentAction action) {
        addClusterLocationParameter(projectKey, action);
        addCallerParameter(action);
        addNotificationsGroupIdParameter(projectKey, action);
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

    private void addDeletionWrapperWorkflowParameters(String catalogItemId,
                                                      String componentUrl,
                                                      String customDeletionWorkflowId,
                                                      String customDeletionWorkflowName,
                                                      String deletionWorkflowTimeoutSeconds,
                                                      CreateIncidentAction action) {
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("access_token")
                .value(authenticationProvider.getAccessToken())
                .type(ParameterType.STRING.getValue())
                .build()
        );
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("catalog_item_id")
                .value(catalogItemId)
                .type(ParameterType.STRING.getValue())
                .build()
        );
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("component_url")
                .value(componentUrl)
                .type(ParameterType.STRING.getValue())
                .build()
        );
        if (Strings.isNotBlank(customDeletionWorkflowId)) {
            action.addParametersItem(CreateIncidentParameter.builder()
                    .name("deletion_workflow_id")
                    .value(customDeletionWorkflowId)
                    .type(ParameterType.STRING.getValue())
                    .build()
            );
        }
        if (Strings.isNotBlank(customDeletionWorkflowName)) {
            action.addParametersItem(CreateIncidentParameter.builder()
                    .name("deletion_workflow_name")
                    .value(customDeletionWorkflowName)
                    .type(ParameterType.STRING.getValue())
                    .build()
            );
        }
        if (Strings.isNotBlank(deletionWorkflowTimeoutSeconds)) {
            action.addParametersItem(CreateIncidentParameter.builder()
                    .name("deletion_workflow_timeout_seconds")
                    .value(deletionWorkflowTimeoutSeconds)
                    .type(ParameterType.STRING.getValue())
                    .build()
            );
        }
        var dispatchedWorkflowParams = action.getParameters().stream().map(CreateIncidentParameter::getName).collect(Collectors.toSet());
        // We need to add in a static way these parameters, so the dispatcher can send them
        // to the dispatched workflow
        dispatchedWorkflowParams.add("ods_namespace");
        dispatchedWorkflowParams.add("project_key");
        dispatchedWorkflowParams.add("cluster_location");
        dispatchedWorkflowParams.add("component_id");
        dispatchedWorkflowParams.add("is_deployed");
        dispatchedWorkflowParams.add("change_number");
        dispatchedWorkflowParams.add("reason");

        var allParams = new ArrayList<>(action.getParameters());
        var createIncidentParamDispatchedWorkflowParams = CreateIncidentParameter.builder()
                .name("dispatched_workflow_params")
                .value(dispatchedWorkflowParams)
                .type(ParameterType.MULTIPLELIST.getValue())
                .build();
        allParams.add(createIncidentParamDispatchedWorkflowParams);

        action.setParameters(allParams);
    }

    private void addCallerParameter(CreateIncidentAction action) {
        var caller = authenticationProvider.getUserPrincipalName();
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("caller")
                .type(ParameterType.STRING.getValue())
                .value(caller)
                .build());
    }

    private void addNotificationsGroupIdParameter(String projectKey, CreateIncidentAction action) {
        action.addParametersItem(CreateIncidentParameter.builder()
                .name("notifications_group_id")
                .type(ParameterType.STRING.getValue())
                .value(projectKey)
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

    public String getDeletionWorkflowId(String projectKey, String componentId) {
        return provisionService.getDeletionWorkflowId(projectKey, componentId);
    }

    public String getDeletionWorkflowName(String projectKey, String componentId) {
        return provisionService.getDeletionWorkflowName(projectKey, componentId);
    }

    public String getDeletionWorkflowTimeoutSeconds(String projectKey, String componentId) {
        return provisionService.getDeletionWorkflowTimeoutSeconds(projectKey, componentId);
    }

    private AwxWorkflowJobLaunch buildAwxWorkflowJobLaunch(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {

        addDefaultParameters(projectKey, componentId, createIncidentWorkflowId, createIncidentAction);

        return entitiesMapper.asAwxWorkflowJobLaunch(createIncidentAction);
    }

    private AwxWorkflowJobLaunch buildAwxDeletionWorkflowJobLaunch(String projectKey,
                                                                   String componentId,
                                                                   CreateIncidentAction createIncidentAction) {

        addDefaultParameters(projectKey, componentId, deletionWrapperWorkflowId, createIncidentAction);
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
                ProvisioningStatus.DELETING,
                request
        );
    }

    private AwxResponse triggerDeletion(
            String projectKey,
            String componentId,
            boolean triggerWrapperDeletionWorkflow,
            CreateIncidentAction action) {

        if (!triggerWrapperDeletionWorkflow) {
            log.debug("Workflow not found for deletion. Creating incident via AWX");
            return triggerAwxIncidentWorkflow(projectKey, componentId, action);
        }

        log.debug("Workflow found for deletion. Triggering wrapper for custom deletion workflow");
        return triggerAwxDeletionWorkflow(projectKey, componentId, action);
    }
}
