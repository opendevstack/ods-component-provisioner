package com.boehringer.componentprovisioner.server.services.exceptions;

public class CatalogClientException extends RuntimeException {
    public CatalogClientException(Exception e) {
        super(e);
    }
}
