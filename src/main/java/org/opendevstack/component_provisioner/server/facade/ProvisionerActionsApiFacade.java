package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProvisionerActionsApiFacade {

    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final AuthenticationProvider authenticationProvider;
    private final ProjectsInfoService projectsInfoService;


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

    public void notifyComponentCatalogProvisionStarts(ProvisionAction provisionAction) {
        var projectKey = getParameterString(provisionAction, "project_key");

        log.debug("Notifying component catalog about starting provision for project {} and action with id: {}", projectKey, provisionAction.getId());

        var componentId = getComponentId(provisionAction);
        var catalogItemId = getCatalogItemId(provisionAction);
        var componentUrl = getComponentUrl(provisionAction);
        var accessToken = getAccessToken(provisionAction);
        var idToken = getIdToken(provisionAction);

        var parameters = provisionAction.getParameters().stream()
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

        componentCatalogService.notifyComponentCatalogProvisionStarts(projectKey, componentId, catalogItemId, componentUrl, idToken, accessToken, parameters);
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

    private String getIdToken(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "id_token");
    }

    private String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

    public void addSystemParametersToAction(ProvisionAction provisionAction) {
        addClusterLocationToAction(provisionAction);
        addCallerToAction(provisionAction);
        addIdTokenToActions(provisionAction);
    }

    private void addCallerToAction(ProvisionAction provisionAction) {
        var caller = authenticationProvider.getUserPrincipalName();

        log.debug("Adding caller parameter with value: {}", caller);
        provisionAction.addParametersItem(ProvisionActionParameter.builder()
                .name("caller")
                .value(caller)
                .type("string")
                .build());
    }

    private void addClusterLocationToAction(ProvisionAction provisionAction) {
        var projectKey = getParameterString(provisionAction, "project_key");
        var accessToken = getParameterString(provisionAction, "access_token");

        log.debug("Fetching cluster location for project: {}", projectKey);
        var clusters = projectsInfoService.getProjectClusters(accessToken, projectKey).getClusters();
        if (clusters.isEmpty()) {
            throw new ProjectConfigurationException("Cannot retrieve the current project location for project: " + projectKey);
        }
        var clusterLocation = clusters.get(0);

        log.debug("Adding cluster_location parameter with value: {}", clusterLocation);
        provisionAction.addParametersItem(ProvisionActionParameter.builder()
                .name("cluster_location")
                .value(clusterLocation)
                .type("string")
                .build());
    }

    private void addIdTokenToActions(ProvisionAction provisionAction) {
        provisionAction.addParametersItem(ProvisionActionParameter.builder()
                .name("id_token")
                .value(authenticationProvider.getIdToken())
                .type("string")
                .build()
        );
    }

    private AwxWorkflowJobLaunch buildAwxWorkflowJobLaunch(ProvisionAction provisionAction) {
        log.debug("Setting action_id parameter to: {}", provisionAction.getId());

        var parameterItem = ProvisionActionParameter.builder()
                .name("action_id")
                .type("string")
                .value(provisionAction.getId())
                .build();

        provisionAction.addParametersItem(parameterItem);

        return entitiesMapper.asAwxWorkflowJobLaunch(provisionAction);
    }
}
