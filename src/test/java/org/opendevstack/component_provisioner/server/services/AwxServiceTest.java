package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.awx.v2.api.JobsApi;
import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobNodesApi;
import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobTemplatesApi;
import org.opendevstack.component_provisioner.client.awx.v2.model.ApiWorkflowJobNodesList200ResponseMother;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetailMother;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJob;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobLaunch;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobNodeList;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobNodeListMother;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.services.exceptions.AwxClientException;
import org.opendevstack.component_provisioner.server.services.model.AwxResultNames;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AwxServiceTest {

    private static final String AWX_API_VERSION = "v2";

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private WorkflowJobTemplatesApi workflowJobTemplatesApi;

    @Mock
    private WorkflowJobNodesApi workflowJobNodesApi;

    @Mock
    private JobsApi jobsApi;

    @InjectMocks
    private AwxService awxService;

    @Test
    void givenSuccessfulApiCall_whenTriggerWorkflowJob_thenReturnsMappedWorkflowJob() {
        // given
        var jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch();
        var apiResponse = new ResponseEntity<>(new WorkflowJob(), HttpStatus.OK);
        var mappedJob = new AwxWorkflowJob();

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenReturn(apiResponse);
        when(entitiesMapper.asAwxWorkflowJob(apiResponse.getBody())).thenReturn(mappedJob);

        // when
        var result = awxService.triggerWorkflowJob("action-id", jobLaunch);

        // then
        assertThat(result.getLeft()).isEqualTo(HttpStatus.OK);
        assertThat(result.getRight()).isPresent();
        assertThat(result.getRight().get()).isEqualTo(mappedJob);
    }

    @Test
    void givenNullApiResponseBody_whenTriggerWorkflowJob_thenReturnsEmptyOptional() {
        // given
        var jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch();
        var apiResponse = new ResponseEntity<>(new WorkflowJob(), HttpStatus.OK);

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenReturn(apiResponse);

        // when
        var result = awxService.triggerWorkflowJob("action-id", jobLaunch);

        // then
        assertThat(result.getLeft()).isEqualTo(HttpStatus.OK);
        assertThat(result.getRight()).isEmpty();
    }

    @Test
    void givenHttpStatusCodeException_whenTriggerWorkflowJob_thenReturnsErrorStatusAndEmptyOptional() {
        // given
        var jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch();
        HttpStatusCodeException exception = mock(HttpStatusCodeException.class);

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenThrow(exception);
        when(exception.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        // when
        var result = awxService.triggerWorkflowJob("action-id", jobLaunch);

        // then
        assertThat(result.getLeft()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getRight()).isEmpty();
    }

    @Test
    void givenRestClientException_whenTriggerWorkflowJob_thenThrowsRuntimeException() {
        // given
        var jobLaunch = new AwxWorkflowJobLaunch();
        jobLaunch.setJobTemplateId("template-id");

        var jobData = new WorkflowJobLaunch();
        var exception = new RestClientException("Error");

        when(entitiesMapper.asWorkflowJobLaunch(jobLaunch)).thenReturn(jobData);
        when(workflowJobTemplatesApi.apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                "v2", "template-id", jobData)).thenThrow(exception);

        // when / then
        assertThatThrownBy(() -> awxService.triggerWorkflowJob("action-id", jobLaunch))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void givenJobId_whenGetWorkflowJobByIdSucceeds_thenReturnsJobDetail() {
        // given
        var workflowJobId = "workflow-job-id";
        var jobId = "12345";
        var jobDetail = JobDetailMother.of();
        List<WorkflowJobNodeList> results = Collections.singletonList(WorkflowJobNodeListMother.of(Integer.valueOf(jobId)));
        var workflowJobNodesResponse = ApiWorkflowJobNodesList200ResponseMother.of(results);

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(AWX_API_VERSION, workflowJobId, null, null, null))
                .thenReturn(workflowJobNodesResponse);
        when(jobsApi.apiJobsRead(AWX_API_VERSION, jobId)).thenReturn(jobDetail);

        // when
        var result = awxService.getWorkflowJobById(workflowJobId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(jobDetail);
    }

    @Test
    void givenJobId_whenGetWorkflowJobByIdReturnsNull_thenReturnsEmptyOptional() {
        // given
        var jobId = "job-123";

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(AWX_API_VERSION, jobId, null, null, null))
                .thenReturn(null);

        // when
        var result = awxService.getWorkflowJobById(jobId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenJobId_whenHttpStatusCodeExceptionOccurs_thenReturnsEmptyOptional() {
        // given
        var jobId = "job-123";
        HttpStatusCodeException exception = mock(HttpStatusCodeException.class);

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(AWX_API_VERSION, jobId, null, null, null))
                .thenThrow(exception);
        when(exception.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        // when
        var result = awxService.getWorkflowJobById(jobId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenJobId_whenRestClientExceptionOccurs_thenThrowsAwxClientException() {
        // given
        var jobId = "job-123";
        var exception = new RestClientException("Connection error");

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(AWX_API_VERSION, jobId, null, null, null))
                .thenThrow(exception);

        // when / then
        assertThatThrownBy(() -> awxService.getWorkflowJobById(jobId))
                .isInstanceOf(AwxClientException.class);
    }

    @Test
    void givenWorkflowNodesWithNullJobId_whenGetWorkflowJobById_thenIgnoresNullAndReturnsEmpty() {
        // given
        var workflowJobId = "wf-id";

        var nodeWithNull = new WorkflowJobNodeList();
        nodeWithNull.setJob(null);

        var response = ApiWorkflowJobNodesList200ResponseMother.of(List.of(nodeWithNull));

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(
                AWX_API_VERSION, workflowJobId, null, null, null)).thenReturn(response);

        // when
        var result = awxService.getWorkflowJobById(workflowJobId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenNullAndValidNodes_whenGetWorkflowJobById_thenSkipsNullAndReturnsValidJobDetail() {
        // given
        var workflowJobId = "wf-id";
        var validJobId = "123";

        var nullNode = new WorkflowJobNodeList();
        nullNode.setJob(null);

        var validNode = WorkflowJobNodeListMother.of(Integer.valueOf(validJobId));

        var response = ApiWorkflowJobNodesList200ResponseMother.of(List.of(nullNode, validNode));

        var jobDetail = JobDetailMother.of().toBuilder()
                .artifacts(Map.of(AwxResultNames.RESULT_CODE.getValue(), "value"))
                .build();

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(
                AWX_API_VERSION, workflowJobId, null, null, null)).thenReturn(response);
        when(jobsApi.apiJobsRead(AWX_API_VERSION, validJobId)).thenReturn(jobDetail);

        // when
        var result = awxService.getWorkflowJobById(workflowJobId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(jobDetail);
    }

    @Test
    void givenAllNodesNull_whenGetWorkflowJobById_thenJobsApiIsNeverCalled() {
        // given
        var workflowJobId = "wf-id";

        var n1 = new WorkflowJobNodeList();
        n1.setJob(null);

        var n2 = new WorkflowJobNodeList();
        n2.setJob(null);

        var response = ApiWorkflowJobNodesList200ResponseMother.of(List.of(n1, n2));

        when(workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(
                AWX_API_VERSION, workflowJobId, null, null, null)).thenReturn(response);

        // when
        var result = awxService.getWorkflowJobById(workflowJobId);

        // then
        assertThat(result).isEmpty();
        verifyNoInteractions(jobsApi);
    }
}

