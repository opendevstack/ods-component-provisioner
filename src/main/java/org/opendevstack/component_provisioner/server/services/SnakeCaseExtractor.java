package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SnakeCaseExtractor {

    public Map<String, Object> toSnakeCaseMap(Object obj) {
        Map<String, Object> result = new HashMap<>();

        for (Method method : obj.getClass().getMethods()) {
            // only no-arg getters
            if (method.getName().startsWith("get")
                    && method.getParameterCount() == 0
                    && !method.getName().equals("getClass")) {

                try {
                    Object value = method.invoke(obj);

                    // skip null values if desired
                    if (value != null) {
                        String propertyName = method.getName().substring(3); // remove "get"
                        propertyName = Character.toLowerCase(propertyName.charAt(0))
                                + propertyName.substring(1);

                        String snakeCaseKey = camelToSnake(propertyName);
                        result.put(snakeCaseKey, value);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to extract getter: " + method.getName(), e);
                }
            }
        }

        return result;
    }

    private String camelToSnake(String camelCase) {
        return camelCase
                .replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toLowerCase();
    }
}
