package org.opendevstack.component_provisioner.server.model;

import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;

public class AwxWorkflowJobLaunchMother {

    public static AwxWorkflowJobLaunch of() {
        return AwxWorkflowJobLaunch.builder()
                .jobTemplateId("1")
                .build();
    }
}
