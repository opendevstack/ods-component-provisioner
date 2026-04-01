package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.server.controllers.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiValidatorTest {

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @ParameterizedTest
    @ValueSource(strings = { "project_key", "component_id", "access_token" })
    void validate_throwsInvalidRestEntityException_whenRequiredParameterMissing(String missingParam) {
        var action = buildActionMissing(missingParam);

        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    private ProvisionAction buildActionMissing(String missingParamName) {
        var params = new ArrayList<ProvisionActionParameter>();
        if (!"project_key".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("project_key", "pkey"));
        if (!"component_id".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("component_id", "cid"));
        if (!"catalog_item_id".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("catalog_item_id", "catid"));
        if (!"access_token".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("access_token", "accessToken"));

        return ProvisionActionMother.of(params);
    }

    @Test
    void validate_throwsProjectComponentAlreadyProvisionedException_whenComponentAlreadyExistsInCatalog() {
        var projectKey = "pkey";
        var componentId = "cid";
        var idToken = "idToken";
        var accessToken = "accessToken";

        var params = List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "111"),
                ProvisionActionParameterMother.of("access_token", accessToken)
        );

        var action = ProvisionActionMother.of(params);

        var exists = new ProjectComponentInfo();
        exists.setComponentId(componentId);

        when(authenticationProvider.getIdToken()).thenReturn(idToken);

        when(componentCatalogService.getProjectComponents(projectKey, idToken, accessToken))
                .thenReturn(List.of(exists));

        assertThrows(ProjectComponentAlreadyProvisionedException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

}
