package org.opendevstack.component_provisioner.server.controllers.model;

import java.util.Arrays;

public enum ProjectComponentStatus {
    CREATING,
    CREATED,
    DELETING,
    UNKNOWN;

    public static String valuesToString() {
        var namesList = Arrays.stream(values())
                .map(Enum::name)
                .toList();

        return String.join(", ", namesList);
    }
}
