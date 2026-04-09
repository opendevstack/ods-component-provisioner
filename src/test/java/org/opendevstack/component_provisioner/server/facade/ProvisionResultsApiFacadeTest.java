package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfoMother;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentActionMother;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiFacadeTest {

    @Mock
    private AwxService awxService;
    @Mock
    private ComponentCatalogService componentCatalogService;
    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ProvisionResultsApiFacade facade;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(facade, "workflowJobId", "WORKFLOW_123");
    }

    @Test
    void requestProvisionToAwx_mapsResponseCorrectly() {
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);
        when(awxService.triggerWorkflowJob("CREATE_INCIDENT", launch)).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        var result = facade.requestProvisionToAwx("PRJ", "CID", action);

        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());
    }

    @Test
    void isInDeletingState_returnsTrueWhenMatchingComponentFound() {
        var action = CreateIncidentActionMother.of();
        var accessToken = action.getParameters().stream().filter(p -> p.getName().equals("access_token")).map(CreateIncidentParameter::getValue).map(Object::toString).findFirst().orElseThrow();
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.DELETING);
        when(componentCatalogService.getProjectComponents("PRJ", "ID", accessToken)).thenReturn(List.of(pc));

        var result = facade.isInDeletingState("PRJ", "componentId", "ID", action);
        assertThat(result).isTrue();
    }

    @Test
    void validate_status_throwsOnInvalid() {
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "invalid"));
        assertThat(ex.getMessage()).contains("Status is not valid");
    }

    @Test
    void validate_createIncident_throwsOnMissingFields() {
        var invalid = CreateIncidentAction.builder().build();
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CID", invalid));
        assertThat(ex.getMessage()).contains("caller, cluster_location");
    }
}
