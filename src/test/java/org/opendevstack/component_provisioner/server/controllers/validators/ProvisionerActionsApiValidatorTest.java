package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiValidatorTest {

    @Mock
    private ComponentsValidator componentsValidator;

    @Mock
    private InputParamsValidator inputParamsValidator;

    @InjectMocks
    private ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @Test
    void givenAlreadyProvisionedComponent_whenValidating_thenThrowsProjectComponentAlreadyProvisionedException() {
        // given
        var projectKey = "pkey";
        var componentId = "cid";
        var accessToken = "accessToken";

        var params = List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "111"),
                ProvisionActionParameterMother.of("access_token", accessToken)
        );

        var action = ProvisionActionMother.of(params);

        doThrow(new ProjectComponentAlreadyProvisionedException("This component name already exists, please choose another name."))
                .when(componentsValidator)
                .validate(action);

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validate(action))
                .isInstanceOf(ProjectComponentAlreadyProvisionedException.class);
    }

    @Test
    void givenInvalidInputParams_whenValidating_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "";
        var accessToken = "accessToken";
        var componentId = "cid";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", accessToken)
        ));

        doThrow(new InvalidRestEntityException("project_key, access_token, component_id are required."))
                .when(inputParamsValidator)
                .validate(action);

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenValidInput_whenValidating_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validate(action));
    }


    @Test
    void givenComponentsValidatorThrows_whenValidating_thenThrowsRuntimeException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        doThrow(new RuntimeException("Service error"))
                .when(componentsValidator)
                .validate(any());

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validate(action))
                .isInstanceOf(RuntimeException.class);
    }
}
