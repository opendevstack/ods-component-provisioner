package org.opendevstack.component_provisioner.server.model;

public class CreateIncidentParameterMother {

    public static CreateIncidentParameter of(String name) {
        return of(name, name + "_value");
    }

    public static CreateIncidentParameter of(String name, String value) {
        return CreateIncidentParameter.builder()
                .name(name)
                .value(value)
                .type("String")
                .build();
    }
}
