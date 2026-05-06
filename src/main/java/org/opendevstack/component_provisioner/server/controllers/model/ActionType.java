package org.opendevstack.component_provisioner.server.controllers.model;

import lombok.Getter;

@Getter
public enum ActionType {

    CREATE_INCIDENT("create_incident"),
    DELETE("delete");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }

}
