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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
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
        // given
        var parameters = new ArrayList<ProvisionActionParameter>();
        var provisionAction = ProvisionActionMother.of(parameters);

        var httpStatusCode = HttpStatus.OK;
        var awxResponseBody = ProvisionActionResponse.builder().build();

        var awxResponse = new AwxResponse(httpStatusCode, awxResponseBody);

        when(provisionerActionsApiFacade.triggerProvisionAction(any(ProvisionAction.class)))
                .thenReturn(awxResponse);

        // when
        ResponseEntity<ProvisionActionResponse> responseEntity = controller.triggerProvisionAction(provisionAction);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(awxResponseBody);

        verify(provisionerActionsApiFacade).triggerProvisionAction(provisionAction);
    }
}