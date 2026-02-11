package org.opendevstack.component_provisioner.server.services.exceptions;

public class AwxClientException extends RuntimeException {
    public AwxClientException(String message, Exception e) {
        super(message, e);
    }
}
