package org.opendevstack.component_provisioner.server.controllers.validators;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.CatalogItemUserActionParameterMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MandatoryFieldsValidatorTest {

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private MandatoryFieldsValidator validator;

    @Test
    void givenBlankValueAndSingleDefault_whenUpdateParam_thenDefaultValueIsApplied() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", null);

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of(
                        "param1",
                        "default-value"
                );

        // when
        validator.updateParam(param, catalogParam);

        // then
        assertThat(param.getValue())
                .isEqualTo(List.of("default-value"));
    }

    @Test
    void givenBlankValueAndMultipleDefaultsForNonListType_whenUpdateParam_thenExceptionIsThrown() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", "");

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of(
                        "param1",
                        List.of("a", "b")
                );

        // when / then
        assertThatThrownBy(() -> validator.updateParam(param, catalogParam))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("param1 is mandatory");
    }

    @Test
    void givenBlankValueAndNoDefaults_whenUpdateParam_thenExceptionIsThrown() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", " ");

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of("param1");
        catalogParam.setDefaultValue(null);
        catalogParam.setDefaultValues(null);

        // when / then
        assertThatThrownBy(() -> validator.updateParam(param, catalogParam))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("param1 is mandatory");
    }

    @Test
    void givenNonBlankValueAndNoDefaults_whenUpdateParam_thenValueIsAccepted() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", "any-value");

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of("param1");
        catalogParam.setDefaultValue(null);
        catalogParam.setDefaultValues(null);

        // when
        validator.updateParam(param, catalogParam);

        // then
        assertThat(param.getValue()).isEqualTo("any-value");
    }

    @Test
    void givenSingleValueNotInOptions_whenUpdateParam_thenExceptionIsThrown() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", "invalid");

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of(
                        "param1",
                        Collections.emptyList(),
                        List.of("valid1", "valid2")
                );

        // when / then
        assertThatThrownBy(() -> validator.updateParam(param, catalogParam))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("invalid")
                .hasMessageContaining("param1");
    }

    @Test
    void givenListValueWithInvalidOption_whenUpdateParam_thenExceptionIsThrown() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", List.of("valid", "invalid"));
        param.setType("multiplelist");

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of(
                        "param1",
                        Strings.EMPTY,
                        List.of("valid")
                );

        // when / then
        assertThatThrownBy(() -> validator.updateParam(param, catalogParam))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("invalid");
    }

    @Test
    void givenListValueAllValid_whenUpdateParam_thenValueIsAccepted() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", List.of("a", "b"));
        param.setType("multiplelist");

        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of(
                        "param1",
                        List.of("a", "b", "c")
                );

        // when
        validator.updateParam(param, catalogParam);

        // then
        assertThat(param.getValue()).isEqualTo(List.of("a", "b"));
    }

    @Test
    void givenBlankValueAndMultipleDefaultsButWrongType_whenUpdateParam_thenExceptionIsThrown() {
        // given
        ProvisionActionParameter param =
                ProvisionActionParameterMother.of("param1", " ");

        // multiple defaults exist, but param is TEXT
        CatalogItemUserActionParameter catalogParam =
                CatalogItemUserActionParameterMother.of(
                        "param1",
                        List.of("a", "b"),
                        List.of("option1", "option2")
                );

        // when / then
        var exception = assertThrows(
                InvalidRestEntityException.class,
                () -> validator.updateParam(param, catalogParam)
        );

        assertThat(exception.getMessage())
                .contains("param1 is mandatory")
                .doesNotContain("option1", "option2");
    }
}
