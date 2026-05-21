package org.opendevstack.component_provisioner.server.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.ods_api_server.v1.model.CreateProjectResponse;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapper;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapperMother;
import org.opendevstack.component_provisioner.server.facade.exceptions.IllegalConfigurationException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
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
    void givenNoParametersMatchingConfigured_whenReplaceProvisioningParametersFromOdsApi_thenSkipsOdsApiCall() {
        // given
        initializeService("project_name");
        var unrelatedParam = ProvisionActionParameterMother.of("unrelated_param", "someValue");
        var params = Map.of("unrelated_param", unrelatedParam);
        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of(params);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result).isEqualTo(wrapper);
        verifyNoInteractions(odsApiService);
    }

    @Test
    void givenProjectNotFound_whenReplaceProvisioningParametersFromOdsApi_thenReturnsUnmodifiedWrapper() {
        // given
        initializeService("project_name");

        var projectKey = "projectKey";
        var params = Map.of(
                "project_key", ProvisionActionParameterMother.of("project_key", projectKey),
                "project_name", ProvisionActionParameterMother.of("project_name", "projectName")
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
        ProvisionActionParameter parameter = ProvisionActionParameterMother.of("project_name", "oldValue");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "project_name", parameter,
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("project_name", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = Map.of("project_name", "newValue");

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap())
                .containsKey("project_name")
                .extracting("project_name").isNotNull();
        Assertions.assertThat(result.getParametersMap().get("project_name").getValue()).isEqualTo("newValue");
    }

    @Test
    void givenParameterNotInOdsApi_whenReplaceProvisioningParametersFromOdsApi_thenKeepsOriginalValue() {
        // given
        initializeService("project_name");
        ProvisionActionParameter parameter = ProvisionActionParameterMother.of("project_name", "originalValue");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "project_name", parameter,
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("project_name", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = new HashMap<>();

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap().get("project_name").getValue()).isEqualTo("originalValue");
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
                .hasMessageContaining("Only type string and singlelist are supported");
    }

    @Test
    void givenEmptyValueFromOdsApi_whenReplaceProvisioningParametersFromOdsApi_thenKeepsOriginalValue() {
        // given
        initializeService("project_name");
        ProvisionActionParameter parameter = ProvisionActionParameterMother.of("project_name", "originalValue");

        var projectKey = "projectKey";
        var projectKeyParam = ProvisionActionParameterMother.of("project_key", projectKey);
        var params = Map.of(
                "project_name", parameter,
                "project_key", projectKeyParam
        );

        ProvisionActionWrapper wrapper = ProvisionActionWrapperMother.of("project_name", params);

        var projectData = new CreateProjectResponse();
        Map<String, Object> odsApiValues = Map.of("project_name", "");

        when(odsApiService.getProject(anyString())).thenReturn(projectData);
        when(snakeCaseExtractor.toSnakeCaseMap(projectData)).thenReturn(odsApiValues);

        // when
        ProvisionActionWrapper result = replaceParametersService.replaceProvisioningParametersFromOdsApi(wrapper);

        // then
        Assertions.assertThat(result.getParametersMap().get("project_name").getValue()).isEqualTo("originalValue");
    }
}
