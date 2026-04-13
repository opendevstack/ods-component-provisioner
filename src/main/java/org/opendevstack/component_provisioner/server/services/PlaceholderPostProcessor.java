package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.text.StringSubstitutor.replace;

@Service
@AllArgsConstructor
@Slf4j
public class PlaceholderPostProcessor {

    public ProvisionAction process(ProvisionAction provisionAction) {
        if (provisionAction == null || provisionAction.getParameters() == null) {
            return provisionAction;
        }
        var parameters = provisionAction.getParameters();
        Map<String, String> paramValues = new HashMap<>();
        for (var param : parameters) {
            Object value = param.getValue();
            if (value instanceof String) {
                paramValues.put(param.getName(), (String) value);
            }
        }
        for (var param : parameters) {
            Object value = param.getValue();
            if (value instanceof String strVal) {
                String replaced = replace(strVal, paramValues, "${", "}");
                if (!replaced.equals(strVal)) {
                    param.setValue(replaced);
                }
            } else if (value instanceof List<?> listVal) {
                boolean changed = false;
                List<Object> newList = new ArrayList<>();
                for (Object elem : listVal) {
                    if (elem instanceof String elemStr) {
                        String replaced = replace(elemStr, paramValues, "${", "}");
                        if (!replaced.equals(elemStr)) {
                            changed = true;
                        }
                        newList.add(replaced);
                    } else {
                        newList.add(elem);
                    }
                }
                if (changed) {
                    param.setValue(newList);
                }
            }
        }

        return provisionAction;
    }
}
