package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class PageValueNotValidException extends RuntimeException {
    public PageValueNotValidException(String message) {
        super(message);
    }
}
