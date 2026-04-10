package org.opendevstack.component_provisioner.server.model;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;

import java.util.Collections;
import java.util.List;

public class CatalogItemUserActionParameterMother {

    public static CatalogItemUserActionParameter of() {
        return of("paramName");
    }

    public static CatalogItemUserActionParameter of(String name) {
        return of(name, "paramValue");
    }

    public static CatalogItemUserActionParameter of(String name, String defaultValue) {
        return of(name, defaultValue, Collections.emptyList());
    }

    public static CatalogItemUserActionParameter of(String name, String defaultValue, List<String> options) {
        return CatalogItemUserActionParameter.builder()
                .type("String")
                .name(name)
                .required(true)
                .defaultValue(defaultValue)
                .options(options)
                .build();
    }

    public static CatalogItemUserActionParameter of(String name, List<String> defaultValues) {
        return of(name, defaultValues, Collections.emptyList());
    }

    public static CatalogItemUserActionParameter of(String name, List<String> defaultValues, List<String> options) {
        return CatalogItemUserActionParameter.builder()
                .type("String")
                .name(name)
                .required(true)
                .defaultValues(defaultValues)
                .options(options)
                .build();
    }
}
