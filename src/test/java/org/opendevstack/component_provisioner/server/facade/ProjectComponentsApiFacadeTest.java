package org.opendevstack.component_provisioner.server.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetailMother;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfoMother;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectComponentsApiFacadeTest {

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private AwxService awxService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ProjectComponentsApiFacade projectComponentsApiFacade;

    @Test
    void givenProjectKeyAndComponentId_whenGetProjectComponentById_thenReturnComponentInfo() {
        // given
        var projectKey = "test-project";
        var componentId = "test-component-id";
        var accessToken = "test-token";
        var expectedInfo = ProjectComponentExtendedInfoMother.of();

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(expectedInfo);

        // when
        ProjectComponentExtendedInfo result = projectComponentsApiFacade.getProjectComponentById(projectKey, componentId);

        // then
        assertThat(result).isEqualTo(expectedInfo);
        verify(authenticationProvider).getAccessToken();
        verify(componentCatalogService).getProjectComponentById(accessToken, projectKey, componentId);
    }

    @Test
    void givenProjectKeyAndComponentInfo_whenEnrichWithAapInfo_thenReturnProvisionStatus() {
        // given
        var projectKey = "test-project";
        var componentInfo = ProjectComponentExtendedInfoMother.of();
        var jobDetail = JobDetailMother.of();

        ProjectComponentProvisionStatus expectedStatus = new ProjectComponentProvisionStatus();

        when(awxService.getWorkflowJobById("12345")).thenReturn(Optional.of(jobDetail));
        when(entitiesMapper.asProjectComponentProvisionStatus(projectKey, componentInfo, jobDetail))
                .thenReturn(expectedStatus);

        // when
        ProjectComponentProvisionStatus result = projectComponentsApiFacade.enrichWithAapInfo(projectKey, componentInfo);

        // then
        assertThat(result).isEqualTo(expectedStatus);
        verify(awxService).getWorkflowJobById("12345");
        verify(entitiesMapper).asProjectComponentProvisionStatus(projectKey, componentInfo, jobDetail);
    }
}
