package org.opendevstack.component_provisioner.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetailMother;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfoMother;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntitiesMapperTest {

    private EntitiesMapper entitiesMapper;

    @BeforeEach
    void setUp() {
        entitiesMapper = new EntitiesMapper(new com.fasterxml.jackson.databind.ObjectMapper());
    }

    @Test
    void givenValidJobDetail_whenAsProjectComponentProvisionStatusIsCalled_thenMapsAllFields() {
        // given
        var artifacts = new HashMap<String, String>();
        artifacts.put("result_code", "SUCCESS");
        artifacts.put("result_output", "Component provisioned successfully");

        var jobDetail = JobDetailMother.of(42, artifacts);
        var projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of();

        // when
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getComponentId()).isEqualTo("comp-123");
        assertThat(result.getStatus()).isEqualTo(org.opendevstack.component_provisioner.server.model.ProvisioningStatus.CREATED);
        assertThat(result.getProjectKey()).isEqualTo("TEST_PROJECT");
        assertThat(result.getWorkflowJobId()).isEqualTo("12345");
        assertThat(result.getErrorMessage()).isEqualTo("SUCCESS");
        assertThat(result.getErrorTask()).isEqualTo("Component provisioned successfully");
    }

    @Test
    void givenJobDetailWithNullId_whenAsProjectComponentProvisionStatusIsCalled_thenUsesWorkflowJobId() {
        // given
        var jobDetail = JobDetailMother.of(null, new HashMap<>());
        var projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of();

        // when
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // then
        assertThat(result.getWorkflowJobId()).isEqualTo("12345");
    }

    @Test
    void givenJobDetailWithNullArtifacts_whenAsProjectComponentProvisionStatusIsCalled_thenUsesNaDefaults() {
        // given
        var jobDetail = JobDetailMother.of(1, null);
        var projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of();

        // when
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // then
        assertThat(result.getErrorMessage()).isEqualTo("N/A");
        assertThat(result.getErrorTask()).isEqualTo("N/A");
    }

    @Test
    void givenJobDetailWithMissingArtifactKeys_whenAsProjectComponentProvisionStatusIsCalled_thenUsesNaDefaults() {
        // given
        var artifacts = new HashMap<String, String>();
        var jobDetail = JobDetailMother.of(99, artifacts);
        var projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of(ProvisioningStatus.FAILED);

        // when
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // then
        assertThat(result.getErrorMessage()).isEqualTo("N/A");
        assertThat(result.getErrorTask()).isEqualTo("N/A");
    }

    @Test
    void givenServerUpdateRequest_whenAsClientProvisioningStatusUpdateRequest_thenMapsAllFields() {
        // given
        var parameter = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequestAllOfParameters();
        parameter.setName("env");
        parameter.setValues(java.util.List.of("dev", "prod"));

        var serverRequest = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest();
        serverRequest.setComponentId("comp-1");
        serverRequest.setCatalogItemId("cat-1");
        serverRequest.componentUrl("http://example.com");
        serverRequest.workflowJobId("wf-1");
        serverRequest.setParameters(java.util.List.of(parameter));

        // when
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(serverRequest);

        // then
        assertThat(clientRequest).isNotNull();
        assertThat(clientRequest.getComponentId()).isEqualTo("comp-1");
        assertThat(clientRequest.getCatalogItemId()).isEqualTo("cat-1");
        assertThat(clientRequest.getComponentUrl()).isEqualTo("http://example.com");
        assertThat(clientRequest.getWorkflowJobId()).isEqualTo("wf-1");
        assertThat(clientRequest.getParameters()).hasSize(1);
        assertThat(clientRequest.getParameters().getFirst().getName()).isEqualTo("env");
        assertThat(clientRequest.getParameters().getFirst().getValues()).isEqualTo(java.util.List.of("dev", "prod"));
    }

    @Test
    void givenServerRequestWithNullParameters_whenAsClientProvisioningStatusUpdateRequest_thenReturnsEmptyList() {
        // given
        var serverRequest = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest();
        serverRequest.setComponentId("comp-1");
        serverRequest.setCatalogItemId("cat-1");
        serverRequest.setParameters(null);

        // when
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(serverRequest);

        // then
        assertThat(clientRequest.getParameters()).isNotNull();
        assertThat(clientRequest.getParameters()).isEmpty();
    }

    @Test
    void givenServerPartialUpdateRequest_whenAsClientProvisioningStatusUpdateRequest_thenMapsAllFields() {
        // given
        var serverRequest = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest();
        serverRequest.setComponentId("comp-2");
        serverRequest.setCatalogItemId("cat-2");
        serverRequest.componentUrl("http://example.org");

        // when
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(serverRequest);

        // then
        assertThat(clientRequest).isNotNull();
        assertThat(clientRequest.getComponentId()).isEqualTo("comp-2");
        assertThat(clientRequest.getCatalogItemId()).isEqualTo("cat-2");
        assertThat(clientRequest.getComponentUrl()).isEqualTo("http://example.org");
    }

    @Test
    void givenProjectComponentExtendedInfo_whenAsClientProvisioningStatusUpdateRequest_thenMapsAllFields() {
        // given
        var source = ProjectComponentExtendedInfo.builder()
                .componentId("comp-3")
                .catalogItemId("cat-3")
                .componentUrl("http://example.net")
                .workflowJobId("wf-3")
                .deletionWorkflowJobId("del-wf-3")
                .parameters(List.of(ProjectComponentParameter.builder()
                        .name("region")
                        .values(List.of("eu", "us"))
                        .build()))
                .build();

        // when
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(source);

        // then
        assertThat(clientRequest).isNotNull();
        assertThat(clientRequest.getComponentId()).isEqualTo("comp-3");
        assertThat(clientRequest.getCatalogItemId()).isEqualTo("cat-3");
        assertThat(clientRequest.getComponentUrl()).isEqualTo("http://example.net");
        assertThat(clientRequest.getWorkflowJobId()).isEqualTo("wf-3");
        assertThat(clientRequest.getDeletionWorkflowJobId()).isEqualTo("del-wf-3");
        assertThat(clientRequest.getParameters()).hasSize(1);
        assertThat(clientRequest.getParameters().getFirst().getName()).isEqualTo("region");
        assertThat(clientRequest.getParameters().getFirst().getValues()).isEqualTo(List.of("eu", "us"));
    }

    @Test
    void givenProjectComponentWithNullParameters_whenAsClientProvisioningStatusUpdateRequest_thenReturnsEmptyList() {
        // given
        var source = new ProjectComponentExtendedInfo();
        source.setComponentId("comp-4");
        source.setCatalogItemId("cat-4");
        source.setParameters(null);

        // when
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(source);

        // then
        assertThat(clientRequest.getParameters()).isNotNull();
        assertThat(clientRequest.getParameters()).isEmpty();
    }

    @Test
    void givenValidInput_whenAsProvisioningDeleteRequestParametersInner_thenMapsProperly() {
        // given
        var input = new org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequestParametersInner();

        // when
        var output = entitiesMapper.asProvisioningDeleteRequestParametersInner(input);

        // then
        assertThat(output).isNotNull();
        assertThat(output.getName()).isEqualTo(input.getName());
        assertThat(output.getValues()).isEqualTo(input.getValues());
    }
}

