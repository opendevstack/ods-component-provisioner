package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfoMother;
import org.opendevstack.component_provisioner.server.facade.ProjectComponentsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatusMother;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectComponentsApiControllerTest {

    @Mock
    private ProjectComponentsApiFacade projectComponentsApiFacade;

    @InjectMocks
    private ProjectComponentsApiController controller;

    @Test
    void givenValidProjectKeyAndComponentId_whenGetProjectComponentProvisionStatusById_thenReturnsOkWithStatus() {
        // given
        var projectKey = "TEST";
        var componentId = "comp-123";
        var expectedStatus = ProjectComponentProvisionStatusMother.of();
        var projectComponentExtendedInfo = ProjectComponentExtendedInfoMother.of();

        when(projectComponentsApiFacade.getProjectComponentById(projectKey, componentId)).thenReturn(projectComponentExtendedInfo);
        when(projectComponentsApiFacade.enrichWithAapInfo(projectKey, projectComponentExtendedInfo))
                .thenReturn(expectedStatus);

        // when
        ResponseEntity<ProjectComponentProvisionStatus> result = controller.getProjectComponentProvisionStatusById(projectKey, componentId);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(expectedStatus);
        verify(projectComponentsApiFacade).getProjectComponentById(projectKey, componentId);
        verify(projectComponentsApiFacade).enrichWithAapInfo(projectKey, projectComponentExtendedInfo);
    }

    @Test
    void givenProjectKeyAndComponentId_whenGetProjectComponentProvisionStatusById_thenEnrichesWithAapInfo() {
        // given
        String projectKey = "TEST";
        String componentId = "comp-123";
        ProjectComponentProvisionStatus baseStatus = ProjectComponentProvisionStatusMother.of();
        var projectComponentExtendedInfo = ProjectComponentExtendedInfoMother.of();

        when(projectComponentsApiFacade.getProjectComponentById(projectKey, componentId))
                .thenReturn(projectComponentExtendedInfo);
        when(projectComponentsApiFacade.enrichWithAapInfo(projectKey, projectComponentExtendedInfo))
                .thenReturn(baseStatus);

        // when
        ResponseEntity<ProjectComponentProvisionStatus> result = controller.getProjectComponentProvisionStatusById(projectKey, componentId);

        // then
        assertThat(result.getBody()).isEqualTo(baseStatus);
        verify(projectComponentsApiFacade).enrichWithAapInfo(projectKey, projectComponentExtendedInfo);
    }
}
