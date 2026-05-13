package org.opendevstack.component_provisioner.server.services.model;

import lombok.Getter;

@Getter
public enum AwxResultNames {

    RESULT_OUTPUT("result_output"),
    RESULT_CODE("result_code");

    private final String value;

    AwxResultNames(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
