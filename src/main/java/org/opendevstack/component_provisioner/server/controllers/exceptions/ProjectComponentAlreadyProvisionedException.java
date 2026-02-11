package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class ProjectComponentAlreadyProvisionedException extends RuntimeException {
    public ProjectComponentAlreadyProvisionedException(String message) {
        super(message);
    }
}
