package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputParamsValidatorTest {

    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private InputParamsValidator validator;

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void givenBlankProjectKey_whenValidateInputParams_thenThrowsInvalidRestEntityException(String projectKey) {
        // given
        var accessToken = "accessToken";
        var componentId = "componentId";
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when / then
        assertThatThrownBy(() -> validator.validate(provisionAction))
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
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when / then
        assertThatThrownBy(() -> validator.validate(provisionAction))
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
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when / then
        assertThatThrownBy(() -> validator.validate(provisionAction))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessage("project_key, access_token, component_id are required.");
    }

    @Test
    void givenValidInputParams_whenValidateInputParams_thenDoesNotThrow() {
        // given
        var projectKey = "projectKey";
        var accessToken = "accessToken";
        var componentId = "componentId";
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(provisionAction));
    }

    private ProvisionAction provisionAction(String projectKey, String componentId) {
        var parameters = new ArrayList<org.opendevstack.component_provisioner.server.model.ProvisionActionParameter>();

        if (projectKey != null) {
            parameters.add(ProvisionActionParameterMother.of("project_key", projectKey));
        }

        if (componentId != null) {
            parameters.add(ProvisionActionParameterMother.of("component_id", componentId));
        }

        return ProvisionActionMother.of(parameters);
    }
}

