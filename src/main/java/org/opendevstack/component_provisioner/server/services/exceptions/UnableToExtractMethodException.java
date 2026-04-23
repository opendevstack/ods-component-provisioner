package org.opendevstack.component_provisioner.server.services.exceptions;

public class UnableToExtractMethodException extends RuntimeException{

    public UnableToExtractMethodException(String message, Throwable cause) {
        super(message, cause);
    }
}
