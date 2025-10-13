package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiBulkJobLaunchList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiJobTemplatesCallbackList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiJobTemplatesSliceWorkflowJobsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.BulkJobLaunch;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJob;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobCancel;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobList;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.WorkflowJobsApi")
public class WorkflowJobsApi extends BaseApi {

    public WorkflowJobsApi() {
        super(new ApiClient());
    }

    @Autowired
    public WorkflowJobsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * This endpoint allows the client to launch multiple UnifiedJobTemplates at a time, along side any launch time parameters that they would normally set at launch time.
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return BulkJobLaunch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BulkJobLaunch apiBulkJobLaunchCreate(String version, BulkJobLaunch data) throws RestClientException {
        return apiBulkJobLaunchCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * This endpoint allows the client to launch multiple UnifiedJobTemplates at a time, along side any launch time parameters that they would normally set at launch time.
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;BulkJobLaunch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BulkJobLaunch> apiBulkJobLaunchCreateWithHttpInfo(String version, BulkJobLaunch data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiBulkJobLaunchCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiBulkJobLaunchCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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

        ParameterizedTypeReference<BulkJobLaunch> localReturnType = new ParameterizedTypeReference<BulkJobLaunch>() {};
        return apiClient.invokeAPI("/api/v2/bulk/job_launch/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * This endpoint allows the client to launch multiple UnifiedJobTemplates at a time, along side any launch time parameters that they would normally set at launch time.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiBulkJobLaunchList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiBulkJobLaunchList200Response apiBulkJobLaunchList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiBulkJobLaunchListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * This endpoint allows the client to launch multiple UnifiedJobTemplates at a time, along side any launch time parameters that they would normally set at launch time.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiBulkJobLaunchList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiBulkJobLaunchList200Response> apiBulkJobLaunchListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiBulkJobLaunchList");
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

        ParameterizedTypeReference<ApiBulkJobLaunchList200Response> localReturnType = new ParameterizedTypeReference<ApiBulkJobLaunchList200Response>() {};
        return apiClient.invokeAPI("/api/v2/bulk/job_launch/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow jobs associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJobList
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobList apiJobTemplatesSliceWorkflowJobsCreate(String version, String id, WorkflowJobList data) throws RestClientException {
        return apiJobTemplatesSliceWorkflowJobsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow jobs associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobList&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobList> apiJobTemplatesSliceWorkflowJobsCreateWithHttpInfo(String version, String id, WorkflowJobList data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesSliceWorkflowJobsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesSliceWorkflowJobsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiJobTemplatesSliceWorkflowJobsCreate");
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

        ParameterizedTypeReference<WorkflowJobList> localReturnType = new ParameterizedTypeReference<WorkflowJobList>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/slice_workflow_jobs/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow jobs associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesSliceWorkflowJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesSliceWorkflowJobsList200Response apiJobTemplatesSliceWorkflowJobsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesSliceWorkflowJobsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow jobs associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesSliceWorkflowJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesSliceWorkflowJobsList200Response> apiJobTemplatesSliceWorkflowJobsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesSliceWorkflowJobsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesSliceWorkflowJobsList");
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

        ParameterizedTypeReference<ApiJobTemplatesSliceWorkflowJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiJobTemplatesSliceWorkflowJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/slice_workflow_jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow jobs associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesSliceWorkflowJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesSliceWorkflowJobsList200Response apiWorkflowJobTemplatesWorkflowJobsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesWorkflowJobsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow jobs associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesSliceWorkflowJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesSliceWorkflowJobsList200Response> apiWorkflowJobTemplatesWorkflowJobsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesWorkflowJobsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesWorkflowJobsList");
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

        ParameterizedTypeReference<ApiJobTemplatesSliceWorkflowJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiJobTemplatesSliceWorkflowJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/workflow_jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the workflow job can be
     * canceled. The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this workflow job is in a state that can   be canceled (boolean, read-only)  Make a POST request to this endpoint to submit a request to cancel a pending or running workflow job.  The response status code will be 202 if the request to cancel was successfully submitted, or 405 if the workflow job cannot be canceled.
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJobCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobCancel apiWorkflowJobsCancelCreate(String version, String id, WorkflowJobCancel data) throws RestClientException {
        return apiWorkflowJobsCancelCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the workflow job can be
     * canceled. The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this workflow job is in a state that can   be canceled (boolean, read-only)  Make a POST request to this endpoint to submit a request to cancel a pending or running workflow job.  The response status code will be 202 if the request to cancel was successfully submitted, or 405 if the workflow job cannot be canceled.
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobCancel> apiWorkflowJobsCancelCreateWithHttpInfo(String version, String id, WorkflowJobCancel data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsCancelCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsCancelCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobsCancelCreate");
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

        ParameterizedTypeReference<WorkflowJobCancel> localReturnType = new ParameterizedTypeReference<WorkflowJobCancel>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/cancel/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the workflow job can be
     * canceled. The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this workflow job is in a state that can   be canceled (boolean, read-only)  Make a POST request to this endpoint to submit a request to cancel a pending or running workflow job.  The response status code will be 202 if the request to cancel was successfully submitted, or 405 if the workflow job cannot be canceled.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJobCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobCancel apiWorkflowJobsCancelRead(String version, String id) throws RestClientException {
        return apiWorkflowJobsCancelReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the workflow job can be
     * canceled. The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this workflow job is in a state that can   be canceled (boolean, read-only)  Make a POST request to this endpoint to submit a request to cancel a pending or running workflow job.  The response status code will be 202 if the request to cancel was successfully submitted, or 405 if the workflow job cannot be canceled.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJobCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobCancel> apiWorkflowJobsCancelReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsCancelRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsCancelRead");
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

        ParameterizedTypeReference<WorkflowJobCancel> localReturnType = new ParameterizedTypeReference<WorkflowJobCancel>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/cancel/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Delete a Workflow Job:  Make a DELETE request to this resource to delete this workflow job.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiWorkflowJobsDelete(String version, String id) throws RestClientException {
        apiWorkflowJobsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Delete a Workflow Job:  Make a DELETE request to this resource to delete this workflow job.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiWorkflowJobsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsDelete");
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
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow jobs.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesSliceWorkflowJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesSliceWorkflowJobsList200Response apiWorkflowJobsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow jobs.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job records.    ## Results  Each workflow job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesSliceWorkflowJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesSliceWorkflowJobsList200Response> apiWorkflowJobsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsList");
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

        ParameterizedTypeReference<ApiJobTemplatesSliceWorkflowJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiJobTemplatesSliceWorkflowJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Delete a Workflow Job:  Make a DELETE request to this resource to delete this workflow job.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJob apiWorkflowJobsRead(String version, String id) throws RestClientException {
        return apiWorkflowJobsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job. (integer) * &#x60;type&#x60;: Data type for this workflow job. (choice) * &#x60;url&#x60;: URL for this workflow job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job. (string) * &#x60;description&#x60;: Optional description of this workflow job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;extra_vars&#x60;:  (json) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;job_template&#x60;: If automatically created for a sliced job run, the job template the workflow job was created from. (id) * &#x60;is_sliced_job&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;webhook_guid&#x60;: Unique identifier of the event that triggered this webhook (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Delete a Workflow Job:  Make a DELETE request to this resource to delete this workflow job.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJob> apiWorkflowJobsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsRead");
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

        ParameterizedTypeReference<WorkflowJob> localReturnType = new ParameterizedTypeReference<WorkflowJob>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this endpoint to launch a workflow job identical to the parent workflow job. This will spawn jobs, project updates, or inventory updates based on the unified job templates referenced in the workflow nodes in the workflow job. No POST data is accepted for this action.
     * If successful, the response status code will be 201 and serialized data of the new workflow job will be returned.
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiWorkflowJobsRelaunchCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobsRelaunchCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a POST request to this endpoint to launch a workflow job identical to the parent workflow job. This will spawn jobs, project updates, or inventory updates based on the unified job templates referenced in the workflow nodes in the workflow job. No POST data is accepted for this action.
     * If successful, the response status code will be 201 and serialized data of the new workflow job will be returned.
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiWorkflowJobsRelaunchCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsRelaunchCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsRelaunchCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobsRelaunchCreate");
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

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/relaunch/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this endpoint to launch a workflow job identical to the parent workflow job. This will spawn jobs, project updates, or inventory updates based on the unified job templates referenced in the workflow nodes in the workflow job. No POST data is accepted for this action.
     * If successful, the response status code will be 201 and serialized data of the new workflow job will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesCallbackList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesCallbackList200Response apiWorkflowJobsRelaunchList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobsRelaunchListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a POST request to this endpoint to launch a workflow job identical to the parent workflow job. This will spawn jobs, project updates, or inventory updates based on the unified job templates referenced in the workflow nodes in the workflow job. No POST data is accepted for this action.
     * If successful, the response status code will be 201 and serialized data of the new workflow job will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesCallbackList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesCallbackList200Response> apiWorkflowJobsRelaunchListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsRelaunchList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsRelaunchList");
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

        ParameterizedTypeReference<ApiJobTemplatesCallbackList200Response> localReturnType = new ParameterizedTypeReference<ApiJobTemplatesCallbackList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/relaunch/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
