package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.springframework.stereotype.Service;

import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getDeletionWorkflow;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getDeletionWorkflowName;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getWorkflow;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getWorkflowName;

@Service
@Slf4j
public class WorkflowsValidator {

    public void validate(ProvisionAction provisionAction) {
        var workflow = getWorkflow(provisionAction);
        var workflowName = getWorkflowName(provisionAction);
        var deletionWorkflow = getDeletionWorkflow(provisionAction);
        var deletionWorkflowName = getDeletionWorkflowName(provisionAction);

        log.debug("Validating presence of workflow or workflow_name. Workflow: {}, Workflow name: {}", workflow, workflowName);

        var workflowIsNotPresent = StringUtils.isBlank(workflow) && StringUtils.isBlank(workflowName);
        var deletionWorkflowIsNotPresent = StringUtils.isBlank(deletionWorkflow) && StringUtils.isBlank(deletionWorkflowName);

        if (workflowIsNotPresent || deletionWorkflowIsNotPresent) {
            throw new InvalidRestEntityException("Either workflow or workflow_name are required. Also deletion_workflow is required.");
        }
    }

    public void validate(String deletionWorkflowId, String deletionWorkflowName) {
        log.debug("Validating presence of deletion workflow or deletion workflow name. Deletion Workflow ID: {}, Deletion Workflow Name: {}", deletionWorkflowId, deletionWorkflowName);

        if (StringUtils.isBlank(deletionWorkflowId) && StringUtils.isBlank(deletionWorkflowName)) {
            throw new InvalidRestEntityException("The component has no deletion_workflow nor deletion_workflow_name configured, so params is_deployed, change_number and reason are required in the request.");
        }
    }
}
