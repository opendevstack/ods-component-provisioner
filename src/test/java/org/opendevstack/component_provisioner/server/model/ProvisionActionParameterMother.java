package org.opendevstack.component_provisioner.server.model;

public class ProvisionActionParameterMother {

    public static ProvisionActionParameter of(String name, Object value) {
        return ProvisionActionParameter.builder()
                .type("text")
                .name(name)
                .value(value)
                .build();
    }
}
