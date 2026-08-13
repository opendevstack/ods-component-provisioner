package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProvisionResultsApi;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.facade.ProvisionResultsApiFacade;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@Slf4j
@AllArgsConstructor
public class ProvisionResultsApiController implements ProvisionResultsApi {

    private final ProvisionResultsApiFacade provisionResultsApiFacade;

    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey,
                                                               ProvisioningStatus status,
                                                               ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Notifying provision status update. ProjectKey: {}, Status: {}, provisioningStatusUpdateRequest: {}",
                projectKey, status, provisioningStatusUpdateRequest);

        provisionResultsApiFacade.validate(projectKey, status, provisioningStatusUpdateRequest.getCatalogItemId(),
                provisioningStatusUpdateRequest.getCatalogItemSlug());

        provisionResultsApiFacade.notifyProvisioningStatusUpdate(
                projectKey,
                status,
                provisioningStatusUpdateRequest
        );

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdatePartially(String projectKey,
                                                                        ProvisioningStatus status,
                                                                        ProvisioningStatusPartialUpdateRequest
                                                                                provisioningStatusPartialUpdateRequest) {
        log.debug("Notifying provision status update partially. ProjectKey: {}, Status: {}, provisioningStatusPartialUpdateRequest: {}",
                projectKey, status, provisioningStatusPartialUpdateRequest);

        provisionResultsApiFacade.validate(projectKey, status, provisioningStatusPartialUpdateRequest.getCatalogItemId(),
                provisioningStatusPartialUpdateRequest.getCatalogItemSlug());

        provisionResultsApiFacade.notifyProvisioningStatusUpdatePartially(
                projectKey,
                status,
                provisioningStatusPartialUpdateRequest
        );

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProjectComponent(String projectKey,
                                                       ProvisioningDeleteRequest provisioningDeleteRequest) {
        log.debug("Delete Project component. ProjectKey: {}, provisioningDeleteRequest: {}",
                projectKey, provisioningDeleteRequest);



        provisionResultsApiFacade.deleteProvisioningStatus(projectKey,
                provisioningDeleteRequest.getComponentId());

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProvisionActionResponse> requestDeletion(
            String projectKey,
            String componentId,
            CreateIncidentAction createIncidentAction) {

        log.debug("Requesting deletion. ProjectKey: {}, componentId: {}",
                projectKey, componentId);

        AwxResponse awxResponse = provisionResultsApiFacade.requestDeletion(
                projectKey,
                componentId,
                createIncidentAction
        );

        return ResponseEntity
                .status(awxResponse.httpStatusCode())
                .body(awxResponse.awxResponseBody());
    }


}
