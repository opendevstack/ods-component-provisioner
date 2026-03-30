package org.opendevstack.component_provisioner.server.controllers;

import org.opendevstack.component_provisioner.server.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiController implements ProvisionerActionsApi {
    private final AuthorizationInfo authInfo;
    private final AwxService awxService;
    private final ComponentCatalogService componentCatalogService;
    private final EntitiesMapper entitiesMapper;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public ResponseEntity<ProvisionActionResponse> triggerProvisionAction(ProvisionAction provisionAction) {
        log.info("User '{}' requested  triggering provisioner action: '{}'",
                authInfo.getCurrentPrincipalName(),
                provisionAction);

        validate(provisionAction);
        notifyComponentCatalogProvisionStarts(provisionAction);

        var awxResponse = requestProvisionToAwx(provisionAction);

        return ResponseEntity
                .status(awxResponse.httpStatusCode())
                .body(awxResponse.awxResponseBody());
    }

    private AwxResponse requestProvisionToAwx(ProvisionAction provisionAction) {
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

    private void validate(ProvisionAction provisionAction) {
        var projectKey = getProjectKey(provisionAction);
        var accessToken = getAccessToken(provisionAction);
        var componentId = getComponentId(provisionAction);
        var idToken = authenticationProvider.getIdToken();

        if (StringUtils.isBlank(projectKey) || StringUtils.isBlank(accessToken) || StringUtils.isBlank(componentId)) {
            throw new InvalidRestEntityException("project_key, access_token, component_id are required.");
        }

        var projectComponents = componentCatalogService.getProjectComponents(projectKey, idToken, accessToken);

        var componentIdAlreadyProvisioned = projectComponents.stream()
                .filter(projectComponent -> projectComponent.getComponentId() != null)
                .anyMatch(projectComponent -> projectComponent.getComponentId().equals(componentId));

        if (componentIdAlreadyProvisioned) {
            throw new ProjectComponentAlreadyProvisionedException("This component name already exists, please choose another name.");
        }
    }

    private void notifyComponentCatalogProvisionStarts(ProvisionAction provisionAction) {
        var projectKey = getParameterString(provisionAction, "project_key");

        var componentId = getComponentId(provisionAction);
        var catalogItemId = getCatalogItemId(provisionAction);
        var componentUrl = getComponentUrl(provisionAction);

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

        componentCatalogService.notifyComponentCatalogProvisionStarts(projectKey, componentId, catalogItemId, componentUrl, parameters);
    }

    private String getCatalogItemId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "catalog_item_id");
    }

    private String getComponentId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_id");
    }

    private String getProjectKey(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "project_key");
    }

    private String getAccessToken(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "access_token");
    }

    private String getComponentUrl(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_url");
    }

    private String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
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
