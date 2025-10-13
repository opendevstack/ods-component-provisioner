package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiWorkflowJobNodesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobNodeDetail;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.WorkflowJobNodesApi")
public class WorkflowJobNodesApi extends BaseApi {

    public WorkflowJobNodesApi() {
        super(new ApiClient());
    }

    @Autowired
    public WorkflowJobNodesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobNodesList200Response apiWorkflowJobNodesAlwaysNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobNodesAlwaysNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobNodesList200Response> apiWorkflowJobNodesAlwaysNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesAlwaysNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesAlwaysNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/always_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobNodesList200Response apiWorkflowJobNodesFailureNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobNodesFailureNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobNodesList200Response> apiWorkflowJobNodesFailureNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesFailureNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesFailureNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/failure_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job nodes.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobNodesList200Response apiWorkflowJobNodesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobNodesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job nodes.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobNodesList200Response> apiWorkflowJobNodesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJobNodeDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobNodeDetail apiWorkflowJobNodesRead(String version, String id) throws RestClientException {
        return apiWorkflowJobNodesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJobNodeDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobNodeDetail> apiWorkflowJobNodesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesRead");
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

        ParameterizedTypeReference<WorkflowJobNodeDetail> localReturnType = new ParameterizedTypeReference<WorkflowJobNodeDetail>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobNodesList200Response apiWorkflowJobNodesSuccessNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobNodesSuccessNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobNodesList200Response> apiWorkflowJobNodesSuccessNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesSuccessNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesSuccessNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/success_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobNodesList200Response apiWorkflowJobsWorkflowNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobsWorkflowNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job nodes associated with the selected workflow job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job node records.    ## Results  Each workflow job node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job node. (integer) * &#x60;type&#x60;: Data type for this workflow job node. (choice) * &#x60;url&#x60;: URL for this workflow job node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;job&#x60;:  (id) * &#x60;workflow_job&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;do_not_run&#x60;: Indicates that a job will not be created when True. Workflow runtime semantics will mark this True if the node is in a path that will decidedly not be ran. A value of False means the node may not run. (boolean) * &#x60;identifier&#x60;: An identifier coresponding to the workflow job template node that this node was created from. (string)    ## Sorting  To specify that workflow job nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobNodesList200Response> apiWorkflowJobsWorkflowNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsWorkflowNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsWorkflowNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/workflow_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
