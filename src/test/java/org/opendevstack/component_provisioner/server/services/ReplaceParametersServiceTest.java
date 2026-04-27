package org.opendevstack.component_provisioner.server.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.model.CreateProjectResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapper;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapperMother;
import org.opendevstack.component_provisioner.server.facade.exceptions.IllegalConfigurationException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReplaceParametersServiceTest {

    @Mock
    private OdsApiService odsApiService;

    @Mock
    private SnakeCaseExtractor snakeCaseExtractor;

    private ReplaceParametersService replaceParametersService;

    private void initializeService(String paramsToOverride) {
        replaceParametersService = new ReplaceParametersService(odsApiService, snakeCaseExtractor, paramsToOverride, true);
    }

    @Test
    void givenEmptyParamsToOverride_whenReplaceProvisioningParametersFromOdsApi_thenReturnsUnmodifiedWrapper() {
        // given
        initializeService("");
        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of();

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result).isEqualTo(wrapper);
    }

    @Test
    void givenProjectNotFound_whenReplaceProvisioningParametersFromOdsApi_thenReturnsUnmodifiedWrapper() {
        // given
        initializeService("project_name");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of(params);

        when(odsApiService.getProject(projectKey)).thenReturn(null);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result).isEqualTo(wrapper);
    }

    @Test
    void givenParameterOverriddenFromOdsApi_whenReplaceProvisioningParametersFromOdsApi_thenReturnsUpdatedParameter() {
        // given
        initializeService("project_name");
        ProvisionActionParameter parameter = ProvisionActionParameterMother.of("param1", "oldValue");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "param1", parameter,
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("param1", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = Map.of("param1", "newValue");

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap())
                .containsKey("param1")
                .extracting("param1").isNotNull();
        Assertions.assertThat(result.getParametersMap().get("param1").getValue()).isEqualTo("newValue");
    }

    @Test
    void givenParameterNotInOdsApi_whenReplaceProvisioningParametersFromOdsApi_thenKeepsOriginalValue() {
        // given
        initializeService("project_name");
        ProvisionActionParameter parameter = ProvisionActionParameterMother.of("param1", "originalValue");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "param1", parameter,
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("param1", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = new HashMap<>();

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap().get("param1").getValue()).isEqualTo("originalValue");
    }

    @Test
    void givenNonStringParameterInOdsApi_whenReplaceProvisioningParametersFromOdsApi_thenThrowsIllegalConfigurationException() {
        // given
        initializeService("non_string_param");
        ProvisionActionParameter parameter = ProvisionActionParameterMother.of("non_string_param", "INTEGER", 0);
        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "non_string_param", parameter,
                "project_key", projectKeyParam
        );
        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("non_string_param", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = Map.of("non_string_param", 123);

        when(odsApiService.getProject(projectKey)).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when & then
        Assertions.assertThatThrownBy(() -> replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper))
                .isInstanceOf(IllegalConfigurationException.class)
                .hasMessageContaining("not of type String");
    }

    @Test
    void givenMissingRequiredOdsParameter_whenReplaceProvisioningParametersFromOdsApi_thenAddsParameterFromOdsApi() {
        // given
        initializeService("required_param");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("provision_action_id", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = Map.of("required_param", "valueFromOds");

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap())
                .containsKey("required_param");
        Assertions.assertThat(result.getParametersMap().get("required_param").getValue()).isEqualTo("valueFromOds");
        Assertions.assertThat(result.getParametersMap().get("required_param").getType()).isEqualTo(ParameterType.STRING.getValue());
    }

    @Test
    void givenMultipleParametersWithMixedSources_whenReplaceProvisioningParametersFromOdsApi_thenMergesCorrectly() {
        // given
        initializeService("param2,param3");
        ProvisionActionParameter param1 = ProvisionActionParameterMother.of("param1", "value1");
        ProvisionActionParameter param2 = ProvisionActionParameterMother.of("param2", "oldValue2");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "project_key", projectKeyParam,
                "param1", param1,
                "param2", param2
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("provision", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = Map.of("param2", "newValue2", "param3", "value3");

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap())
                .hasSize(4)
                .containsKeys("project_key", "param1", "param2", "param3");
        Assertions.assertThat(result.getParametersMap().get("param1").getValue()).isEqualTo("value1");
        Assertions.assertThat(result.getParametersMap().get("param2").getValue()).isEqualTo("newValue2");
        Assertions.assertThat(result.getParametersMap().get("param3").getValue()).isEqualTo("value3");
    }
}
