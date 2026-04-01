package org.opendevstack.component_provisioner.server.controllers.exceptions;

public class UserNotAllowedException extends RuntimeException {

    public UserNotAllowedException(String message) {
        super(message);
    }

}
