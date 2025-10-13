package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiWorkflowJobTemplateNodesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobTemplateNode;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobTemplateNodeDetail;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.WorkflowJobTemplateNodesApi")
public class WorkflowJobTemplateNodesApi extends BaseApi {

    public WorkflowJobTemplateNodesApi() {
        super(new ApiClient());
    }

    @Autowired
    public WorkflowJobTemplateNodesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return WorkflowJobTemplateNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNode apiWorkflowJobTemplateNodesAlwaysNodesCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplateNodesAlwaysNodesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;WorkflowJobTemplateNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNode> apiWorkflowJobTemplateNodesAlwaysNodesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesAlwaysNodesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesAlwaysNodesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNode> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNode>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/always_nodes/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobTemplateNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobTemplateNodesList200Response apiWorkflowJobTemplateNodesAlwaysNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplateNodesAlwaysNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobTemplateNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobTemplateNodesList200Response> apiWorkflowJobTemplateNodesAlwaysNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesAlwaysNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesAlwaysNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/always_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job template nodes.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return WorkflowJobTemplateNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNode apiWorkflowJobTemplateNodesCreate(String version, WorkflowJobTemplateNode data) throws RestClientException {
        return apiWorkflowJobTemplateNodesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job template nodes.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplateNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNode> apiWorkflowJobTemplateNodesCreateWithHttpInfo(String version, WorkflowJobTemplateNode data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplateNodesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNode> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNode>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiWorkflowJobTemplateNodesDelete(String version, String id) throws RestClientException {
        apiWorkflowJobTemplateNodesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiWorkflowJobTemplateNodesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);
        uriVariables.put("id", id);

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
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return WorkflowJobTemplateNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNode apiWorkflowJobTemplateNodesFailureNodesCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplateNodesFailureNodesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;WorkflowJobTemplateNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNode> apiWorkflowJobTemplateNodesFailureNodesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesFailureNodesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesFailureNodesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNode> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNode>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/failure_nodes/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobTemplateNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobTemplateNodesList200Response apiWorkflowJobTemplateNodesFailureNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplateNodesFailureNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobTemplateNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobTemplateNodesList200Response> apiWorkflowJobTemplateNodesFailureNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesFailureNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesFailureNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/failure_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job template nodes.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobTemplateNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobTemplateNodesList200Response apiWorkflowJobTemplateNodesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplateNodesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job template nodes.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobTemplateNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobTemplateNodesList200Response> apiWorkflowJobTemplateNodesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJobTemplateNodeDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNodeDetail apiWorkflowJobTemplateNodesPartialUpdate(String version, String id, WorkflowJobTemplateNodeDetail data) throws RestClientException {
        return apiWorkflowJobTemplateNodesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplateNodeDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNodeDetail> apiWorkflowJobTemplateNodesPartialUpdateWithHttpInfo(String version, String id, WorkflowJobTemplateNodeDetail data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplateNodesPartialUpdate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNodeDetail> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNodeDetail>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJobTemplateNodeDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNodeDetail apiWorkflowJobTemplateNodesRead(String version, String id) throws RestClientException {
        return apiWorkflowJobTemplateNodesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplateNodeDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNodeDetail> apiWorkflowJobTemplateNodesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesRead");
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

        ParameterizedTypeReference<WorkflowJobTemplateNodeDetail> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNodeDetail>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return WorkflowJobTemplateNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNode apiWorkflowJobTemplateNodesSuccessNodesCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplateNodesSuccessNodesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;WorkflowJobTemplateNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNode> apiWorkflowJobTemplateNodesSuccessNodesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesSuccessNodesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesSuccessNodesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNode> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNode>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/success_nodes/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobTemplateNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobTemplateNodesList200Response apiWorkflowJobTemplateNodesSuccessNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplateNodesSuccessNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job template nodes associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobTemplateNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobTemplateNodesList200Response> apiWorkflowJobTemplateNodesSuccessNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesSuccessNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesSuccessNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/success_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJobTemplateNodeDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNodeDetail apiWorkflowJobTemplateNodesUpdate(String version, String id, WorkflowJobTemplateNodeDetail data) throws RestClientException {
        return apiWorkflowJobTemplateNodesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)      # Update a Workflow Job Template Node:  Make a PUT or PATCH request to this resource to update this workflow job template node.  The following fields may be modified:          * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;workflow_job_template&#x60;:  (id, required) * &#x60;unified_job_template&#x60;:  (id, default&#x3D;&#x60;&#x60;)    * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string, default&#x3D;&#x60;\&quot;xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template Node:  Make a DELETE request to this resource to delete this workflow job template node.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplateNodeDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNodeDetail> apiWorkflowJobTemplateNodesUpdateWithHttpInfo(String version, String id, WorkflowJobTemplateNodeDetail data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplateNodesUpdate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNodeDetail> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNodeDetail>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Workflow nodes reference templates to execute and define the ordering
     * in which to execute them. After a job in this workflow finishes, the subsequent actions are to:   - run nodes contained in \&quot;failure_nodes\&quot; or \&quot;always_nodes\&quot; if job failed  - run nodes contained in \&quot;success_nodes\&quot; or \&quot;always_nodes\&quot; if job succeeded  The workflow job is marked as &#x60;successful&#x60; if all of the jobs running as a part of the workflow job have completed, and the workflow job has not been canceled. Even if a job within the workflow has failed, the workflow job will not be marked as failed.   # List Workflow Job Template Nodes for a Workflow Job Template:  Make a GET request to this resource to retrieve a list of workflow job template nodes associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return WorkflowJobTemplateNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNode apiWorkflowJobTemplatesWorkflowNodesCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplatesWorkflowNodesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Workflow nodes reference templates to execute and define the ordering
     * in which to execute them. After a job in this workflow finishes, the subsequent actions are to:   - run nodes contained in \&quot;failure_nodes\&quot; or \&quot;always_nodes\&quot; if job failed  - run nodes contained in \&quot;success_nodes\&quot; or \&quot;always_nodes\&quot; if job succeeded  The workflow job is marked as &#x60;successful&#x60; if all of the jobs running as a part of the workflow job have completed, and the workflow job has not been canceled. Even if a job within the workflow has failed, the workflow job will not be marked as failed.   # List Workflow Job Template Nodes for a Workflow Job Template:  Make a GET request to this resource to retrieve a list of workflow job template nodes associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;WorkflowJobTemplateNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNode> apiWorkflowJobTemplatesWorkflowNodesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesWorkflowNodesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesWorkflowNodesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNode> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNode>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/workflow_nodes/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Workflow nodes reference templates to execute and define the ordering
     * in which to execute them. After a job in this workflow finishes, the subsequent actions are to:   - run nodes contained in \&quot;failure_nodes\&quot; or \&quot;always_nodes\&quot; if job failed  - run nodes contained in \&quot;success_nodes\&quot; or \&quot;always_nodes\&quot; if job succeeded  The workflow job is marked as &#x60;successful&#x60; if all of the jobs running as a part of the workflow job have completed, and the workflow job has not been canceled. Even if a job within the workflow has failed, the workflow job will not be marked as failed.   # List Workflow Job Template Nodes for a Workflow Job Template:  Make a GET request to this resource to retrieve a list of workflow job template nodes associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiWorkflowJobTemplateNodesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiWorkflowJobTemplateNodesList200Response apiWorkflowJobTemplatesWorkflowNodesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesWorkflowNodesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Workflow nodes reference templates to execute and define the ordering
     * in which to execute them. After a job in this workflow finishes, the subsequent actions are to:   - run nodes contained in \&quot;failure_nodes\&quot; or \&quot;always_nodes\&quot; if job failed  - run nodes contained in \&quot;success_nodes\&quot; or \&quot;always_nodes\&quot; if job succeeded  The workflow job is marked as &#x60;successful&#x60; if all of the jobs running as a part of the workflow job have completed, and the workflow job has not been canceled. Even if a job within the workflow has failed, the workflow job will not be marked as failed.   # List Workflow Job Template Nodes for a Workflow Job Template:  Make a GET request to this resource to retrieve a list of workflow job template nodes associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job template nodes found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template node records.    ## Results  Each workflow job template node data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template node. (integer) * &#x60;type&#x60;: Data type for this workflow job template node. (choice) * &#x60;url&#x60;: URL for this workflow job template node. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template node was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template node was last modified. (datetime) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;workflow_job_template&#x60;:  (id) * &#x60;unified_job_template&#x60;:  (id) * &#x60;success_nodes&#x60;:  (field) * &#x60;failure_nodes&#x60;:  (field) * &#x60;always_nodes&#x60;:  (field) * &#x60;all_parents_must_converge&#x60;: If enabled then the node will only run if all of the parent nodes have met the criteria to reach this node (boolean) * &#x60;identifier&#x60;: An identifier for this node that is unique within its workflow. It is copied to workflow job nodes corresponding to this node. (string)    ## Sorting  To specify that workflow job template nodes are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiWorkflowJobTemplateNodesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiWorkflowJobTemplateNodesList200Response> apiWorkflowJobTemplatesWorkflowNodesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesWorkflowNodesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesWorkflowNodesList");
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

        ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response> localReturnType = new ParameterizedTypeReference<ApiWorkflowJobTemplateNodesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/workflow_nodes/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
