package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInstanceGroupsJobsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.UnifiedJobStdout;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.UnifiedJobsApi")
public class UnifiedJobsApi extends BaseApi {

    public UnifiedJobsApi() {
        super(new ApiClient());
    }

    @Autowired
    public UnifiedJobsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * ad hoc command.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return UnifiedJobStdout
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UnifiedJobStdout apiAdHocCommandsStdoutRead(String version, String id) throws RestClientException {
        return apiAdHocCommandsStdoutReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * ad hoc command.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;UnifiedJobStdout&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UnifiedJobStdout> apiAdHocCommandsStdoutReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsStdoutRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsStdoutRead");
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
            "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<UnifiedJobStdout> localReturnType = new ParameterizedTypeReference<UnifiedJobStdout>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/stdout/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * unified jobs associated with the selected instance group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsJobsList200Response apiInstanceGroupsJobsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstanceGroupsJobsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * unified jobs associated with the selected instance group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsJobsList200Response> apiInstanceGroupsJobsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsJobsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsJobsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * unified jobs associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsJobsList200Response apiInstancesJobsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesJobsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * unified jobs associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsJobsList200Response> apiInstancesJobsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesJobsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesJobsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * inventory update.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return UnifiedJobStdout
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UnifiedJobStdout apiInventoryUpdatesStdoutRead(String version, String id) throws RestClientException {
        return apiInventoryUpdatesStdoutReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * inventory update.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;UnifiedJobStdout&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UnifiedJobStdout> apiInventoryUpdatesStdoutReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesStdoutRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesStdoutRead");
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
            "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<UnifiedJobStdout> localReturnType = new ParameterizedTypeReference<UnifiedJobStdout>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/stdout/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * job.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return UnifiedJobStdout
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UnifiedJobStdout apiJobsStdoutRead(String version, String id) throws RestClientException {
        return apiJobsStdoutReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * job.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;UnifiedJobStdout&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UnifiedJobStdout> apiJobsStdoutReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsStdoutRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsStdoutRead");
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
            "text/plain", "application/json", "text/html; charset=utf-8"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<UnifiedJobStdout> localReturnType = new ParameterizedTypeReference<UnifiedJobStdout>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/stdout/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * project update.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return UnifiedJobStdout
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UnifiedJobStdout apiProjectUpdatesStdoutRead(String version, String id) throws RestClientException {
        return apiProjectUpdatesStdoutReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve the stdout from running this
     * project update.  ## Format  Use the &#x60;format&#x60; query string parameter to specify the output format.  * Browsable API: &#x60;?format&#x3D;api&#x60; * HTML: &#x60;?format&#x3D;html&#x60; * Plain Text: &#x60;?format&#x3D;txt&#x60; * Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi&#x60; * JSON structure: &#x60;?format&#x3D;json&#x60; * Downloaded Plain Text: &#x60;?format&#x3D;txt_download&#x60; * Downloaded Plain Text with ANSI color codes: &#x60;?format&#x3D;ansi_download&#x60;  (_New in Ansible Tower 2.0.0_) When using the Browsable API, HTML and JSON formats, the &#x60;start_line&#x60; and &#x60;end_line&#x60; query string parameters can be used to specify a range of line numbers to retrieve.  Use &#x60;dark&#x3D;1&#x60; or &#x60;dark&#x3D;0&#x60; as a query string parameter to force or disable a dark background.  Files over 1.0 MB (configurable) will not display in the browser. Use the &#x60;txt_download&#x60; or &#x60;ansi_download&#x60; formats to download the file directly to view it.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;UnifiedJobStdout&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UnifiedJobStdout> apiProjectUpdatesStdoutReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesStdoutRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesStdoutRead");
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
            "text/plain", "application/json", "text/html; charset=utf-8"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<UnifiedJobStdout> localReturnType = new ParameterizedTypeReference<UnifiedJobStdout>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/stdout/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * unified jobs associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsJobsList200Response apiSchedulesJobsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSchedulesJobsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * unified jobs associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsJobsList200Response> apiSchedulesJobsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesJobsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesJobsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * unified jobs.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsJobsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsJobsList200Response apiUnifiedJobsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUnifiedJobsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * unified jobs.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of unified jobs found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more unified job records.    ## Results  Each unified job data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this unified job. (integer) * &#x60;type&#x60;: Data type for this unified job. (choice) * &#x60;url&#x60;: URL for this unified job. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this unified job was created. (datetime) * &#x60;modified&#x60;: Timestamp when this unified job was last modified. (datetime) * &#x60;name&#x60;: Name of this unified job. (string) * &#x60;description&#x60;: Optional description of this unified job. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string)    ## Sorting  To specify that unified jobs are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsJobsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsJobsList200Response> apiUnifiedJobsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUnifiedJobsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsJobsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/unified_jobs/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
