package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.Getter;

@Getter
public enum ParameterType {

    TEXT("text"),
    BOOLEAN("boolean"),
    NUMBER("number"),
    SINGLELIST("singlelist"),
    MULTIPLELIST("multiplelist");

    private final String value;

    ParameterType(String value) {
        this.value = value;
    }

}
