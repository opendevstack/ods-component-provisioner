package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiJobTemplatesJobsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.JobCancel;
import com.boehringer.componentprovisioner.client.awx.v2.model.JobCreateSchedule;
import com.boehringer.componentprovisioner.client.awx.v2.model.JobDetail;
import com.boehringer.componentprovisioner.client.awx.v2.model.JobRelaunch;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.JobsApi")
public class JobsApi extends BaseApi {

    public JobsApi() {
        super(new ApiClient());
    }

    @Autowired
    public JobsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve aggregate statistics about job runs suitable for graphing.
     *  ## Parameters and Filtering  The &#x60;period&#x60; of the data can be adjusted with:      ?period&#x3D;month  Where &#x60;month&#x60; can be replaced with &#x60;week&#x60;, &#x60;two_weeks&#x60;, or &#x60;day&#x60;.  &#x60;month&#x60; is the default.  The type of job can be filtered with:      ?job_type&#x3D;all  Where &#x60;all&#x60; can be replaced with &#x60;inv_sync&#x60;, &#x60;playbook_run&#x60; or &#x60;scm_update&#x60;.  &#x60;all&#x60; is the default.  ## Results  Data will be returned in the following format:      \&quot;jobs\&quot;: {             \&quot;successful\&quot;: [                 [                     1402808400.0,                      9                 ], ... ],             \&quot;failed\&quot;: [              [                     1402808400.0,                      3                 ], ... ]     }  Each element contains an epoch timestamp represented in seconds and a numerical value indicating the number of events during that time period
     * <p><b>200</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiDashboardGraphsJobsList(String version) throws RestClientException {
        apiDashboardGraphsJobsListWithHttpInfo(version);
    }

    /**
     * Make a GET request to this resource to retrieve aggregate statistics about job runs suitable for graphing.
     *  ## Parameters and Filtering  The &#x60;period&#x60; of the data can be adjusted with:      ?period&#x3D;month  Where &#x60;month&#x60; can be replaced with &#x60;week&#x60;, &#x60;two_weeks&#x60;, or &#x60;day&#x60;.  &#x60;month&#x60; is the default.  The type of job can be filtered with:      ?job_type&#x3D;all  Where &#x60;all&#x60; can be replaced with &#x60;inv_sync&#x60;, &#x60;playbook_run&#x60; or &#x60;scm_update&#x60;.  &#x60;all&#x60; is the default.  ## Results  Data will be returned in the following format:      \&quot;jobs\&quot;: {             \&quot;successful\&quot;: [                 [                     1402808400.0,                      9                 ], ... ],             \&quot;failed\&quot;: [              [                     1402808400.0,                      3                 ], ... ]     }  Each element contains an epoch timestamp represented in seconds and a numerical value indicating the number of events during that time period
     * <p><b>200</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiDashboardGraphsJobsListWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiDashboardGraphsJobsList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/dashboard/graphs/jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * jobs associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job records.    ## Results  Each job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string)    ## Sorting  To specify that jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesJobsList200Response apiJobTemplatesJobsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesJobsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * jobs associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job records.    ## Results  Each job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string)    ## Sorting  To specify that jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesJobsList200Response> apiJobTemplatesJobsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesJobsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesJobsList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "search", search));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page_size", pageSize));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<ApiJobTemplatesJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiJobTemplatesJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the job can be canceled.
     * The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this job can be canceled (boolean, read-only)    # Cancel a Job Make a POST request to this resource to cancel a pending or running job.  The response status code will be 202 if successful, or 405 if the job cannot be canceled.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return JobCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobCancel apiJobsCancelCreate(String version, String id, JobCancel data) throws RestClientException {
        return apiJobsCancelCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the job can be canceled.
     * The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this job can be canceled (boolean, read-only)    # Cancel a Job Make a POST request to this resource to cancel a pending or running job.  The response status code will be 202 if successful, or 405 if the job cannot be canceled.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;JobCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobCancel> apiJobsCancelCreateWithHttpInfo(String version, String id, JobCancel data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsCancelCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsCancelCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiJobsCancelCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobCancel> localReturnType = new ParameterizedTypeReference<JobCancel>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/cancel/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the job can be canceled.
     * The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this job can be canceled (boolean, read-only)    # Cancel a Job Make a POST request to this resource to cancel a pending or running job.  The response status code will be 202 if successful, or 405 if the job cannot be canceled.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return JobCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobCancel apiJobsCancelRead(String version, String id) throws RestClientException {
        return apiJobsCancelReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the job can be canceled.
     * The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this job can be canceled (boolean, read-only)    # Cancel a Job Make a POST request to this resource to cancel a pending or running job.  The response status code will be 202 if successful, or 405 if the job cannot be canceled.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;JobCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobCancel> apiJobsCancelReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsCancelRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsCancelRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobCancel> localReturnType = new ParameterizedTypeReference<JobCancel>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/cancel/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this endpoint to create a schedule that launches
     * the job template that launched this job, and uses the same parameters that the job was launched with. These parameters include all \&quot;prompted\&quot; resources such as &#x60;extra_vars&#x60;, &#x60;inventory&#x60;, &#x60;limit&#x60;, etc.  Jobs that were launched with user-provided passwords cannot have a schedule created from them.  Make a GET request for information about what those prompts are and whether or not a schedule can be created.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return JobCreateSchedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobCreateSchedule apiJobsCreateScheduleCreate(String version, String id, JobCreateSchedule data) throws RestClientException {
        return apiJobsCreateScheduleCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a POST request to this endpoint to create a schedule that launches
     * the job template that launched this job, and uses the same parameters that the job was launched with. These parameters include all \&quot;prompted\&quot; resources such as &#x60;extra_vars&#x60;, &#x60;inventory&#x60;, &#x60;limit&#x60;, etc.  Jobs that were launched with user-provided passwords cannot have a schedule created from them.  Make a GET request for information about what those prompts are and whether or not a schedule can be created.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;JobCreateSchedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobCreateSchedule> apiJobsCreateScheduleCreateWithHttpInfo(String version, String id, JobCreateSchedule data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsCreateScheduleCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsCreateScheduleCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiJobsCreateScheduleCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobCreateSchedule> localReturnType = new ParameterizedTypeReference<JobCreateSchedule>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/create_schedule/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this endpoint to create a schedule that launches
     * the job template that launched this job, and uses the same parameters that the job was launched with. These parameters include all \&quot;prompted\&quot; resources such as &#x60;extra_vars&#x60;, &#x60;inventory&#x60;, &#x60;limit&#x60;, etc.  Jobs that were launched with user-provided passwords cannot have a schedule created from them.  Make a GET request for information about what those prompts are and whether or not a schedule can be created.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return JobCreateSchedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobCreateSchedule apiJobsCreateScheduleRead(String version, String id) throws RestClientException {
        return apiJobsCreateScheduleReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a POST request to this endpoint to create a schedule that launches
     * the job template that launched this job, and uses the same parameters that the job was launched with. These parameters include all \&quot;prompted\&quot; resources such as &#x60;extra_vars&#x60;, &#x60;inventory&#x60;, &#x60;limit&#x60;, etc.  Jobs that were launched with user-provided passwords cannot have a schedule created from them.  Make a GET request for information about what those prompts are and whether or not a schedule can be created.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;JobCreateSchedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobCreateSchedule> apiJobsCreateScheduleReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsCreateScheduleRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsCreateScheduleRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobCreateSchedule> localReturnType = new ParameterizedTypeReference<JobCreateSchedule>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/create_schedule/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field) * &#x60;custom_virtualenv&#x60;:  (string)      # Delete a Job:  Make a DELETE request to this resource to delete this job.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiJobsDelete(String version, String id) throws RestClientException {
        apiJobsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field) * &#x60;custom_virtualenv&#x60;:  (string)      # Delete a Job:  Make a DELETE request to this resource to delete this job.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiJobsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * jobs.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job records.    ## Results  Each job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string)    ## Sorting  To specify that jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesJobsList200Response apiJobsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * jobs.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job records.    ## Results  Each job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string)    ## Sorting  To specify that jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesJobsList200Response> apiJobsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "search", search));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page_size", pageSize));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<ApiJobTemplatesJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiJobTemplatesJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field) * &#x60;custom_virtualenv&#x60;:  (string)      # Delete a Job:  Make a DELETE request to this resource to delete this job.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return JobDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobDetail apiJobsRead(String version, String id) throws RestClientException {
        return apiJobsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job. (integer) * &#x60;type&#x60;: Data type for this job. (choice) * &#x60;url&#x60;: URL for this job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job was last modified. (datetime) * &#x60;name&#x60;: Name of this job. (string) * &#x60;description&#x60;: Optional description of this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check     - &#x60;scan&#x60;: Scan * &#x60;inventory&#x60;:  (id) * &#x60;project&#x60;:  (id) * &#x60;playbook&#x60;:  (string) * &#x60;scm_branch&#x60;: Branch to use in job run. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;forks&#x60;:  (integer) * &#x60;limit&#x60;:  (string) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (json) * &#x60;job_tags&#x60;:  (string) * &#x60;force_handlers&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;start_at_task&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;use_fact_cache&#x60;: If enabled, the service will act as an Ansible Fact Cache Plugin; persisting facts at the end of a playbook run to the database and caching facts for use by Ansible. (boolean) * &#x60;organization&#x60;: The organization used to determine access to this unified job. (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_template&#x60;:  (id) * &#x60;passwords_needed_to_start&#x60;:  (field) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;artifacts&#x60;:  (json) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this job, if available (string) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;diff_mode&#x60;: If enabled, textual changes made to any templated files on the host are shown in the standard output (boolean) * &#x60;job_slice_number&#x60;: If part of a sliced job, the ID of the inventory slice operated on. If not part of sliced job, parameter is not used. (integer) * &#x60;job_slice_count&#x60;: If ran as part of sliced jobs, the total number of slices. If 1, job is not part of a sliced job. (integer) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field) * &#x60;custom_virtualenv&#x60;:  (string)      # Delete a Job:  Make a DELETE request to this resource to delete this job.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;JobDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobDetail> apiJobsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobDetail> localReturnType = new ParameterizedTypeReference<JobDetail>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return JobRelaunch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobRelaunch apiJobsRelaunchCreate(String version, String id, Object data) throws RestClientException {
        return apiJobsRelaunchCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;JobRelaunch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobRelaunch> apiJobsRelaunchCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsRelaunchCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsRelaunchCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobRelaunch> localReturnType = new ParameterizedTypeReference<JobRelaunch>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/relaunch/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return JobRelaunch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobRelaunch apiJobsRelaunchRead(String version, String id) throws RestClientException {
        return apiJobsRelaunchReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;JobRelaunch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobRelaunch> apiJobsRelaunchReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsRelaunchRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsRelaunchRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<JobRelaunch> localReturnType = new ParameterizedTypeReference<JobRelaunch>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/relaunch/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
