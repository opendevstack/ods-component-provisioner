package org.opendevstack.component_provisioner.server.controllers.model;

import lombok.Getter;

@Getter
public enum ActionType {

    CREATE_INCIDENT("CREATE_INCIDENT"),
    PROVISION("PROVISION"),
    DELETE("DELETE");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }

}
