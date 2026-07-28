package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class SizeValueNotValidException extends RuntimeException {
    public SizeValueNotValidException(String message) {
        super(message);
    }
}
