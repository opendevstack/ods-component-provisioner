package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.model.awx.AwxResponse;
import org.opendevstack.component_provisioner.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    private ProvisionerActionsApiFacade provisionerActionsApiFacade;

    @InjectMocks
    private ProvisionerActionsApiController controller;

    @Test
    void givenAProvisionAction_whenTriggerProvisionAction_thenReturnExpectedResponse() {
        // Given
        var parameters = new ArrayList<ProvisionActionParameter>();
        var provisionAction = ProvisionActionMother.of(parameters);

        var httpStatusCode = HttpStatus.OK;
        var awxResponseBody = ProvisionActionResponse.builder().build();

        var awxResponse = new AwxResponse(httpStatusCode, awxResponseBody);

        when(provisionerActionsApiFacade.triggerProvisionAction(any(ProvisionAction.class)))
                .thenReturn(awxResponse);

        // When
        var responseEntity = controller.triggerProvisionAction(provisionAction);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(awxResponseBody, responseEntity.getBody());

        verify(provisionerActionsApiFacade).triggerProvisionAction(provisionAction);
    }
}