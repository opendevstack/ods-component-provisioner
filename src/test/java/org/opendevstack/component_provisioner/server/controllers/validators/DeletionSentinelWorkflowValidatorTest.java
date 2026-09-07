package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeletionSentinelWorkflowValidatorTest {

    @Test
    void givenDeletionWorkflowNameMatchesSentinel_whenValidateIsCalled_thenThrowsProjectConfigurationException() {
        // given
        var validator = new DeletionSentinelWorkflowValidator("NOT-IMPLEMENTED");

        // when / then
        assertThatThrownBy(() -> validator.validate("NOT-IMPLEMENTED"))
                .isInstanceOf(ProjectConfigurationException.class)
                .hasMessage("This project component is not meant to be deleted. Please contact your administrator if you believe this is an error.");
    }

    @Test
    void givenDeletionWorkflowNameDoesNotMatchSentinel_whenValidateIsCalled_thenDoesNotThrow() {
        // given
        var validator = new DeletionSentinelWorkflowValidator("NOT-IMPLEMENTED");

        // when / then
        assertThatCode(() -> validator.validate("CUSTOM-WORKFLOW")).doesNotThrowAnyException();
    }
}

