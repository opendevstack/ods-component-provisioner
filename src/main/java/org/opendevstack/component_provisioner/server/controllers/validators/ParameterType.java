package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.Getter;

@Getter
public enum ParameterType {

    STRING("string"),
    SINGLELIST("singlelist"),
    MULTIPLELIST("multiplelist");

    private final String value;

    ParameterType(String value) {
        this.value = value;
    }

}
