package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProjectComponentsStatusApi;
import org.opendevstack.component_provisioner.server.facade.ProjectComponentsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProjectComponentsApiController implements ProjectComponentsStatusApi {

    private final ProjectComponentsApiFacade projectComponentsApiFacade;

    @Override
    public ResponseEntity<ProjectComponentProvisionStatus> getProjectComponentProvisionStatusById(String projectKey, String componentId) {
        var projectComponentInfo = projectComponentsApiFacade.getProjectComponentById(projectKey, componentId);

        log.debug("getProjectComponentById - projectComponentInfo: {}", projectComponentInfo);

        var projectComponentExtendedInfo = projectComponentsApiFacade.enrichWithAapInfo(projectKey, projectComponentInfo);

        log.debug("getProjectComponentById - projectComponentExtendedInfo: {}", projectComponentExtendedInfo);

        return ResponseEntity.ok(projectComponentExtendedInfo);
    }
}
