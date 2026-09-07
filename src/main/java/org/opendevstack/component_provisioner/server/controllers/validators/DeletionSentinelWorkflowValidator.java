package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeletionSentinelWorkflowValidator {

    private final String deletionSentinelWorkflowName;

    // We intentionally do not use @RequiredArgsConstructor here: with @Value on this non-final
    // field, Lombok would not include it in the generated constructor, which leaves Spring to
    // inject it at field level instead of constructor level.
    // See Spring docs for @Value:
    // https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html
    public DeletionSentinelWorkflowValidator(
        @Value("${component-provisioner.awx.workflows.deletion-sentinel-workflow-name}")
        String deletionSentinelWorkflowName
    ) {
        this.deletionSentinelWorkflowName = deletionSentinelWorkflowName;
    }

    public void validate(String deletionWorkflowName) {
        if (deletionSentinelWorkflowName.equals(deletionWorkflowName)) {
            log.debug("Deletion workflow name matching sentinel. Rejecting deletion request for workflow: {}", deletionWorkflowName);
            throw new ProjectConfigurationException("This project component is not meant to be deleted. Please contact your administrator if you believe this is an error.");
        } else {
            log.debug("Deletion workflow name does not match sentinel. Proceeding with deletion request for workflow: {}", deletionWorkflowName);
        }
    }
}
