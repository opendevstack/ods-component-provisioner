package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.Getter;

@Getter
public enum MandatoryFieldType {

    SINGLELIST("singlelist"),
    MULTIPLELIST("multiplelist");

    private final String value;

    MandatoryFieldType(String value) {
        this.value = value;
    }

}
