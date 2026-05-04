package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProvisionResultsApi;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.facade.ProvisionResultsApiFacade;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@Slf4j
@AllArgsConstructor
public class ProvisionResultsApiController implements ProvisionResultsApi {

    private final AuthenticationProvider authenticationProvider;
    private final ProvisionResultsApiFacade provisionResultsApiFacade;

   @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey, String status, ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Notifying provision status update. ProjectKey: {}, Status: {}, provisioningStatusUpdateRequest: {}", projectKey, status, provisioningStatusUpdateRequest);

        var accessToken = authenticationProvider.getAccessToken();

        provisionResultsApiFacade.validate(projectKey, status, provisioningStatusUpdateRequest.getCatalogItemId(), provisioningStatusUpdateRequest.getCatalogItemSlug());

        provisionResultsApiFacade.notifyProvisioningStatusUpdate(
                projectKey,
                ProjectComponentStatus.valueOf(status),
                provisioningStatusUpdateRequest,
                accessToken
        );

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdatePartially(String projectKey, String status, ProvisioningStatusPartialUpdateRequest provisioningStatusPartialUpdateRequest) {
        log.debug("Notifying provision status update partially. ProjectKey: {}, Status: {}, provisioningStatusPartialUpdateRequest: {}", projectKey, status, provisioningStatusPartialUpdateRequest);

        var accessToken = authenticationProvider.getAccessToken();

        provisionResultsApiFacade.validate(projectKey, status, provisioningStatusPartialUpdateRequest.getCatalogItemId(), provisioningStatusPartialUpdateRequest.getCatalogItemSlug());

        provisionResultsApiFacade.notifyProvisioningStatusUpdatePartially(
                projectKey,
                ProjectComponentStatus.valueOf(status),
                provisioningStatusPartialUpdateRequest,
                accessToken
        );

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProvisioningStatus(String projectKey, ProvisioningDeleteRequest provisioningDeleteRequest) {
        log.debug("Delete provisioning status. ProjectKey: {}, provisioningDeleteRequest: {}", projectKey, provisioningDeleteRequest);

        var accessToken = authenticationProvider.getAccessToken();

        provisionResultsApiFacade.deleteProvisioningStatus(projectKey, provisioningDeleteRequest.getComponentId(), accessToken);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProvisionActionResponse> createIncident(String projectKey, String componentId, CreateIncidentAction createIncidentAction) {
        log.debug("Creating incident. ProjectKey: {}, componentId: {}, CreateIncidentAction: {}", projectKey, componentId, createIncidentAction);
        ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest = new ProvisioningStatusUpdateRequest();
        provisioningStatusUpdateRequest.setComponentId(componentId);
        var accessToken = authenticationProvider.getAccessToken();

        provisionResultsApiFacade.validate(projectKey, componentId, createIncidentAction);
        provisionResultsApiFacade.addSystemParametersToAction(projectKey, createIncidentAction);

        var isInDeletingState = provisionResultsApiFacade.isInDeletingState(projectKey, componentId, accessToken);

        if (isInDeletingState) {
            log.debug("Project component already in DELETING state, skipping create of the incident via AWX");

            return ResponseEntity.ok().build();
        } else {
            log.debug("Setting state to DELETING");

            provisionResultsApiFacade.notifyProvisioningStatusUpdate(projectKey,
                    ProjectComponentStatus.DELETING,
                    provisioningStatusUpdateRequest,
                    accessToken);

            log.debug("Creating incident via AWX");

            var awxResponse = provisionResultsApiFacade.requestProvisionToAwx(projectKey, componentId, createIncidentAction);

            return ResponseEntity
                    .status(awxResponse.httpStatusCode())
                    .body(awxResponse.awxResponseBody());
        }
    }

}
