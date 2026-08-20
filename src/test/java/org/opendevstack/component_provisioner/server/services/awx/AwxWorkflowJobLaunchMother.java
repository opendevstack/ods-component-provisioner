package org.opendevstack.component_provisioner.server.services.awx;

public class AwxWorkflowJobLaunchMother {

    public static AwxWorkflowJobLaunch of() {
        return AwxWorkflowJobLaunch.builder()
                .jobTemplateId("1")
                .build();
    }
}
