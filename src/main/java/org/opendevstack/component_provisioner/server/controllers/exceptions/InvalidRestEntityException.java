package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class InvalidRestEntityException extends RuntimeException {

    public InvalidRestEntityException(String message) {
        super(message);
    }

}
