package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_provisioner.client.awx.v2.api.JobsApi;
import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobNodesApi;
import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobTemplatesApi;
import org.opendevstack.component_provisioner.client.awx.v2.model.ApiWorkflowJobNodesList200Response;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetail;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobNodeList;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.services.exceptions.AwxClientException;
import org.opendevstack.component_provisioner.server.services.model.AwxResultNames;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AwxService {

    private static final String AWX_API_VERSION = "v2";
    @Qualifier("awxWorkflowJobTemplatesApi")
    private final WorkflowJobTemplatesApi workflowJobTemplatesApi;
    @Qualifier("awxJobsApi")
    private final JobsApi jobsApi;
    @Qualifier("awxWorkflowJobNodesApi")
    private final WorkflowJobNodesApi workflowJobNodesApi;
    private final EntitiesMapper entitiesMapper;

    public AwxService(WorkflowJobTemplatesApi workflowJobTemplatesApi, JobsApi jobsApi, WorkflowJobNodesApi workflowJobNodesApi, EntitiesMapper entitiesMapper) {
        this.workflowJobTemplatesApi = workflowJobTemplatesApi;
        this.jobsApi = jobsApi;
        this.workflowJobNodesApi = workflowJobNodesApi;
        this.entitiesMapper = entitiesMapper;
    }

    public Pair<HttpStatusCode, Optional<AwxWorkflowJob>> triggerWorkflowJob(String actionId,
                                                                             AwxWorkflowJobLaunch awxWorkflowJobLaunch) {
        log.info("Triggering workflow with id: {}, data: {}", actionId, awxWorkflowJobLaunch);

        try {
            // The version is actually not used in the generated client, due to
            // the generated uris not containing a {version} path param
            var jobData = entitiesMapper.asWorkflowJobLaunch(awxWorkflowJobLaunch);

            var response = this.workflowJobTemplatesApi
                    .apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(
                            AWX_API_VERSION,
                            awxWorkflowJobLaunch.getJobTemplateId(),
                            jobData
                    );

            var result = Optional.ofNullable(response.getBody())
                    .map(entitiesMapper::asAwxWorkflowJob);

            return Pair.of(
                    response.getStatusCode(),
                    result
            );
        } catch (HttpStatusCodeException e) {
            var errMsg = String.format(
                    "Error triggering workflow job with id: %s, data: %s, status code: %s",
                    actionId, awxWorkflowJobLaunch, e.getStatusCode()
            );

            log.error(errMsg, e);

            return Pair.of(e.getStatusCode(), Optional.empty());
        } catch (RestClientException e) {
            var errMsg = String.format(
                    "Error triggering workflow job with id: %s, data: %s",
                    actionId, awxWorkflowJobLaunch
            );

            log.error(errMsg, e);
            throw new AwxClientException(errMsg, e);
        }
    }

    public Optional<JobDetail> getWorkflowJobById(String jobId) {
        log.info("Getting workflow job with id: {}", jobId);

        try {
            var workflowNodesList = workflowJobNodesApi.apiWorkflowJobsWorkflowNodesList(AWX_API_VERSION, jobId, null, null, null);

            log.debug("WorkflowNodesList: {}", workflowNodesList);

            var innerNodesList = Optional.ofNullable(workflowNodesList)
                    .map(ApiWorkflowJobNodesList200Response::getResults).stream()
                    .flatMap(java.util.Collection::stream)
                    .map(WorkflowJobNodeList::getJob)
                    .toList();

            for (Integer nodeId:  innerNodesList) {
                var jobDetail = jobsApi.apiJobsRead(AWX_API_VERSION, nodeId.toString());

                boolean someArtifactIsAnAwxResult =
                        Optional.ofNullable(jobDetail.getArtifacts())
                                .map(Map::keySet)
                                .orElse(Collections.emptySet())
                                .stream()
                                .anyMatch(key ->
                                        Arrays.stream(AwxResultNames.values())
                                                .anyMatch(e -> e.getValue().equals(key))
                                );

                if (someArtifactIsAnAwxResult) {
                    log.debug("Found job detail with artifacts for node id: {}, job detail: {}", nodeId, jobDetail);

                    return Optional.of(jobDetail);
                }
            }

            return Optional.empty();
        } catch (HttpStatusCodeException e) {
            var errMsg = String.format(
                    "Error getting workflow job with id: %s, status code: %s",
                    jobId, e.getStatusCode()
            );

            log.error(errMsg, e);

            return Optional.empty();
        } catch (RestClientException e) {
            var errMsg = String.format(
                    "Error getting workflow job with id: %s",
                    jobId
            );

            log.error(errMsg, e);
            throw new AwxClientException(errMsg, e);
        }
    }
}
