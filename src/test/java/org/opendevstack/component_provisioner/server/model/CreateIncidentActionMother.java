package org.opendevstack.component_provisioner.server.model;

import java.util.ArrayList;

public class CreateIncidentActionMother {

    public static CreateIncidentAction of() {
        var parameters = new ArrayList<CreateIncidentParameter>();

        parameters.add(CreateIncidentParameterMother.of("caller"));
        parameters.add(CreateIncidentParameterMother.of("cluster_location"));
        parameters.add(CreateIncidentParameterMother.of("is_deployed"));
        parameters.add(CreateIncidentParameterMother.of("change_number"));
        parameters.add(CreateIncidentParameterMother.of("reason"));

        return CreateIncidentAction.builder()
                .parameters(parameters)
                .build();
    }
}
