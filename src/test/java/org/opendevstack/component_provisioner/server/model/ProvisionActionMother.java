package org.opendevstack.component_provisioner.server.model;

import java.util.List;

public class ProvisionActionMother {

    public static ProvisionAction of(List<ProvisionActionParameter> parameters) {
        return ProvisionAction.builder()
                .id("action-id")
                .parameters(parameters)
                .build();
    }
}
