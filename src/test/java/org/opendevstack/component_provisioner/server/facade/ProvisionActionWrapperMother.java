package org.opendevstack.component_provisioner.server.facade;

import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProvisionActionWrapperMother {

    public static ProvisionActionWrapper of() {
        return of(Collections.emptyList());
    }

    public static ProvisionActionWrapper of(List<ProvisionActionParameter> parameters) {
        var parametersMap = parameters.stream()
                .collect(Collectors.toMap(
                        ProvisionActionParameter::getName,
                        Function.identity()
                ));

        return of("action-id", parametersMap);
    }

    public static ProvisionActionWrapper of(String provisionActionId, Map<String, ProvisionActionParameter> parametersMap) {
        return new ProvisionActionWrapper(provisionActionId, parametersMap);
    }
}
