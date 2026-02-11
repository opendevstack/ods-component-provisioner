package org.opendevstack.component_provisioner.server.services.exceptions;

public class CatalogClientException extends RuntimeException {
    public CatalogClientException(Exception e) {
        super(e);
    }
}
