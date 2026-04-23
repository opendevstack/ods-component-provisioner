package org.opendevstack.component_provisioner.server.model;

import java.util.Collections;
import java.util.List;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;

public final class ProjectComponentExtendedInfoMother {

    private ProjectComponentExtendedInfoMother() {
        // Evita instanciación
    }

    /**
     * Objeto completamente válido con valores por defecto
     */
    public static ProjectComponentExtendedInfo valid() {
        return ProjectComponentExtendedInfo.builder()
                .componentId("component-id")
                .catalogItemId("aHR0cDovL2JpdGJ1Y2tldC10ZXN0LmNvbQ")
                .catalogItemRef("L3JlZmVyZW5jZT9wYXJhbT0xMA")
                .status("CREATED")
                .componentUrl("https://example.com/component")
                .parameters(Collections.emptyList())
                .build();
    }

    /**
     * Variante simple pasando solo los campos relevantes al test
     */
    public static ProjectComponentExtendedInfo of(
            String componentId,
            String status
    ) {
        return ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(status)
                .catalogItemId("aHR0cDovL2JpdGJ1Y2tldC10ZXN0LmNvbQ")
                .catalogItemRef("L3JlZmVyZW5jZT9wYXJhbT0xMA")
                .componentUrl("https://example.com/component")
                .parameters(Collections.emptyList())
                .build();
    }

    /**
     * Variante si necesitas parámetros
     */
    public static ProjectComponentExtendedInfo withParameters(
            List<ProjectComponentParameter> parameters
    ) {
        return ProjectComponentExtendedInfo.builder()
                .componentId("component-id")
                .catalogItemId("aHR0cDovL2JpdGJ1Y2tldC10ZXN0LmNvbQ")
                .catalogItemRef("L3JlZmVyZW5jZT9wYXJhbT0xMA")
                .status("CREATED")
                .componentUrl("https://example.com/component")
                .parameters(parameters)
                .build();
    }
}