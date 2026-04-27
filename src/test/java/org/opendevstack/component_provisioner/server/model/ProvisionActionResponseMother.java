package org.opendevstack.component_provisioner.server.model;

public class ProvisionActionResponseMother {
    public static ProvisionActionResponse of() {
        return ProvisionActionResponse.builder()
                .id(1234)
                .build();
    }
}
