package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.awx.v2.api.JobsApi;
import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobTemplatesApi;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetailMother;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJob;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.services.exceptions.AwxClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AwxServiceTest {

    private static final String AWX_API_VERSION = "v2";
    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private WorkflowJobTemplatesApi workflowJobTemplatesApi;

    @Mock
    private JobsApi jobsApi;

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

    @Test
    void givenJobId_whenGetWorkflowJobByIdSucceeds_thenReturnsJobDetail() {
        // given
        var jobDetail = JobDetailMother.of();

        when(jobsApi.apiJobsRead(AWX_API_VERSION, "12345")).thenReturn(jobDetail);

        // when
        var result = awxService.getWorkflowJobById("12345");

        // then
        assertTrue(result.isPresent());
        assertEquals(jobDetail, result.get());
    }

    @Test
    void givenJobId_whenGetWorkflowJobByIdReturnsNull_thenReturnsEmptyOptional() {
        // given
        String jobId = "job-123";

        when(jobsApi.apiJobsRead(AWX_API_VERSION, jobId)).thenReturn(null);

        // when
        var result = awxService.getWorkflowJobById(jobId);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenJobId_whenHttpStatusCodeExceptionOccurs_thenReturnsEmptyOptional() {
        // given
        String jobId = "job-123";
        HttpStatusCodeException exception = mock(HttpStatusCodeException.class);

        when(jobsApi.apiJobsRead(AWX_API_VERSION, jobId)).thenThrow(exception);
        when(exception.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        // when
        var result = awxService.getWorkflowJobById(jobId);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenJobId_whenRestClientExceptionOccurs_thenThrowsAwxClientException() {
        // given
        String jobId = "job-123";
        RestClientException exception = new RestClientException("Connection error");

        when(jobsApi.apiJobsRead(AWX_API_VERSION, jobId)).thenThrow(exception);

        // when & then
        assertThrows(AwxClientException.class, () -> awxService.getWorkflowJobById(jobId));
    }

}