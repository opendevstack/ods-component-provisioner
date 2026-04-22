package org.opendevstack.component_provisioner.server.facade;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.facade.exceptions.IllegalConfigurationException;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.OdsApiService;
import org.opendevstack.component_provisioner.server.services.PlaceholderPostProcessor;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.SnakeCaseExtractor;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProvisionerActionsApiFacade {

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final AuthenticationProvider authenticationProvider;
    private final ProjectsInfoService projectsInfoService;
    private final OdsApiService odsApiService;
    private final ProvisionerActionsApiValidator provisionerActionsApiValidator;
    private final PlaceholderPostProcessor placeholderPostProcessor;
    private final SnakeCaseExtractor snakeCaseExtractor;

    private final List<String> paramsToOverrideFromOdsApi;

    public ProvisionerActionsApiFacade(AwxService awxService, ComponentCatalogService componentCatalogService,
                                       EntitiesMapper entitiesMapper, AuthenticationProvider authenticationProvider,
                                       ProjectsInfoService projectsInfoService, OdsApiService odsApiService,
                                       ProvisionerActionsApiValidator provisionerActionsApiValidator,
                                       PlaceholderPostProcessor placeholderPostProcessor, SnakeCaseExtractor snakeCaseExtractor,
                                       @Value("${component-provisioner.ods-api-service.params.override}") String paramsToOverrideFromOdsApiConfig) {
        this.awxService = awxService;
        this.componentCatalogService = componentCatalogService;
        this.entitiesMapper = entitiesMapper;
        this.authenticationProvider = authenticationProvider;
        this.projectsInfoService = projectsInfoService;
        this.odsApiService = odsApiService;
        this.provisionerActionsApiValidator = provisionerActionsApiValidator;
        this.placeholderPostProcessor = placeholderPostProcessor;
        this.snakeCaseExtractor = snakeCaseExtractor;
        this.paramsToOverrideFromOdsApi = Arrays.stream(paramsToOverrideFromOdsApiConfig.split(",")).toList();
    }

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

        var projectKeyData = odsApiService.getProject(projectKey);

        if (projectKeyData == null) {
            log.warn("Project data not found in ODS API for project key: {}. Skipping overriding provisioning parameters from ODS API.", projectKey);

            return provisionActionWrapper;
        } else {
            var odsApiSnakeCaseValuesMap = snakeCaseExtractor.toSnakeCaseMap(projectKeyData);
            var parametersMap = provisionActionWrapper.getParametersMap();

            var updatedParametersMap = replaceProvisioningParametersFromOdsApi(parametersMap, odsApiSnakeCaseValuesMap);

            return new ProvisionActionWrapper(provisionActionWrapper.getProvisionActionId(), updatedParametersMap);
        }
    }

    private Map<String, ProvisionActionParameter> replaceProvisioningParametersFromOdsApi(Map<String, ProvisionActionParameter> parametersMap, Map<String, Object> odsApiSnakeCaseValuesMap) {
        Map<String, ProvisionActionParameter> updatedParameters = new HashMap<>();

        // Iterate over all parameters and set update value if required, otherwise keep the same value
        for (Map.Entry<String, ProvisionActionParameter> entry : parametersMap.entrySet()) {
            if (odsApiSnakeCaseValuesMap.containsKey(entry.getKey())) {
                log.debug("Found ods parameter at request, overriding: {}", entry.getKey());

                if (entry.getValue().getType().equals(ParameterType.STRING.getValue())) {
                    var parameter = ProvisionActionParameter.builder()
                            .name(entry.getValue().getName())
                            .type(entry.getValue().getType())
                            .value(odsApiSnakeCaseValuesMap.get(entry.getKey()).toString())
                            .build();

                    updatedParameters.put(entry.getKey(), parameter);
                } else {
                    throw new IllegalConfigurationException("Parameter " + entry.getKey() + " is not of type String. Only type string are supported for overriding from ODS API.");
                }
            } else {
                log.debug("Found parameter, but not in ods, keeping it as it is: {}", entry.getKey());

                var parameter = ProvisionActionParameter.builder()
                        .name(entry.getValue().getName())
                        .type(entry.getValue().getType())
                        .value(entry.getValue())
                        .build();

                updatedParameters.put(entry.getKey(), parameter);
            }
        }

        // If there are required ODS parameters not in the request, we add them with value from ODS API
        for (String odsApiParameterToOverride : paramsToOverrideFromOdsApi) {
            if (!parametersMap.containsKey(odsApiParameterToOverride) && odsApiSnakeCaseValuesMap.containsKey(odsApiParameterToOverride)) {
                log.debug("Adding missing parameter from ODS API: {}", odsApiParameterToOverride);

                var parameter = ProvisionActionParameter.builder()
                        .name(odsApiParameterToOverride)
                        .type(ParameterType.STRING.getValue())
                        .value(odsApiSnakeCaseValuesMap.get(odsApiParameterToOverride).toString())
                        .build();

                updatedParameters.put(odsApiParameterToOverride, parameter);
            }
        }

        return updatedParameters;
    }

}
