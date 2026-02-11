package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class RestEntityNotFoundException extends RuntimeException {

    public RestEntityNotFoundException(String message) {
        super(message);
    }

}
