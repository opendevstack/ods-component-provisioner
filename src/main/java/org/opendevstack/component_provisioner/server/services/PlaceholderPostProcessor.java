package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapper;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.text.StringSubstitutor.replace;

@Service
@AllArgsConstructor
@Slf4j
public class PlaceholderPostProcessor {

    public ProvisionActionWrapper process(ProvisionActionWrapper provisionActionWrapper) {
        if (provisionActionWrapper == null || provisionActionWrapper.getParametersMap() == null) {
            return provisionActionWrapper;
        }

        var parameters = provisionActionWrapper.getParametersMap().values().stream().toList();
        Map<String, String> paramValues = extractStringParameters(parameters);

        List<ProvisionActionParameter> newParameters =
                parameters.stream()
                        .map(p -> replaceParameterValue(p, paramValues))
                        .toList();

        // If nothing changed, return the original instance
        if (newParameters.equals(parameters)) {
            return provisionActionWrapper;
        }

        var newParametersMap = newParameters.stream()
                .collect(Collectors.toMap(
                        ProvisionActionParameter::getName,
                        Function.identity()
                ));

        return new ProvisionActionWrapper(provisionActionWrapper.getProvisionActionId(), newParametersMap);
    }

    private Map<String, String> extractStringParameters(List<ProvisionActionParameter> provisionActionParameters) {
        return provisionActionParameters.stream()
                .filter(p -> p.getValue() instanceof String)
                .collect(HashMap::new,
                        (m, p) -> m.put(p.getName(), (String) p.getValue()),
                        HashMap::putAll);
    }

    private ProvisionActionParameter replaceParameterValue(
            ProvisionActionParameter param,
            Map<String, String> paramValues) {

        Object oldValue = param.getValue();
        Object newValue = replaceValue(oldValue, paramValues);

        // No change → reuse existing parameter
        if (Objects.equals(oldValue, newValue)) {
            return param;
        }

        // Create a new parameter instance (immutable-style)
        return param.toBuilder()
                .value(newValue)
                .build();
    }

    private Object replaceValue(Object value, Map<String, String> paramValues) {
        if (value instanceof String str) {
            return replace(str, paramValues, "${", "}");
        }

        if (value instanceof List<?> list) {
            return list.stream()
                    .map(elem -> elem instanceof String s
                            ? replace(s, paramValues, "${", "}")
                            : elem)
                    .toList();
        }

        return value;
    }
}
