package org.opendevstack.component_provisioner.client.component_catalog.v1.model;

import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;

public class ProjectComponentInfoMother {

    public static ProjectComponentInfo of() {
        return of(ProjectComponentStatus.CREATING);
    }

    public static ProjectComponentInfo of(ProjectComponentStatus status) {
        return ProjectComponentInfo.builder()
                .componentId("componentId")
                .componentUrl("http://www.example.com")
                .canBeDeleted(false)
                .hasAutomatedDeletionWorkflow(false)
                .status(status.name())
                .build();
    }
}
