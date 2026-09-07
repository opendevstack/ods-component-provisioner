package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WorkflowsValidatorTest {

    private final WorkflowsValidator validator = new WorkflowsValidator();

    @Test
    void givenMissingWorkflowAndWorkflowName_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenMissingDeletionNamesAndWorkflowNameProvided_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123")
        ));

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenMissingDeletionNamesAndOnlyWorkflowName_whenValidatingWorkflow_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow_name", "wf-name")
        ));

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenBlankDeletionWorkflow_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("deletion_workflow", "   ")
        ));

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenWorkflowProvidedByUser_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(action));
    }

    @Test
    void givenDeletionWorkflowNameProvided_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("deletion_workflow_name", "del-wf-name")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(action));
    }

    @Test
    void givenWorkflowNameProvidedByUser_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow_name", "wf-name"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(action));
    }

    @Test
    void givenWorkflowAndWorkflowNameProvided_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("workflow_name", "wf-name"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(action));
    }

    @Test
    void givenWorkflowFromHiddenCatalogItemParam_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        // given
        // Workflow was not provided by the user but was injected from the catalog item's
        // hidden (non-visible) mandatory parameter before this validation is invoked.
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("workflow", "wf-from-hidden-param"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(action));
    }
}

