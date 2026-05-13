package org.opendevstack.component_provisioner.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetailMother;
import org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfoMother;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntitiesMapperTest {

    private EntitiesMapper entitiesMapper;

    @BeforeEach
    void setUp() {
        entitiesMapper = new EntitiesMapper(new com.fasterxml.jackson.databind.ObjectMapper());
    }

    @Test
    void testAsProjectComponentProvisionStatusWithValidData() {
        // Arrange
        var artifacts = new HashMap<String, String>();
        artifacts.put("result_code", "SUCCESS");
        artifacts.put("result_output", "Component provisioned successfully");

        var jobDetail = JobDetailMother.of(42, artifacts);

        String projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of();

        // Act
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // Assert
        assertNotNull(result);
        assertEquals("comp-123", result.getComponentId());
        assertEquals("PROVISIONED", result.getStatus());
        assertEquals("TEST_PROJECT", result.getProjectKey());
        assertEquals("12345", result.getWorkflowJobId());
        assertEquals("SUCCESS", result.getErrorMessage());
        assertEquals("Component provisioned successfully", result.getErrorTask());
    }

    @Test
    void testAsProjectComponentProvisionStatusWithNullJobDetailId() {
        // Arrange
        var jobDetail = JobDetailMother.of(null, new HashMap<>());

        String projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of();

        // Act
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // Assert
        assertEquals("12345", result.getWorkflowJobId());
    }

    @Test
    void testAsProjectComponentProvisionStatusWithNullArtifacts() {
        // Arrange
        var jobDetail = JobDetailMother.of(1, null);

        String projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of();

        // Act
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // Assert
        assertEquals("N/A", result.getErrorMessage());
        assertEquals("N/A", result.getErrorTask());
    }

    @Test
    void testAsProjectComponentProvisionStatusWithMissingArtifactKeys() {
        // Arrange
        var artifacts = new HashMap<String, String>();
        var jobDetail = JobDetailMother.of(99, artifacts);

        String projectKey = "TEST_PROJECT";
        var projectComponentInfo = ProjectComponentExtendedInfoMother.of("FAILED");

        // Act
        ProjectComponentProvisionStatus result = entitiesMapper.asProjectComponentProvisionStatus(
                projectKey, projectComponentInfo, jobDetail);

        // Assert
        assertEquals("N/A", result.getErrorMessage());
        assertEquals("N/A", result.getErrorTask());
    }

    @Test
    void givenAServerProvisioningStatusUpdateRequest_whenAsClientProvisioningStatusUpdateRequestIsCalled_thenMapsAllFields() {
        // Arrange
        var parameter = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequestAllOfParameters();
        parameter.setName("env");
        parameter.setValues(java.util.List.of("dev", "prod"));

        var serverRequest = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest();
        serverRequest.setComponentId("comp-1");
        serverRequest.setCatalogItemId("cat-1");
        serverRequest.componentUrl("http://example.com");
        serverRequest.workflowJobId("wf-1");
        serverRequest.setParameters(java.util.List.of(parameter));

        // Act
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(serverRequest);

        // Assert
        assertNotNull(clientRequest);
        assertEquals("comp-1", clientRequest.getComponentId());
        assertEquals("cat-1", clientRequest.getCatalogItemId());
        assertEquals("http://example.com", clientRequest.getComponentUrl());
        assertEquals("wf-1", clientRequest.getWorkflowJobId());
        assertEquals(1, clientRequest.getParameters().size());
        assertEquals("env", clientRequest.getParameters().get(0).getName());
        assertEquals(java.util.List.of("dev", "prod"), clientRequest.getParameters().get(0).getValues());
    }

    @Test
    void givenAServerProvisioningStatusUpdateRequestWithNullParameters_whenAsClientProvisioningStatusUpdateRequestIsCalled_thenReturnsEmptyList() {
        // Arrange
        var serverRequest = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest();
        serverRequest.setComponentId("comp-1");
        serverRequest.setCatalogItemId("cat-1");
        serverRequest.setParameters(null);

        // Act
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(serverRequest);

        // Assert
        assertNotNull(clientRequest.getParameters());
        assertEquals(0, clientRequest.getParameters().size());
    }

    @Test
    void givenAServerProvisioningStatusPartialUpdateRequest_whenAsClientProvisioningStatusUpdateRequestIsCalled_thenMapsAllFields() {
        // Arrange
        var serverRequest = new org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest();
        serverRequest.setComponentId("comp-2");
        serverRequest.setCatalogItemId("cat-2");
        serverRequest.componentUrl("http://example.org");

        // Act
        var clientRequest = entitiesMapper.asClientProvisioningStatusUpdateRequest(serverRequest);

        // Assert
        assertNotNull(clientRequest);
        assertEquals("comp-2", clientRequest.getComponentId());
        assertEquals("cat-2", clientRequest.getCatalogItemId());
        assertEquals("http://example.org", clientRequest.getComponentUrl());
    }
}

