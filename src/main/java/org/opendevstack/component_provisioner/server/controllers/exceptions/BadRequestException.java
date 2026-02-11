package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
