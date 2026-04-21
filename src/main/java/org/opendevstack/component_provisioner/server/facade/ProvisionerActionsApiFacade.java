package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.OdsApiService;
import org.opendevstack.component_provisioner.server.services.PlaceholderPostProcessor;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getProjectFlavour;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getProjectKey;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.setProjectFlavour;

@Service
@Slf4j
@AllArgsConstructor
public class ProvisionerActionsApiFacade {

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final AuthenticationProvider authenticationProvider;
    private final ProjectsInfoService projectsInfoService;
    private final OdsApiService odsApiService;
    private final ProvisionerActionsApiValidator provisionerActionsApiValidator;
    private final PlaceholderPostProcessor placeholderPostProcessor;

    public AwxResponse triggerProvisionAction(ProvisionAction provisionAction) {
        log.info("Triggering provisioner action with id: '{}'", provisionAction.getId());

        var provisionActionWrapper = new ProvisionActionWrapper(provisionAction);
        var systemParametersActionWrapper = addSystemParametersToAction(provisionActionWrapper);

        provisionerActionsApiValidator.validate(systemParametersActionWrapper.toProvisionAction());
        var updateProvisionActionWithoutPlaceholdersWrapper = placeholderPostProcessor.process(systemParametersActionWrapper);
        notifyComponentCatalogProvisionStarts(updateProvisionActionWithoutPlaceholdersWrapper);

        var updatedProvisionActionWithOdsApiParametersWrapper = replaceProvisioningParametersFromOdsApi(updateProvisionActionWithoutPlaceholdersWrapper);
        var awxResponse = requestProvisionToAwx(updatedProvisionActionWithOdsApiParametersWrapper.toProvisionAction());

        log.debug("Triggered provisioner action with id: '{}'. Response : '{}'", provisionAction.getId(), awxResponse);

        return awxResponse;
    }

    public AwxResponse requestProvisionToAwx(ProvisionAction provisionAction) {
        log.debug("Triggering AWX workflow job for provision action with id: {}", provisionAction.getId());

        var workflowJobLaunch = buildAwxWorkflowJobLaunch(provisionAction);

        var result = awxService.triggerWorkflowJob(provisionAction.getId(), workflowJobLaunch);

        var awxHttpStatus = result.getLeft();
        var awxResponseBody = result.getRight()
                .map(entitiesMapper::asProvisionActionResponse)
                .orElse(null);

        return AwxResponse.builder()
                .httpStatusCode(awxHttpStatus)
                .awxResponseBody(awxResponseBody)
                .build();
    }

    public void notifyComponentCatalogProvisionStarts(ProvisionActionWrapper provisionActionWrapper) {
        var projectKey = provisionActionWrapper.getProjectKey();

        log.debug("Notifying component catalog about starting provision for project {} and action with id: {}", projectKey, provisionActionWrapper.getProvisionActionId());

        var componentId = provisionActionWrapper.getComponentId();
        var catalogItemId = provisionActionWrapper.getCatalogItemId();
        var componentUrl = provisionActionWrapper.getComponentUrl();
        var accessToken = provisionActionWrapper.getAccessToken();

        var parameters = provisionActionWrapper.getParametersMap().values().stream()
                .collect(java.util.stream.Collectors.toMap(
                        ProvisionActionParameter::getName,
                        p -> {
                            Object val = p.getValue();
                            if (val == null) {
                                return List.of("");
                            }
                            if (val instanceof List<?> list) {
                                return list.stream().map(Object::toString).toList();
                            }
                            return List.of(val.toString());
                        }
                ));

        componentCatalogService.notifyComponentCatalogProvisionStarts(projectKey, componentId, catalogItemId, componentUrl, accessToken, parameters);
    }

    private String getCatalogItemId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "catalog_item_id");
    }

    private String getComponentId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_id");
    }

    private String getComponentUrl(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_url");
    }

    private String getAccessToken(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "access_token");
    }

    private String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

    public ProvisionActionWrapper addSystemParametersToAction(ProvisionActionWrapper provisionActionWrapper) {
        var locationProvisionWrapper = addClusterLocationToAction(provisionActionWrapper);
        var callerProvisionWrapper = addCallerToAction(locationProvisionWrapper);
        var bearerTokenWrapper = addBearerTokenToActions(callerProvisionWrapper);

        log.debug("Added system parameters to provision action: '{}'", bearerTokenWrapper);

        return bearerTokenWrapper;
    }

    private ProvisionActionWrapper addCallerToAction(ProvisionActionWrapper provisionActionWrapper) {
        var caller = authenticationProvider.getUserPrincipalName();

        log.debug("Adding caller parameter with value: {}", caller);
        var callerParameter = ProvisionActionParameter.builder()
                .name("caller")
                .value(caller)
                .type(ParameterType.STRING.getValue())
                .build();

        return provisionActionWrapper.cloneWithParameter(callerParameter);
    }

    private ProvisionActionWrapper addClusterLocationToAction(ProvisionActionWrapper provisionActionWrapper) {
        var projectKey = provisionActionWrapper.getProjectKey();
        var accessToken = authenticationProvider.getAccessToken();

        log.debug("Fetching cluster location for project: {}", projectKey);
        var clusters = projectsInfoService.getProjectClusters(accessToken, projectKey).getClusters();
        if (clusters.isEmpty()) {
            throw new ProjectConfigurationException("Cannot retrieve the current project location for project: " + projectKey);
        }
        var clusterLocation = clusters.getFirst();

        log.debug("Adding cluster_location parameter with value: {}", clusterLocation);
        var locationParameter = ProvisionActionParameter.builder()
                .name("cluster_location")
                .value(clusterLocation)
                .type(ParameterType.STRING.getValue())
                .build();

        return provisionActionWrapper.cloneWithParameter(locationParameter);
    }

    private ProvisionActionWrapper addBearerTokenToActions(ProvisionActionWrapper provisionActionWrapper) {
        var bearerTokenParameter = ProvisionActionParameter.builder()
                .name("access_token")
                .value(authenticationProvider.getAccessToken())
                .type(ParameterType.STRING.getValue())
                .build();

        return provisionActionWrapper.cloneWithParameter(bearerTokenParameter);
    }

    private AwxWorkflowJobLaunch buildAwxWorkflowJobLaunch(ProvisionAction provisionAction) {
        log.debug("Setting action_id parameter to: {}", provisionAction.getId());

        var parameterItem = ProvisionActionParameter.builder()
                .name("action_id")
                .type(ParameterType.STRING.getValue())
                .value(provisionAction.getId())
                .build();

        var updatedProvisionAction = addParametersItem(provisionAction, parameterItem);

        return entitiesMapper.asAwxWorkflowJobLaunch(updatedProvisionAction);
    }

    // In order to be safe, we create a new ProvisionAction instance with the additional parameter instead of modifying the existing one (which might be immutable or shared).
    private ProvisionAction addParametersItem(ProvisionAction provisionAction, ProvisionActionParameter parameterItem) {
        return Optional.ofNullable(parameterItem)
                .map(item -> {
                    var newParameters = provisionAction.getParameters() == null
                            ? List.of(item)
                            : java.util.stream.Stream.concat(provisionAction.getParameters().stream(), java.util.stream.Stream.of(item))
                            .toList();

                    return provisionAction.toBuilder()
                            .id(provisionAction.getId())
                            .parameters(newParameters)
                            .build();
                })
        .orElse(provisionAction);
    }

    public ProvisionActionWrapper replaceProvisioningParametersFromOdsApi(ProvisionActionWrapper provisionActionWrapper) {
        var projectKey = provisionActionWrapper.getProjectKey();
        var accessToken = authenticationProvider.getAccessToken();

        var projectKeyData = odsApiService.getProject(accessToken, projectKey);

        //TODO: get it from configuration, but at the moment, let's try with hardcoded value
        var originalProjectFlavour = provisionActionWrapper.getProjectFlavour();
        var odsProjectFlavour = projectKeyData.getProjectFlavor();

        var overridedProjectFlavour = overrideOriginalValueWithOdsApiValue(originalProjectFlavour, odsProjectFlavour);

        // TODO: Get proper parametersMap with new projectFlavour
        //setProjectFlavour(provisionAction, overridedProjectFlavour);

        return new ProvisionActionWrapper(provisionActionWrapper.getProvisionActionId(), provisionActionWrapper.getParametersMap());
    }

    private String overrideOriginalValueWithOdsApiValue(String originalValue, String odsApiValue) {
        if (Strings.isBlank(odsApiValue)) {
            return originalValue;
        } else {
            return odsApiValue;
        }
    }
}
