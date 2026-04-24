package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.springframework.web.client.RestClientException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.PlaceholderPostProcessor;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.ReplaceParametersService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiFacade {

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final AuthenticationProvider authenticationProvider;
    private final ProjectsInfoService projectsInfoService;
    private final ProvisionerActionsApiValidator provisionerActionsApiValidator;
    private final PlaceholderPostProcessor placeholderPostProcessor;
    private final ReplaceParametersService replaceParametersService;

    public AwxResponse triggerProvisionAction(ProvisionAction provisionAction) {
        log.info("Triggering provisioner action with id: '{}'", provisionAction.getId());

        var provisionActionWrapper = new ProvisionActionWrapper(provisionAction);
        var systemParametersActionWrapper = addSystemParametersToAction(provisionActionWrapper);
        var resolvedActionWrapper = resolveCatalogItemIdentifier(systemParametersActionWrapper);
        provisionerActionsApiValidator.validate(resolvedActionWrapper.toProvisionAction());
        var updateProvisionActionWithoutPlaceholdersWrapper = placeholderPostProcessor.process(resolvedActionWrapper);
        var updatedProvisionActionWithOdsApiParametersWrapper = replaceParametersService.replaceProvisioningParametersFromOdsApi(updateProvisionActionWithoutPlaceholdersWrapper);

        notifyComponentCatalogProvisionStarts(updatedProvisionActionWithOdsApiParametersWrapper);

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

    private ProvisionActionWrapper resolveCatalogItemIdentifier(ProvisionActionWrapper wrapper) {
        var catalogItemId = wrapper.getCatalogItemId();
        var catalogItemSlug = wrapper.getCatalogItemSlug();

        boolean hasId = catalogItemId != null && !catalogItemId.isBlank();
        boolean hasSlug = catalogItemSlug != null && !catalogItemSlug.isBlank();

        if (!hasId && !hasSlug) {
            throw new BadRequestException("Either catalog_item_id or catalog_item_slug must be provided");
        }
        if (hasId && hasSlug) {
            throw new BadRequestException("Only one of catalog_item_id or catalog_item_slug must be provided, not both");
        }
        if (hasId) {
            return wrapper;
        }

        // Only catalog_item_slug provided: resolve to catalog_item_id
        log.debug("Resolving catalog_item_id for catalog_item_slug: {}", catalogItemSlug);
        var accessToken = wrapper.getAccessToken();
        CatalogItem catalogItem;
        try {
            catalogItem = componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug);
        } catch (RestClientException e) {
            throw new SlugNotFoundException("Catalog item slug not found: " + catalogItemSlug);
        }
        var resolvedId = catalogItem.getId();
        log.debug("Resolved catalog_item_slug {} to catalog_item_id: {}", catalogItemSlug, resolvedId);

        var catalogItemIdParameterItem = ProvisionActionParameter.builder()
                .name("catalog_item_id")
                .value(resolvedId)
                .type(ParameterType.STRING.getValue())
                .build();

        return wrapper.cloneWithoutParameter("catalog_item_slug").cloneWithParameter(catalogItemIdParameterItem);
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
}
