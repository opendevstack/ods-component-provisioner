package org.opendevstack.component_provisioner.server.facade;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class ProvisionResultsApiFacade {

    public static final String ACCESS_TOKEN_PARAMETER_NAME = "access_token";

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final ProvisionService provisionService;
    private final AuthenticationProvider authenticationProvider;


    @Value("${component-provisioner.support.create-incident-workflow-id:WORKFLOW}")
    private String workflowJobId;

    public ProvisionResultsApiFacade(AwxService awxService,
                                     ComponentCatalogService componentCatalogService,
                                     EntitiesMapper entitiesMapper,
                                     ProvisionService provisionService,
                                     AuthenticationProvider authenticationProvider) {
        this.awxService = awxService;
        this.componentCatalogService = componentCatalogService;
        this.entitiesMapper = entitiesMapper;
        this.provisionService = provisionService;
        this.authenticationProvider = authenticationProvider;
    }

    public boolean isInDeletingState(String projectKey, String componentId, String idToken, CreateIncidentAction createIncidentAction) {
        var accessToken = getParameterString(createIncidentAction, ACCESS_TOKEN_PARAMETER_NAME);

        var projectComponents = componentCatalogService.getProjectComponents(projectKey, idToken, accessToken);

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

    public void notifyProvisioningStatusUpdate(String projectKey, ProjectComponentStatus status, String componentId,
                                               String catalogItemId, String componentUrl, String accessToken) {
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl, accessToken);
    }

    public void deleteProvisioningStatus(String projectKey, String componentId, String idToken) {
        provisionService.deleteProvisioningStatus(projectKey, componentId, idToken);
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
        var caller = getParameterString(createIncidentAction, "caller");
        var clusterLocation = getParameterString(createIncidentAction, "cluster_location");
        var isDeployed = getParameterString(createIncidentAction, "is_deployed");
        var changeNumber = getParameterString(createIncidentAction, "change_number");
        var reason = getParameterString(createIncidentAction, "reason");

        var accessToken = getParameterString(createIncidentAction, ACCESS_TOKEN_PARAMETER_NAME);

        var mainParamsAreEmpty = StringUtils.isBlank(projectKey) || StringUtils.isBlank(componentId);
        var extraParamsAreEmtpy = StringUtils.isBlank(caller) || StringUtils.isBlank(clusterLocation) || StringUtils.isBlank(isDeployed)
                || StringUtils.isBlank(changeNumber) || StringUtils.isBlank(reason);
        var tokenIsEmpty = StringUtils.isBlank(accessToken);

        if (mainParamsAreEmpty) {
            throw new InvalidRestEntityException("project_key, component_id are required.");
        }

        if (extraParamsAreEmtpy) {
            throw new InvalidRestEntityException("caller, cluster_location, is_deployed, change_number and reason are required.");
        }

        if (tokenIsEmpty) {
            throw new InvalidRestEntityException("access_token is required.");
        }
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
