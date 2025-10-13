package com.boehringer.componentprovisioner.server.controllers;

import com.boehringer.componentprovisioner.server.mappers.EntitiesMapper;
import com.boehringer.componentprovisioner.server.model.ProvisionAction;
import com.boehringer.componentprovisioner.server.model.ProvisionActionResponse;
import com.boehringer.componentprovisioner.server.security.AuthorizationInfo;
import com.boehringer.componentprovisioner.server.services.AwxService;
import com.boehringer.componentprovisioner.server.services.awx.AwxWorkflowJob;
import com.boehringer.componentprovisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    private AwxService awxService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ProvisionerActionsApiController controller;

    @Test
    void triggerProvisionAction_returnsResponseEntityWithMappedResponse_whenServiceReturnsSuccess() {
        var provisionAction = new ProvisionAction();
        provisionAction.setId("action-id");

        var workflowJobLaunch = new AwxWorkflowJobLaunch();
        var provisionActionResponse = new ProvisionActionResponse();

        when(authInfo.getCurrentPrincipalName()).thenReturn("test-user");
        when(entitiesMapper.asAwxWorkflowJobLaunch(provisionAction)).thenReturn(workflowJobLaunch);
        when(awxService.triggerWorkflowJob("action-id", workflowJobLaunch))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(new AwxWorkflowJob())));
        when(entitiesMapper.asProvisionActionResponse(any(AwxWorkflowJob.class))).thenReturn(provisionActionResponse);

        var response = controller.triggerProvisionAction(provisionAction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(provisionActionResponse, response.getBody());
    }

    @Test
    void triggerProvisionAction_returnsResponseEntityWithNullBody_whenServiceReturnsEmptyResponse() {
        var provisionAction = new ProvisionAction();
        provisionAction.setId("action-id");

        var workflowJobLaunch = new AwxWorkflowJobLaunch();

        when(authInfo.getCurrentPrincipalName()).thenReturn("test-user");
        when(entitiesMapper.asAwxWorkflowJobLaunch(provisionAction)).thenReturn(workflowJobLaunch);
        when(awxService.triggerWorkflowJob("action-id", workflowJobLaunch))
                .thenReturn(Pair.of(HttpStatus.NO_CONTENT, Optional.empty()));

        var response = controller.triggerProvisionAction(provisionAction);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

}
