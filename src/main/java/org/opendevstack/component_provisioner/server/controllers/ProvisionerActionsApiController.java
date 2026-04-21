package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.PlaceholderPostProcessor;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiController implements ProvisionerActionsApi {
    private final AuthorizationInfo authInfo;
    private final ProvisionerActionsApiValidator provisionerActionsApiValidator;
    private final ProvisionerActionsApiFacade provisionerActionsApiFacade;
    private final PlaceholderPostProcessor placeholderPostProcessor;

    @Override
    public ResponseEntity<ProvisionActionResponse> triggerProvisionAction(ProvisionAction provisionAction) {
        log.info("User '{}' requested  triggering provisioner action: '{}'",
                authInfo.getCurrentPrincipalName(),
                provisionAction);

        var awxResponse = provisionerActionsApiFacade.triggerProvisionAction(provisionAction);

        return ResponseEntity
                .status(awxResponse.httpStatusCode())
                .body(awxResponse.awxResponseBody());
    }

}
