package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobTemplatesApi;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJob;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AwxServiceTest {

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private WorkflowJobTemplatesApi workflowJobTemplatesApi;

    @InjectMocks
    private AwxService awxService;

    @Test
    void triggerWorkflowJob_returnsHttpStatusAndMappedWorkflowJob_whenApiCallIsSuccessful() {
        AwxWorkflowJobLaunch jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch(); // Mocked job data
        var apiResponse = new ResponseEntity<>(new WorkflowJob(), HttpStatus.OK);
        AwxWorkflowJob mappedJob = new AwxWorkflowJob();

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenReturn(apiResponse);
        when(entitiesMapper.asAwxWorkflowJob(apiResponse.getBody())).thenReturn(mappedJob);

        var result = awxService.triggerWorkflowJob("action-id", jobLaunch);

        assertEquals(HttpStatus.OK, result.getLeft());
        assertTrue(result.getRight().isPresent());
        assertEquals(mappedJob, result.getRight().get());
    }

    @Test
    void triggerWorkflowJob_returnsHttpStatusAndEmptyOptional_whenApiResponseBodyIsNull() {
        AwxWorkflowJobLaunch jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch(); // Mocked job data
        var apiResponse = new ResponseEntity<>(new WorkflowJob(), HttpStatus.OK);

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenReturn(apiResponse);

        var result = awxService.triggerWorkflowJob("action-id", jobLaunch);

        assertEquals(HttpStatus.OK, result.getLeft());
        assertTrue(result.getRight().isEmpty());
    }

    @Test
    void triggerWorkflowJob_returnsErrorStatusAndEmptyOptional_whenHttpStatusCodeExceptionOccurs() {
        AwxWorkflowJobLaunch jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch(); // Mocked job data
        HttpStatusCodeException exception = mock(HttpStatusCodeException.class);

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenThrow(exception);
        when(exception.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        var result = awxService.triggerWorkflowJob("action-id", jobLaunch);

        assertEquals(HttpStatus.BAD_REQUEST, result.getLeft());
        assertTrue(result.getRight().isEmpty());
    }

    @Test
    void triggerWorkflowJob_throwsRuntimeException_whenRestClientExceptionOccurs() {
        AwxWorkflowJobLaunch jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch(); // Mocked job data
        RestClientException exception = new RestClientException("Error");

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenThrow(exception);

        assertThrows(RuntimeException.class, () -> awxService.triggerWorkflowJob("action-id", jobLaunch));
    }
}