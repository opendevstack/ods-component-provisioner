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
        assertEquals("42", result.getWorkflowJobId());
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
        assertEquals("N/A", result.getWorkflowJobId());
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
}

