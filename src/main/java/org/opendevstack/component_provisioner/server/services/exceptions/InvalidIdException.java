package org.opendevstack.component_provisioner.server.services.exceptions;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String id) {
        super("Invalid id: " + id);
    }
}
