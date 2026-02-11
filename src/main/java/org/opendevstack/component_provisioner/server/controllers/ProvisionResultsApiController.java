package org.opendevstack.component_provisioner.server.controllers;

import org.opendevstack.component_provisioner.server.api.ProvisionResultsApi;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.NotifyProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@Slf4j
public class ProvisionResultsApiController implements ProvisionResultsApi {

    public static final String STRING_PARAMETER_TYPE = "string";
    private final AwxService awxService;
    private final EntitiesMapper entitiesMapper;
    private final ProvisionService provisionService;

    @Value("${component-provisioner.support.create-incident-workflow-id}")
    private String workflowJobId;

    public ProvisionResultsApiController(AwxService awxService,
                                         EntitiesMapper entitiesMapper,
                                         ProvisionService provisionService) {
        this.awxService = awxService;
        this.entitiesMapper = entitiesMapper;
        this.provisionService = provisionService;
    }

    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey, String status, NotifyProvisioningStatusUpdateRequest notifyProvisioningCompletedRequest) {
        provisionService.notifyProvisioningStatusUpdate(projectKey,
                status,
                notifyProvisioningCompletedRequest.getComponentId(),
                notifyProvisioningCompletedRequest.getCatalogItemId(),
                notifyProvisioningCompletedRequest.getComponentUrl());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProvisioningStatus(String projectKey, ProvisioningDeleteRequest provisioningDeleteRequest) {
        provisionService.deleteProvisioningStatus(projectKey, provisioningDeleteRequest.getComponentId());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProvisionActionResponse> createIncident(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        validate(projectKey, componentId, createIncidentAction);
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

    private void validate(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        var caller = getParameterString(createIncidentAction, "caller");
        var clusterLocation = getParameterString(createIncidentAction, "cluster_location");
        var isDeployed = getParameterString(createIncidentAction, "is_deployed");
        var changeNumber = getParameterString(createIncidentAction, "change_number");
        var reason = getParameterString(createIncidentAction, "reason");

        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(componentId);
        var extraParamsAreEmtpy = StringUtils.isBlank(caller) || StringUtils.isBlank(clusterLocation) || StringUtils.isBlank(isDeployed)
                || StringUtils.isBlank(changeNumber) || StringUtils.isBlank(reason);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, component_id are required.");
        }

        if (extraParamsAreEmtpy) {
            throw new InvalidRestEntityException("caller, cluster_location, is_deployed, change_number and reason are required.");
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
