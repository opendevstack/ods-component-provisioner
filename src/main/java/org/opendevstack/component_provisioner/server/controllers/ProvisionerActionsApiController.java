package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
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
    private final ProvisionerActionsApiValidator provisionerActionsApiValidator;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public ResponseEntity<ProvisionActionResponse> triggerProvisionAction(ProvisionAction provisionAction) {
        log.info("User '{}' requested  triggering provisioner action: '{}'",
                authInfo.getCurrentPrincipalName(),
                provisionAction);

        addIdTokenToActions(provisionAction);

        provisionerActionsApiValidator.validate(provisionAction);
        notifyComponentCatalogProvisionStarts(provisionAction);

        var awxResponse = requestProvisionToAwx(provisionAction);

        return ResponseEntity
                .status(awxResponse.httpStatusCode())
                .body(awxResponse.awxResponseBody());
    }

    private void addIdTokenToActions(ProvisionAction provisionAction) {
        provisionAction.addParametersItem(ProvisionActionParameter.builder()
                .name("id_token")
                .value(authenticationProvider.getIdToken())
                .type("string")
                .build()
        );
    }

    private AwxResponse requestProvisionToAwx(ProvisionAction provisionAction) {
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

    private void notifyComponentCatalogProvisionStarts(ProvisionAction provisionAction) {
        var projectKey = getParameterString(provisionAction, "project_key");

        log.debug("Notifying component catalog about starting provision for project {} and action with id: {}", projectKey, provisionAction.getId());

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
