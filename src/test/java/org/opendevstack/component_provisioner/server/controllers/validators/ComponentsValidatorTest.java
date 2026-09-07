package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComponentsValidatorTest {

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @InjectMocks
    private ComponentsValidator validator;

    @Test
    void givenAlreadyProvisionedComponent_whenValidating_thenThrowsProjectComponentAlreadyProvisionedException() {
        // given
        var accessToken = "bearerToken";
        var projectKey = "PRJ";
        var componentId = "component-a";
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponents(accessToken, projectKey)).thenReturn(List.of(
                projectComponentInfo("component-b"),
                projectComponentInfo(componentId)
        ));

        // when / then
        assertThatThrownBy(() -> validator.validate(provisionAction))
                .isInstanceOf(ProjectComponentAlreadyProvisionedException.class)
                .hasMessage("This component name already exists, please choose another name.");
    }

    @Test
    void givenProjectComponentsWithNullComponentIds_whenValidatingAndNoMatchExists_thenDoesNotThrow() {
        // given
        var accessToken = "bearerToken";
        var projectKey = "PRJ";
        var componentId = "component-a";
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponents(accessToken, projectKey)).thenReturn(List.of(
                projectComponentInfo(null),
                projectComponentInfo("component-b")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> validator.validate(provisionAction));
    }

    @Test
    void givenComponentIsNotProvisioned_whenValidating_thenUsesAccessTokenAndProjectKeyToLoadProjectComponents() {
        // given
        var accessToken = "bearerToken";
        var projectKey = "PRJ";
        var componentId = "component-a";
        var provisionAction = provisionAction(projectKey, componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponents(accessToken, projectKey)).thenReturn(List.of(
                projectComponentInfo("component-b")
        ));

        // when
        validator.validate(provisionAction);

        // then
        verify(authenticationProvider).getAccessToken();
        verify(componentCatalogService).getProjectComponents(accessToken, projectKey);
    }

    private ProjectComponentInfo projectComponentInfo(String componentId) {
        var projectComponentInfo = new ProjectComponentInfo();
        projectComponentInfo.setComponentId(componentId);
        return projectComponentInfo;
    }

    private ProvisionAction provisionAction(String projectKey, String componentId) {
        return ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId)
        ));
    }
}

