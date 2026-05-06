package org.opendevstack.component_provisioner.server.services.exceptions;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String id) {
        super("Invalid id: " + id);
    }

    public InvalidIdException(String id, Throwable cause) {
        super("Invalid id: " + id, cause);
    }

}
