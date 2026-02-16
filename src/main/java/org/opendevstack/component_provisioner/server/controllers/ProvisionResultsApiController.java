package org.opendevstack.component_provisioner.server.controllers;

import org.opendevstack.component_provisioner.server.api.ProvisionResultsApi;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.NotifyProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@Slf4j
public class ProvisionResultsApiController implements ProvisionResultsApi {

    public static final String STRING_PARAMETER_TYPE = "string";
    public static final String ACCESS_TOKEN_PARAMETER_NAME = "access_token";
    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final ProvisionService provisionService;
    private final AuthenticationProvider authenticationProvider;

    @Value("${component-provisioner.support.create-incident-workflow-id}")
    private String workflowJobId;

    public ProvisionResultsApiController(AwxService awxService,
                                         ComponentCatalogService componentCatalogService,
                                         EntitiesMapper entitiesMapper,
                                         ProvisionService provisionService, AuthenticationProvider authenticationProvider) {
        this.awxService = awxService;
        this.componentCatalogService = componentCatalogService;
        this.entitiesMapper = entitiesMapper;
        this.provisionService = provisionService;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey, String status, NotifyProvisioningStatusUpdateRequest notifyProvisioningCompletedRequest) {
        log.debug("Notifying provision status update. ProjectKey: {}, Status: {}, notifyProvisioningCompletedRequest: {}", projectKey, status, notifyProvisioningCompletedRequest);

        validate(projectKey, status);

        provisionService.notifyProvisioningStatusUpdate(projectKey,
                ProjectComponentStatus.valueOf(status),
                notifyProvisioningCompletedRequest.getComponentId(),
                notifyProvisioningCompletedRequest.getCatalogItemId(),
                notifyProvisioningCompletedRequest.getComponentUrl());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProvisioningStatus(String projectKey, ProvisioningDeleteRequest provisioningDeleteRequest) {
        log.debug("Delete provisioning status. ProjectKey: {}, provisioningDeleteRequest: {}", projectKey, provisioningDeleteRequest);

        provisionService.deleteProvisioningStatus(projectKey, provisioningDeleteRequest.getComponentId());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProvisionActionResponse> createIncident(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        log.debug("Creating incident. ProjectKey: {}, componentId: {}, CreateIncidentAction: {}", projectKey, componentId, createIncidentAction);

        var idToken = authenticationProvider.getIdToken();

        validate(projectKey, componentId, createIncidentAction);

        var isInDeletingState = isInDeletingState(projectKey, componentId, idToken, createIncidentAction);

        if (isInDeletingState) {
            log.debug("Project component already in DELETING state, skipping create of the incident via AWX");

            return  ResponseEntity.ok().build();
        } else {
            log.debug("Setting state to DELETING");
            provisionService.notifyProvisioningStatusUpdate(projectKey,
                    ProjectComponentStatus.DELETING,
                    componentId,
                    null,
                    null);

            log.debug("Creating incident via AWX");

            return createIncidentViaAwx(projectKey, componentId, createIncidentAction);
        }
    }

    private boolean isInDeletingState(String projectKey, String componentId, String idToken, CreateIncidentAction createIncidentAction) {
        var accessToken = getParameterString(createIncidentAction, ACCESS_TOKEN_PARAMETER_NAME);

        var projectComponents = componentCatalogService.getProjectComponents(projectKey, idToken, accessToken);

        return projectComponents.stream()
                .filter(component -> component.getComponentId() != null)
                .filter(component -> ProjectComponentStatus.DELETING.name().equals(component.getStatus()))
                .anyMatch(component -> component.getComponentId().equals(componentId));
    }

    private ResponseEntity<ProvisionActionResponse> createIncidentViaAwx(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var awxResponse = requestProvisionToAwx(projectKey, componentId, createIncidentAction);

        return ResponseEntity
                .status(awxResponse.httpStatusCode())
                .body(awxResponse.awxResponseBody());
    }

    private AwxResponse requestProvisionToAwx(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
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

    private AwxWorkflowJobLaunch buildAwxWorkflowJobLaunch(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        log.debug("Setting project_key parameter to: {}", projectKey);

        var projectKeyParameterItem = CreateIncidentParameter.builder()
                .name("project_key")
                .type(STRING_PARAMETER_TYPE)
                .value(projectKey)
                .build();

        var componentIdParameterItem = CreateIncidentParameter.builder()
                .name("component_id")
                .type(STRING_PARAMETER_TYPE)
                .value(componentId)
                .build();

        var workflowParameterItem = CreateIncidentParameter.builder()
                .name("workflow")
                .type(STRING_PARAMETER_TYPE)
                .value(workflowJobId)
                .build();

        createIncidentAction.addParametersItem(projectKeyParameterItem);
        createIncidentAction.addParametersItem(componentIdParameterItem);
        createIncidentAction.addParametersItem(workflowParameterItem);

        return entitiesMapper.asAwxWorkflowJobLaunch(createIncidentAction);
    }

    private void validate(String projectKey, String status) {
        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(status);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, status are required.");
        }

        if (Arrays.stream(ProjectComponentStatus.values())
                        .noneMatch(e -> e.name().equals(status))) {
            throw new InvalidRestEntityException("Status is not valid. It can only be " +  ProjectComponentStatus.valuesToString());
        }
    }

    private void validate(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var caller = getParameterString(createIncidentAction, "caller");
        var clusterLocation = getParameterString(createIncidentAction, "cluster_location");
        var isDeployed = getParameterString(createIncidentAction, "is_deployed");
        var changeNumber = getParameterString(createIncidentAction, "change_number");
        var reason = getParameterString(createIncidentAction, "reason");

        var accessToken = getParameterString(createIncidentAction, ACCESS_TOKEN_PARAMETER_NAME);

        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(componentId);
        var extraParamsAreEmtpy = StringUtils.isBlank(caller) || StringUtils.isBlank(clusterLocation) || StringUtils.isBlank(isDeployed)
                || StringUtils.isBlank(changeNumber) || StringUtils.isBlank(reason);
        var tokensAreEmpty = StringUtils.isBlank(accessToken);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, component_id are required.");
        }

        if (extraParamsAreEmtpy) {
            throw new InvalidRestEntityException("caller, cluster_location, is_deployed, change_number and reason are required.");
        }

        if (tokensAreEmpty) {
            throw new InvalidRestEntityException("id_token and access_token are required.");
        }
    }

    private String getParameterString(CreateIncidentAction createIncidentAction, String parameterName) {
        return createIncidentAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(CreateIncidentParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

}
