package com.boehringer.componentprovisioner.server.services.awx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwxWorkflowJob {
    // NOTES:
    // - Data structure matching the ones on Martketplace 1.0 edpp-component-catalog
    // - Minimal adaptation of:
    //      com.bi.edp.provisioning_awx.adapter.client.response.AwxClientWorkflowJobTemplateLaunchResponseModel

    /**
     * Flag to signal if job failed.
     */
    private Boolean failed;

    /**
     * Job identifier.
     */
    private Integer id;

    /**
     * Timestamp when this workflow job was created.
     */
    private OffsetDateTime created;

    /**
     * Timestamp when this workflow job was last modified.
     */
    private OffsetDateTime modified;
}
