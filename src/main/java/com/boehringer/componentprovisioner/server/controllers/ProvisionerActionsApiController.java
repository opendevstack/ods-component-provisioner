package com.boehringer.componentprovisioner.server.controllers;

import com.boehringer.componentprovisioner.server.api.ProvisionerActionsApi;
import com.boehringer.componentprovisioner.server.mappers.EntitiesMapper;
import com.boehringer.componentprovisioner.server.model.ProvisionAction;
import com.boehringer.componentprovisioner.server.model.ProvisionActionResponse;
import com.boehringer.componentprovisioner.server.security.AuthorizationInfo;
import com.boehringer.componentprovisioner.server.services.AwxService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiController implements ProvisionerActionsApi {
    private final AuthorizationInfo authInfo;
    private AwxService awxService;
    private EntitiesMapper entitiesMapper;

    @Override
    public ResponseEntity<ProvisionActionResponse> triggerProvisionAction(ProvisionAction provisionAction) {
        log.info("User '{}' requested  triggering provisioner action: '{}'",
                authInfo.getCurrentPrincipalName(),
                provisionAction);

        var workflowJobLaunch = entitiesMapper.asAwxWorkflowJobLaunch(provisionAction);

        var result = awxService.triggerWorkflowJob(provisionAction.getId(), workflowJobLaunch);

        var awxHttpStatus = result.getLeft();
        var awxResponseBody = result.getRight()
                .map(entitiesMapper::asProvisionActionResponse)
                .orElse(null);

        return ResponseEntity
                .status(awxHttpStatus)
                .body(awxResponseBody);
    }
}
