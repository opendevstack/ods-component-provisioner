package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProjectComponentsApi;
import org.opendevstack.component_provisioner.server.facade.ProjectComponentsApiFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProjectComponentsApiController implements ProjectComponentsApi {

    private final ProjectComponentsApiFacade projectComponentsApiFacade;

    @Override
    public ResponseEntity<Void> updateProjectComponentParameters(String projectKey, String componentId, Map<String, List<String>> requestBody) {
        // Implement the logic to update project component parameters here
        log.debug("updateProjectComponentParameters called with projectKey: {}, componentId: {}, requestBody: {}", projectKey, componentId, requestBody);

        projectComponentsApiFacade.updateProjectComponentParameters(projectKey, componentId, requestBody);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> deleteProjectComponentParameters(String projectKey, String componentId, List<String> requestBody) {
        log.debug("deleteProjectComponentParameters called with projectKey: {}, componentId: {}, requestBody: {}", projectKey, componentId, requestBody);

        projectComponentsApiFacade.deleteProjectComponentParameters(projectKey, componentId, requestBody);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
