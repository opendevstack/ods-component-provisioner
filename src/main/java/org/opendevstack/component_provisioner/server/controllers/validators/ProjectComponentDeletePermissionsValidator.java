package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectComponentDeletePermissionsValidator {

    public void validate(ProjectComponentExtendedInfo projectComponentExtendedInfo) {
        log.debug("Validating user has permissions to delete. ProjectComponentExtendedInfo: {}", projectComponentExtendedInfo);
        if (!Boolean.TRUE.equals(projectComponentExtendedInfo.getCanBeDeleted())) {
            throw new UserNotAllowedException("User has no permissions to delete this project component");
        }
    }
}
