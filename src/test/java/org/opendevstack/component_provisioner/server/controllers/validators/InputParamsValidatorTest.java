package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParamsValidatorTest {

    private final InputParamsValidator validator = new InputParamsValidator();

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void givenBlankProjectKey_whenValidateInputParams_thenThrowsInvalidRestEntityException(String projectKey) {
        // given
        var accessToken = "accessToken";
        var componentId = "componentId";

        // when / then
        assertThatThrownBy(() -> validator.validateInputParams(projectKey, accessToken, componentId))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage("project_key, access_token, component_id are required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void givenBlankAccessToken_whenValidateInputParams_thenThrowsInvalidRestEntityException(String accessToken) {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

        // when / then
        assertThatThrownBy(() -> validator.validateInputParams(projectKey, accessToken, componentId))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage("project_key, access_token, component_id are required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void givenBlankComponentId_whenValidateInputParams_thenThrowsInvalidRestEntityException(String componentId) {
        // given
        var projectKey = "projectKey";
        var accessToken = "accessToken";

        // when / then
        assertThatThrownBy(() -> validator.validateInputParams(projectKey, accessToken, componentId))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage("project_key, access_token, component_id are required.");
    }

    @Test
    void givenValidInputParams_whenValidateInputParams_thenDoesNotThrow() {
        // given
        var projectKey = "projectKey";
        var accessToken = "accessToken";
        var componentId = "componentId";

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validateInputParams(projectKey, accessToken, componentId));
    }
}

