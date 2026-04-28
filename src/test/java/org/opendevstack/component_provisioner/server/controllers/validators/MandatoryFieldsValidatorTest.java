package org.opendevstack.component_provisioner.server.controllers.validators;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.CatalogItemMother;
import org.opendevstack.component_provisioner.server.model.CatalogItemUserActionParameterMother;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MandatoryFieldsValidatorTest {

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private MandatoryFieldsValidator validator;

    @Test
    void givenCatalogItemWithoutUserActions_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        catalogItem.setUserActions(null);
        var bearerToken = "bearer-token";

        when(authenticationProvider.getAccessToken()).thenReturn(bearerToken);
        when(componentCatalogService.getCatalogItem(any(), any(), any()))
                .thenReturn(catalogItem);

        ProvisionAction action =
                ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutProvisionAction_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        catalogItem.getUserActions().getFirst().setId("DELETE");
        var bearerToken = "bearer-token";

        when(authenticationProvider.getAccessToken()).thenReturn(bearerToken);
        when(componentCatalogService.getCatalogItem(any(), any(), any()))
                .thenReturn(catalogItem);

        ProvisionAction action =
                ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("doesn't have a PROVISION user action");
    }

    @Test
    void givenMandatoryParameterWithEmptyList_whenValidate_thenExceptionIsThrown() {
        // given
        var bearerToken = "bearer-token";

        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        when(authenticationProvider.getAccessToken()).thenReturn(bearerToken);
        when(componentCatalogService.getCatalogItem(any(), any(), any()))
                .thenReturn(catalogItem);

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", Collections.emptyList());
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when / then
        assertThatThrownBy(() -> validator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("mandatory and no value was provided");
    }

    @Test
    void givenAValidProvisionAction_whenValidate_thenMandatoryFieldsAreProcessed() {
        // given
        var bearerToken = "bearer-token";

        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        when(authenticationProvider.getAccessToken()).thenReturn(bearerToken);
        when(componentCatalogService.getCatalogItem(any(), any(), any()))
                .thenReturn(catalogItem);

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", List.of("defaultValue"));
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when
        validator.validate(action);

        // then
        assertThat(actionParam.getValue()).isEqualTo(List.of("defaultValue"));
    }
}
