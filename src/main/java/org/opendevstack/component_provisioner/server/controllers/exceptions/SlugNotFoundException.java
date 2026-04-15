package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class SlugNotFoundException extends RuntimeException {
    public SlugNotFoundException(String message) {
        super(message);
    }
}
