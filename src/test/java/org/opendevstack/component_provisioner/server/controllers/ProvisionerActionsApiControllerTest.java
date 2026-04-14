package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.opendevstack.component_provisioner.server.services.PlaceholderPostProcessor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @Mock
    private ProvisionerActionsApiFacade provisionerActionsApiFacade;

    @Mock
    private PlaceholderPostProcessor placeholderPostProcessor;

    @InjectMocks
    private ProvisionerActionsApiController controller;

    @Test
    void triggerProvisionAction_returnsResponseEntityWithMappedResponse_whenFacadeReturnsSuccess() {
        var parameters = new ArrayList<ProvisionActionParameter>();
        var provisionAction = ProvisionActionMother.of(parameters);
        var processedProvisionAction = ProvisionActionMother.of(parameters);

        var provisionActionResponse = new ProvisionActionResponse();
        var awxResponse = AwxResponse.builder()
                .httpStatusCode(HttpStatus.OK)
                .awxResponseBody(provisionActionResponse)
                .build();

        when(authInfo.getCurrentPrincipalName()).thenReturn("test-user");
        when(provisionerActionsApiFacade.requestProvisionToAwx(any())).thenReturn(awxResponse);
        when(placeholderPostProcessor.process(provisionAction)).thenReturn(processedProvisionAction);

        var response = controller.triggerProvisionAction(provisionAction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(provisionActionResponse, response.getBody());

        verify(provisionerActionsApiFacade).addIdTokenToActions(provisionAction);
        verify(provisionerActionsApiValidator).validate(provisionAction);
        verify(provisionerActionsApiFacade).notifyComponentCatalogProvisionStarts(processedProvisionAction);
        verify(provisionerActionsApiFacade).requestProvisionToAwx(processedProvisionAction);
    }

    @Test
    void triggerProvisionAction_returnsResponseEntityWithNullBody_whenFacadeReturnsEmptyResponse() {
        var parameters = new ArrayList<ProvisionActionParameter>();
        var provisionAction = ProvisionActionMother.of(parameters);

        var awxResponse = AwxResponse.builder()
                .httpStatusCode(HttpStatus.NO_CONTENT)
                .awxResponseBody(null)
                .build();

        when(authInfo.getCurrentPrincipalName()).thenReturn("test-user");
        when(provisionerActionsApiFacade.requestProvisionToAwx(any())).thenReturn(awxResponse);
        when(placeholderPostProcessor.process(provisionAction)).thenReturn(provisionAction);

        var response = controller.triggerProvisionAction(provisionAction);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(provisionerActionsApiFacade).addIdTokenToActions(provisionAction);
        verify(provisionerActionsApiValidator).validate(provisionAction);
        verify(provisionerActionsApiFacade).notifyComponentCatalogProvisionStarts(provisionAction);
        verify(provisionerActionsApiFacade).requestProvisionToAwx(provisionAction);
    }
}