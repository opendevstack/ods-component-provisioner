package com.boehringer.componentprovisioner.server.services.awx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwxWorkflowJobLaunch {
    // NOTES:
    // - Data structure matching the ones on Martketplace 1.0 edpp-component-catalog
    // - Minimal adaptation of:
    //      com.bi.edp.provisioning_awx.adapter.client.request.AwxClientWorkflowJobTemplateLaunchRequestModel

    /**
     * Job template identifier to launch.
     */
    private String jobTemplateId;

    /**
     * String with extra variables to be passed to the job. Can be JSON or YAML.
     */
    private String extraVars;

    /**
     * Inventory identifier against which to run the job.
     */
    private Integer inventory;

    /**
     * Limit.
     */
    private String limit;

    /**
     * Scm branch.
     */
    private String scmBranch;
}
