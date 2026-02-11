package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.awx.v2.api.WorkflowJobTemplatesApi;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.services.exceptions.AwxClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
@Slf4j
public class AwxService {

    private static final String AWX_API_VERSION = "v2";
    @Qualifier("awxWorkflowJobTemplatesApi")
    private final WorkflowJobTemplatesApi workflowJobTemplatesApi;
    private final EntitiesMapper entitiesMapper;

    public AwxService(WorkflowJobTemplatesApi workflowJobTemplatesApi, EntitiesMapper entitiesMapper) {
        this.workflowJobTemplatesApi = workflowJobTemplatesApi;
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
}
