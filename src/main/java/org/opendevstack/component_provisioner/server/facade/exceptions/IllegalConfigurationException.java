package org.opendevstack.component_provisioner.server.facade.exceptions;

public class IllegalConfigurationException extends RuntimeException {
    public IllegalConfigurationException(String message) {
        super(message);
    }

    public IllegalConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
