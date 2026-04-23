package org.opendevstack.component_provisioner.server.model;

public class ProvisionActionParameterMother {

    public static ProvisionActionParameter of(String name, Object value) {
        return of(name, "string", value);
    }

    public static ProvisionActionParameter of(String name, String type, Object value) {
        return ProvisionActionParameter.builder()
                .type(type)
                .name(name)
                .value(value)
                .build();
    }
}
