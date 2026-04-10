package org.opendevstack.component_provisioner.server.controllers;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProvisionResultsApi;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.facade.ProvisionResultsApiFacade;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.NotifyProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@Slf4j
public class ProvisionResultsApiController implements ProvisionResultsApi {

    private final ProvisionService provisionService;
    private final AuthenticationProvider authenticationProvider;
    private final ProvisionResultsApiFacade provisionResultsApiFacade;

    public ProvisionResultsApiController(ProvisionService provisionService,
                                         AuthenticationProvider authenticationProvider,
                                         ProvisionResultsApiFacade provisionResultsApiFacade) {
        this.provisionService = provisionService;
        this.authenticationProvider = authenticationProvider;
        this.provisionResultsApiFacade = provisionResultsApiFacade;
    }

    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey, String status, NotifyProvisioningStatusUpdateRequest notifyProvisioningCompletedRequest) {
        log.debug("Notifying provision status update. ProjectKey: {}, Status: {}, notifyProvisioningCompletedRequest: {}", projectKey, status, notifyProvisioningCompletedRequest);

        provisionResultsApiFacade.validate(projectKey, status);

        provisionService.notifyProvisioningStatusUpdate(projectKey,
                ProjectComponentStatus.valueOf(status),
                notifyProvisioningCompletedRequest.getComponentId(),
                notifyProvisioningCompletedRequest.getCatalogItemId(),
                notifyProvisioningCompletedRequest.getComponentUrl(),
                authenticationProvider.getIdToken(),
                notifyProvisioningCompletedRequest.getAccessToken());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProvisioningStatus(String projectKey, ProvisioningDeleteRequest provisioningDeleteRequest) {
        log.debug("Delete provisioning status. ProjectKey: {}, provisioningDeleteRequest: {}", projectKey, provisioningDeleteRequest);

        provisionService.deleteProvisioningStatus(projectKey, provisioningDeleteRequest.getComponentId(), authenticationProvider.getIdToken());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProvisionActionResponse> createIncident(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        log.debug("Creating incident. ProjectKey: {}, componentId: {}, CreateIncidentAction: {}", projectKey, componentId, createIncidentAction);

        var idToken = authenticationProvider.getIdToken();

        provisionResultsApiFacade.validate(projectKey, componentId, createIncidentAction);

        var isInDeletingState = provisionResultsApiFacade.isInDeletingState(projectKey, componentId, idToken, createIncidentAction);

        if (isInDeletingState) {
            log.debug("Project component already in DELETING state, skipping create of the incident via AWX");

            return ResponseEntity.ok().build();
        } else {
            log.debug("Setting state to DELETING");
            provisionService.notifyProvisioningStatusUpdate(projectKey,
                    ProjectComponentStatus.DELETING,
                    componentId,
                    null,
                    null,
                    authenticationProvider.getIdToken(),
                    null);

            log.debug("Creating incident via AWX");

            var awxResponse = provisionResultsApiFacade.requestProvisionToAwx(projectKey, componentId, createIncidentAction);

            return ResponseEntity
                    .status(awxResponse.httpStatusCode())
                    .body(awxResponse.awxResponseBody());
        }
    }

}
