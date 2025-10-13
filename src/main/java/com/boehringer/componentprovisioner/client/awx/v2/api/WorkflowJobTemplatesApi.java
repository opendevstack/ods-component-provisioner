package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsCopyList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiJobTemplatesCallbackList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiOrganizationsWorkflowJobTemplatesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Copy;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJob;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobLaunch;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobTemplate;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.WorkflowJobTemplatesApi")
public class WorkflowJobTemplatesApi extends BaseApi {

    public WorkflowJobTemplatesApi() {
        super(new ApiClient());
    }

    @Autowired
    public WorkflowJobTemplatesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJobTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplate apiOrganizationsWorkflowJobTemplatesCreate(String version, String id, WorkflowJobTemplate data) throws RestClientException {
        return apiOrganizationsWorkflowJobTemplatesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplate> apiOrganizationsWorkflowJobTemplatesCreateWithHttpInfo(String version, String id, WorkflowJobTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsWorkflowJobTemplatesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsWorkflowJobTemplatesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsWorkflowJobTemplatesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplate> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/workflow_job_templates/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsWorkflowJobTemplatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsWorkflowJobTemplatesList200Response apiOrganizationsWorkflowJobTemplatesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsWorkflowJobTemplatesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * workflow job templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsWorkflowJobTemplatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsWorkflowJobTemplatesList200Response> apiOrganizationsWorkflowJobTemplatesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsWorkflowJobTemplatesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsWorkflowJobTemplatesList");
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

        ParameterizedTypeReference<ApiOrganizationsWorkflowJobTemplatesList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsWorkflowJobTemplatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/workflow_job_templates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the current user has
     * permission to copy the workflow job template and whether any linked templates or prompted fields will be ignored due to permissions problems. The response will include the following fields:  * &#x60;can_copy&#x60;: Flag indicating whether the active user has permission to make   a copy of this workflow job template, provides same content as the   workflow job template detail view summary_fields.user_capabilities.copy   (boolean, read-only) * &#x60;can_copy_without_user_input&#x60;: Flag indicating if the user should be   prompted for confirmation before the copy is executed (boolean, read-only) * &#x60;templates_unable_to_copy&#x60;: List of node ids of nodes that have a related   job template, project, or inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;inventories_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;credentials_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted credential that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only)  Make a POST request to this endpoint to save a copy of this workflow job template. No POST data is accepted for this action.  If successful, the response status code will be 201. The response body will contain serialized data about the new workflow job template, which will be similar to the original workflow job template, but with an additional &#x60;@&#x60; and a timestamp in the name.  All workflow nodes and connections in the original will also exist in the copy. The nodes will be missing related resources if the user did not have access to use them.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Copy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Copy apiWorkflowJobTemplatesCopyCreate(String version, String id, Copy data) throws RestClientException {
        return apiWorkflowJobTemplatesCopyCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the current user has
     * permission to copy the workflow job template and whether any linked templates or prompted fields will be ignored due to permissions problems. The response will include the following fields:  * &#x60;can_copy&#x60;: Flag indicating whether the active user has permission to make   a copy of this workflow job template, provides same content as the   workflow job template detail view summary_fields.user_capabilities.copy   (boolean, read-only) * &#x60;can_copy_without_user_input&#x60;: Flag indicating if the user should be   prompted for confirmation before the copy is executed (boolean, read-only) * &#x60;templates_unable_to_copy&#x60;: List of node ids of nodes that have a related   job template, project, or inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;inventories_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;credentials_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted credential that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only)  Make a POST request to this endpoint to save a copy of this workflow job template. No POST data is accepted for this action.  If successful, the response status code will be 201. The response body will contain serialized data about the new workflow job template, which will be similar to the original workflow job template, but with an additional &#x60;@&#x60; and a timestamp in the name.  All workflow nodes and connections in the original will also exist in the copy. The nodes will be missing related resources if the user did not have access to use them.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Copy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Copy> apiWorkflowJobTemplatesCopyCreateWithHttpInfo(String version, String id, Copy data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesCopyCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesCopyCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplatesCopyCreate");
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

        ParameterizedTypeReference<Copy> localReturnType = new ParameterizedTypeReference<Copy>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/copy/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the current user has
     * permission to copy the workflow job template and whether any linked templates or prompted fields will be ignored due to permissions problems. The response will include the following fields:  * &#x60;can_copy&#x60;: Flag indicating whether the active user has permission to make   a copy of this workflow job template, provides same content as the   workflow job template detail view summary_fields.user_capabilities.copy   (boolean, read-only) * &#x60;can_copy_without_user_input&#x60;: Flag indicating if the user should be   prompted for confirmation before the copy is executed (boolean, read-only) * &#x60;templates_unable_to_copy&#x60;: List of node ids of nodes that have a related   job template, project, or inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;inventories_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;credentials_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted credential that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only)  Make a POST request to this endpoint to save a copy of this workflow job template. No POST data is accepted for this action.  If successful, the response status code will be 201. The response body will contain serialized data about the new workflow job template, which will be similar to the original workflow job template, but with an additional &#x60;@&#x60; and a timestamp in the name.  All workflow nodes and connections in the original will also exist in the copy. The nodes will be missing related resources if the user did not have access to use them.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsCopyList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsCopyList200Response apiWorkflowJobTemplatesCopyList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesCopyListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the current user has
     * permission to copy the workflow job template and whether any linked templates or prompted fields will be ignored due to permissions problems. The response will include the following fields:  * &#x60;can_copy&#x60;: Flag indicating whether the active user has permission to make   a copy of this workflow job template, provides same content as the   workflow job template detail view summary_fields.user_capabilities.copy   (boolean, read-only) * &#x60;can_copy_without_user_input&#x60;: Flag indicating if the user should be   prompted for confirmation before the copy is executed (boolean, read-only) * &#x60;templates_unable_to_copy&#x60;: List of node ids of nodes that have a related   job template, project, or inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;inventories_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted inventory that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only) * &#x60;credentials_unable_to_copy&#x60;: List of node ids of nodes that have a related   prompted credential that the current user lacks permission   to use and will be missing in workflow nodes of the copy (array, read-only)  Make a POST request to this endpoint to save a copy of this workflow job template. No POST data is accepted for this action.  If successful, the response status code will be 201. The response body will contain serialized data about the new workflow job template, which will be similar to the original workflow job template, but with an additional &#x60;@&#x60; and a timestamp in the name.  All workflow nodes and connections in the original will also exist in the copy. The nodes will be missing related resources if the user did not have access to use them.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsCopyList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsCopyList200Response> apiWorkflowJobTemplatesCopyListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesCopyList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesCopyList");
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

        ParameterizedTypeReference<ApiCredentialsCopyList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsCopyList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/copy/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return WorkflowJobTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplate apiWorkflowJobTemplatesCreate(String version, WorkflowJobTemplate data) throws RestClientException {
        return apiWorkflowJobTemplatesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplate> apiWorkflowJobTemplatesCreateWithHttpInfo(String version, WorkflowJobTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplatesCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplate> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiWorkflowJobTemplatesDelete(String version, String id) throws RestClientException {
        apiWorkflowJobTemplatesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiWorkflowJobTemplatesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesDelete");
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
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the workflow_job_template
     * can be launched and whether any passwords are required to launch the workflow_job_template. The response will include the following fields:  * &#x60;can_start_without_user_input&#x60;: Flag indicating if the workflow_job_template   can be launched without user-input (boolean, read-only) * &#x60;variables_needed_to_start&#x60;: Required variable names required to launch the   workflow_job_template (array, read-only) * &#x60;survey_enabled&#x60;: Flag indicating whether the workflow_job_template has an   enabled survey (boolean, read-only) * &#x60;extra_vars&#x60;: Text which is the &#x60;extra_vars&#x60; field of this workflow_job_template   (text, read-only) * &#x60;node_templates_missing&#x60;: List of node ids of all nodes that have a   null &#x60;unified_job_template&#x60;, which will cause their branches to stop   execution (list, read-only) * &#x60;node_prompts_rejected&#x60;: List of node ids of all nodes that have   specified a field that will be rejected because its  &#x60;unified_job_template&#x60;   does not allow prompting for this field, this will not halt execution of   the branch but the field will be ignored (list, read-only) * &#x60;workflow_job_template_data&#x60;: JSON object listing general information of   this workflow_job_template (JSON object, read-only)  Make a POST request to this resource to launch the workflow_job_template. If any credential, inventory, project or extra variables (extra_vars) are required, they must be passed via POST data, with extra_vars given as a YAML or JSON string and escaped parentheses.  If successful, the response status code will be 201.  If any required passwords are not provided, a 400 status code will be returned.  If the workflow job cannot be launched, a 405 status code will be returned. If the provided credential or inventory are not allowed to be used by the user, then a 403 status code will be returned.
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJob apiWorkflowJobTemplatesLaunchCreate(String version, String id, WorkflowJobLaunch data) throws RestClientException {
        return apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the workflow_job_template
     * can be launched and whether any passwords are required to launch the workflow_job_template. The response will include the following fields:  * &#x60;can_start_without_user_input&#x60;: Flag indicating if the workflow_job_template   can be launched without user-input (boolean, read-only) * &#x60;variables_needed_to_start&#x60;: Required variable names required to launch the   workflow_job_template (array, read-only) * &#x60;survey_enabled&#x60;: Flag indicating whether the workflow_job_template has an   enabled survey (boolean, read-only) * &#x60;extra_vars&#x60;: Text which is the &#x60;extra_vars&#x60; field of this workflow_job_template   (text, read-only) * &#x60;node_templates_missing&#x60;: List of node ids of all nodes that have a   null &#x60;unified_job_template&#x60;, which will cause their branches to stop   execution (list, read-only) * &#x60;node_prompts_rejected&#x60;: List of node ids of all nodes that have   specified a field that will be rejected because its  &#x60;unified_job_template&#x60;   does not allow prompting for this field, this will not halt execution of   the branch but the field will be ignored (list, read-only) * &#x60;workflow_job_template_data&#x60;: JSON object listing general information of   this workflow_job_template (JSON object, read-only)  Make a POST request to this resource to launch the workflow_job_template. If any credential, inventory, project or extra variables (extra_vars) are required, they must be passed via POST data, with extra_vars given as a YAML or JSON string and escaped parentheses.  If successful, the response status code will be 201.  If any required passwords are not provided, a 400 status code will be returned.  If the workflow job cannot be launched, a 405 status code will be returned. If the provided credential or inventory are not allowed to be used by the user, then a 403 status code will be returned.
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJob> apiWorkflowJobTemplatesLaunchCreateWithHttpInfo(String version, String id, WorkflowJobLaunch data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesLaunchCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesLaunchCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplatesLaunchCreate");
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

        ParameterizedTypeReference<WorkflowJob> localReturnType = new ParameterizedTypeReference<WorkflowJob>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/launch/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the workflow_job_template
     * can be launched and whether any passwords are required to launch the workflow_job_template. The response will include the following fields:  * &#x60;can_start_without_user_input&#x60;: Flag indicating if the workflow_job_template   can be launched without user-input (boolean, read-only) * &#x60;variables_needed_to_start&#x60;: Required variable names required to launch the   workflow_job_template (array, read-only) * &#x60;survey_enabled&#x60;: Flag indicating whether the workflow_job_template has an   enabled survey (boolean, read-only) * &#x60;extra_vars&#x60;: Text which is the &#x60;extra_vars&#x60; field of this workflow_job_template   (text, read-only) * &#x60;node_templates_missing&#x60;: List of node ids of all nodes that have a   null &#x60;unified_job_template&#x60;, which will cause their branches to stop   execution (list, read-only) * &#x60;node_prompts_rejected&#x60;: List of node ids of all nodes that have   specified a field that will be rejected because its  &#x60;unified_job_template&#x60;   does not allow prompting for this field, this will not halt execution of   the branch but the field will be ignored (list, read-only) * &#x60;workflow_job_template_data&#x60;: JSON object listing general information of   this workflow_job_template (JSON object, read-only)  Make a POST request to this resource to launch the workflow_job_template. If any credential, inventory, project or extra variables (extra_vars) are required, they must be passed via POST data, with extra_vars given as a YAML or JSON string and escaped parentheses.  If successful, the response status code will be 201.  If any required passwords are not provided, a 400 status code will be returned.  If the workflow job cannot be launched, a 405 status code will be returned. If the provided credential or inventory are not allowed to be used by the user, then a 403 status code will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJobLaunch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobLaunch apiWorkflowJobTemplatesLaunchRead(String version, String id) throws RestClientException {
        return apiWorkflowJobTemplatesLaunchReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the workflow_job_template
     * can be launched and whether any passwords are required to launch the workflow_job_template. The response will include the following fields:  * &#x60;can_start_without_user_input&#x60;: Flag indicating if the workflow_job_template   can be launched without user-input (boolean, read-only) * &#x60;variables_needed_to_start&#x60;: Required variable names required to launch the   workflow_job_template (array, read-only) * &#x60;survey_enabled&#x60;: Flag indicating whether the workflow_job_template has an   enabled survey (boolean, read-only) * &#x60;extra_vars&#x60;: Text which is the &#x60;extra_vars&#x60; field of this workflow_job_template   (text, read-only) * &#x60;node_templates_missing&#x60;: List of node ids of all nodes that have a   null &#x60;unified_job_template&#x60;, which will cause their branches to stop   execution (list, read-only) * &#x60;node_prompts_rejected&#x60;: List of node ids of all nodes that have   specified a field that will be rejected because its  &#x60;unified_job_template&#x60;   does not allow prompting for this field, this will not halt execution of   the branch but the field will be ignored (list, read-only) * &#x60;workflow_job_template_data&#x60;: JSON object listing general information of   this workflow_job_template (JSON object, read-only)  Make a POST request to this resource to launch the workflow_job_template. If any credential, inventory, project or extra variables (extra_vars) are required, they must be passed via POST data, with extra_vars given as a YAML or JSON string and escaped parentheses.  If successful, the response status code will be 201.  If any required passwords are not provided, a 400 status code will be returned.  If the workflow job cannot be launched, a 405 status code will be returned. If the provided credential or inventory are not allowed to be used by the user, then a 403 status code will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJobLaunch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobLaunch> apiWorkflowJobTemplatesLaunchReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesLaunchRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesLaunchRead");
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

        ParameterizedTypeReference<WorkflowJobLaunch> localReturnType = new ParameterizedTypeReference<WorkflowJobLaunch>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/launch/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsWorkflowJobTemplatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsWorkflowJobTemplatesList200Response apiWorkflowJobTemplatesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * workflow job templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of workflow job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more workflow job template records.    ## Results  Each workflow job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)    ## Sorting  To specify that workflow job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsWorkflowJobTemplatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsWorkflowJobTemplatesList200Response> apiWorkflowJobTemplatesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesList");
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

        ParameterizedTypeReference<ApiOrganizationsWorkflowJobTemplatesList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsWorkflowJobTemplatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return WorkflowJobTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplate apiWorkflowJobTemplatesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplatesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;WorkflowJobTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplate> apiWorkflowJobTemplatesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesPartialUpdate");
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

        ParameterizedTypeReference<WorkflowJobTemplate> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJobTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplate apiWorkflowJobTemplatesRead(String version, String id) throws RestClientException {
        return apiWorkflowJobTemplatesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplate> apiWorkflowJobTemplatesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesRead");
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

        ParameterizedTypeReference<WorkflowJobTemplate> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Here is an example survey specification
     *     {         \&quot;name\&quot;: \&quot;Simple Survey\&quot;,         \&quot;description\&quot;: \&quot;Description of the simple survey\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;example question\&quot;,          \&quot;question_description\&quot;: \&quot;What is your favorite color?\&quot;,          \&quot;variable\&quot;: \&quot;favorite_color\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;blue\&quot;             }         ]     }  &#x60;name&#x60; and &#x60;description&#x60; are required elements at the beginning of the survey specification. &#x60;spec&#x60; must be a list of survey items.  Within each survey item &#x60;type&#x60; must be one of:  * text: For survey questions expecting a textual answer * password: For survey questions expecting a password or other sensitive information * integer: For survey questions expecting a whole number answer * float: For survey questions expecting a decimal number * multiplechoice: For survey questions where one option from a list is required * multiselect: For survey questions where multiple items from a presented list can be selected  Each item must contain a &#x60;question_name&#x60; and &#x60;question_description&#x60; field that describes the survey question itself. The &#x60;variable&#x60; elements of each survey items represents the key that will be given to the playbook when the workflow job template is launched.  It will contain the value as a result of the survey.  Here is a more comprehensive example showing the various question types and their acceptable parameters:      {         \&quot;name\&quot;: \&quot;Simple\&quot;,         \&quot;description\&quot;: \&quot;Description\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbeshort\&quot;,          \&quot;question_description\&quot;: \&quot;What is a long answer\&quot;,          \&quot;variable\&quot;: \&quot;long_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: 5,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;Leeloo Minai Lekarariba-Laminai-Tchai Ekbat De Sebat\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbelong\&quot;,          \&quot;question_description\&quot;: \&quot;What is a short answer\&quot;,          \&quot;variable\&quot;: \&quot;short_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: 7,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;leeloo\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;reqd\&quot;,          \&quot;question_description\&quot;: \&quot;I should be required\&quot;,          \&quot;variable\&quot;: \&quot;reqd_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: true,          \&quot;default\&quot;: \&quot;NOT OPTIONAL\&quot;             },             {          \&quot;type\&quot;: \&quot;multiplechoice\&quot;,          \&quot;question_name\&quot;: \&quot;achoice\&quot;,          \&quot;question_description\&quot;: \&quot;Need one of these\&quot;,          \&quot;variable\&quot;: \&quot;single_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\&quot;             },             {          \&quot;type\&quot;: \&quot;multiselect\&quot;,          \&quot;question_name\&quot;: \&quot;mchoice\&quot;,          \&quot;question_description\&quot;: \&quot;Can have multiples of these\&quot;,          \&quot;variable\&quot;: \&quot;multi_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;, \&quot;three\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\\nthree\&quot;             },             {                 \&quot;type\&quot;: \&quot;integer\&quot;,                 \&quot;question_name\&quot;: \&quot;integerchoice\&quot;,                 \&quot;question_description\&quot;: \&quot;I need an int here\&quot;,                 \&quot;variable\&quot;: \&quot;int_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 1,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             },             {                 \&quot;type\&quot;: \&quot;float\&quot;,                 \&quot;question_name\&quot;: \&quot;float\&quot;,                 \&quot;question_description\&quot;: \&quot;I need a float here\&quot;,                 \&quot;variable\&quot;: \&quot;float_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 2,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             }         ]     }
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiWorkflowJobTemplatesSurveySpecCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplatesSurveySpecCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Here is an example survey specification
     *     {         \&quot;name\&quot;: \&quot;Simple Survey\&quot;,         \&quot;description\&quot;: \&quot;Description of the simple survey\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;example question\&quot;,          \&quot;question_description\&quot;: \&quot;What is your favorite color?\&quot;,          \&quot;variable\&quot;: \&quot;favorite_color\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;blue\&quot;             }         ]     }  &#x60;name&#x60; and &#x60;description&#x60; are required elements at the beginning of the survey specification. &#x60;spec&#x60; must be a list of survey items.  Within each survey item &#x60;type&#x60; must be one of:  * text: For survey questions expecting a textual answer * password: For survey questions expecting a password or other sensitive information * integer: For survey questions expecting a whole number answer * float: For survey questions expecting a decimal number * multiplechoice: For survey questions where one option from a list is required * multiselect: For survey questions where multiple items from a presented list can be selected  Each item must contain a &#x60;question_name&#x60; and &#x60;question_description&#x60; field that describes the survey question itself. The &#x60;variable&#x60; elements of each survey items represents the key that will be given to the playbook when the workflow job template is launched.  It will contain the value as a result of the survey.  Here is a more comprehensive example showing the various question types and their acceptable parameters:      {         \&quot;name\&quot;: \&quot;Simple\&quot;,         \&quot;description\&quot;: \&quot;Description\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbeshort\&quot;,          \&quot;question_description\&quot;: \&quot;What is a long answer\&quot;,          \&quot;variable\&quot;: \&quot;long_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: 5,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;Leeloo Minai Lekarariba-Laminai-Tchai Ekbat De Sebat\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbelong\&quot;,          \&quot;question_description\&quot;: \&quot;What is a short answer\&quot;,          \&quot;variable\&quot;: \&quot;short_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: 7,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;leeloo\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;reqd\&quot;,          \&quot;question_description\&quot;: \&quot;I should be required\&quot;,          \&quot;variable\&quot;: \&quot;reqd_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: true,          \&quot;default\&quot;: \&quot;NOT OPTIONAL\&quot;             },             {          \&quot;type\&quot;: \&quot;multiplechoice\&quot;,          \&quot;question_name\&quot;: \&quot;achoice\&quot;,          \&quot;question_description\&quot;: \&quot;Need one of these\&quot;,          \&quot;variable\&quot;: \&quot;single_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\&quot;             },             {          \&quot;type\&quot;: \&quot;multiselect\&quot;,          \&quot;question_name\&quot;: \&quot;mchoice\&quot;,          \&quot;question_description\&quot;: \&quot;Can have multiples of these\&quot;,          \&quot;variable\&quot;: \&quot;multi_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;, \&quot;three\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\\nthree\&quot;             },             {                 \&quot;type\&quot;: \&quot;integer\&quot;,                 \&quot;question_name\&quot;: \&quot;integerchoice\&quot;,                 \&quot;question_description\&quot;: \&quot;I need an int here\&quot;,                 \&quot;variable\&quot;: \&quot;int_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 1,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             },             {                 \&quot;type\&quot;: \&quot;float\&quot;,                 \&quot;question_name\&quot;: \&quot;float\&quot;,                 \&quot;question_description\&quot;: \&quot;I need a float here\&quot;,                 \&quot;variable\&quot;: \&quot;float_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 2,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             }         ]     }
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiWorkflowJobTemplatesSurveySpecCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesSurveySpecCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesSurveySpecCreate");
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
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/survey_spec/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Here is an example survey specification
     *     {         \&quot;name\&quot;: \&quot;Simple Survey\&quot;,         \&quot;description\&quot;: \&quot;Description of the simple survey\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;example question\&quot;,          \&quot;question_description\&quot;: \&quot;What is your favorite color?\&quot;,          \&quot;variable\&quot;: \&quot;favorite_color\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;blue\&quot;             }         ]     }  &#x60;name&#x60; and &#x60;description&#x60; are required elements at the beginning of the survey specification. &#x60;spec&#x60; must be a list of survey items.  Within each survey item &#x60;type&#x60; must be one of:  * text: For survey questions expecting a textual answer * password: For survey questions expecting a password or other sensitive information * integer: For survey questions expecting a whole number answer * float: For survey questions expecting a decimal number * multiplechoice: For survey questions where one option from a list is required * multiselect: For survey questions where multiple items from a presented list can be selected  Each item must contain a &#x60;question_name&#x60; and &#x60;question_description&#x60; field that describes the survey question itself. The &#x60;variable&#x60; elements of each survey items represents the key that will be given to the playbook when the workflow job template is launched.  It will contain the value as a result of the survey.  Here is a more comprehensive example showing the various question types and their acceptable parameters:      {         \&quot;name\&quot;: \&quot;Simple\&quot;,         \&quot;description\&quot;: \&quot;Description\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbeshort\&quot;,          \&quot;question_description\&quot;: \&quot;What is a long answer\&quot;,          \&quot;variable\&quot;: \&quot;long_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: 5,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;Leeloo Minai Lekarariba-Laminai-Tchai Ekbat De Sebat\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbelong\&quot;,          \&quot;question_description\&quot;: \&quot;What is a short answer\&quot;,          \&quot;variable\&quot;: \&quot;short_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: 7,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;leeloo\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;reqd\&quot;,          \&quot;question_description\&quot;: \&quot;I should be required\&quot;,          \&quot;variable\&quot;: \&quot;reqd_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: true,          \&quot;default\&quot;: \&quot;NOT OPTIONAL\&quot;             },             {          \&quot;type\&quot;: \&quot;multiplechoice\&quot;,          \&quot;question_name\&quot;: \&quot;achoice\&quot;,          \&quot;question_description\&quot;: \&quot;Need one of these\&quot;,          \&quot;variable\&quot;: \&quot;single_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\&quot;             },             {          \&quot;type\&quot;: \&quot;multiselect\&quot;,          \&quot;question_name\&quot;: \&quot;mchoice\&quot;,          \&quot;question_description\&quot;: \&quot;Can have multiples of these\&quot;,          \&quot;variable\&quot;: \&quot;multi_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;, \&quot;three\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\\nthree\&quot;             },             {                 \&quot;type\&quot;: \&quot;integer\&quot;,                 \&quot;question_name\&quot;: \&quot;integerchoice\&quot;,                 \&quot;question_description\&quot;: \&quot;I need an int here\&quot;,                 \&quot;variable\&quot;: \&quot;int_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 1,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             },             {                 \&quot;type\&quot;: \&quot;float\&quot;,                 \&quot;question_name\&quot;: \&quot;float\&quot;,                 \&quot;question_description\&quot;: \&quot;I need a float here\&quot;,                 \&quot;variable\&quot;: \&quot;float_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 2,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             }         ]     }
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiWorkflowJobTemplatesSurveySpecDelete(String version, String id) throws RestClientException {
        apiWorkflowJobTemplatesSurveySpecDeleteWithHttpInfo(version, id);
    }

    /**
     * Here is an example survey specification
     *     {         \&quot;name\&quot;: \&quot;Simple Survey\&quot;,         \&quot;description\&quot;: \&quot;Description of the simple survey\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;example question\&quot;,          \&quot;question_description\&quot;: \&quot;What is your favorite color?\&quot;,          \&quot;variable\&quot;: \&quot;favorite_color\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;blue\&quot;             }         ]     }  &#x60;name&#x60; and &#x60;description&#x60; are required elements at the beginning of the survey specification. &#x60;spec&#x60; must be a list of survey items.  Within each survey item &#x60;type&#x60; must be one of:  * text: For survey questions expecting a textual answer * password: For survey questions expecting a password or other sensitive information * integer: For survey questions expecting a whole number answer * float: For survey questions expecting a decimal number * multiplechoice: For survey questions where one option from a list is required * multiselect: For survey questions where multiple items from a presented list can be selected  Each item must contain a &#x60;question_name&#x60; and &#x60;question_description&#x60; field that describes the survey question itself. The &#x60;variable&#x60; elements of each survey items represents the key that will be given to the playbook when the workflow job template is launched.  It will contain the value as a result of the survey.  Here is a more comprehensive example showing the various question types and their acceptable parameters:      {         \&quot;name\&quot;: \&quot;Simple\&quot;,         \&quot;description\&quot;: \&quot;Description\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbeshort\&quot;,          \&quot;question_description\&quot;: \&quot;What is a long answer\&quot;,          \&quot;variable\&quot;: \&quot;long_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: 5,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;Leeloo Minai Lekarariba-Laminai-Tchai Ekbat De Sebat\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbelong\&quot;,          \&quot;question_description\&quot;: \&quot;What is a short answer\&quot;,          \&quot;variable\&quot;: \&quot;short_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: 7,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;leeloo\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;reqd\&quot;,          \&quot;question_description\&quot;: \&quot;I should be required\&quot;,          \&quot;variable\&quot;: \&quot;reqd_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: true,          \&quot;default\&quot;: \&quot;NOT OPTIONAL\&quot;             },             {          \&quot;type\&quot;: \&quot;multiplechoice\&quot;,          \&quot;question_name\&quot;: \&quot;achoice\&quot;,          \&quot;question_description\&quot;: \&quot;Need one of these\&quot;,          \&quot;variable\&quot;: \&quot;single_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\&quot;             },             {          \&quot;type\&quot;: \&quot;multiselect\&quot;,          \&quot;question_name\&quot;: \&quot;mchoice\&quot;,          \&quot;question_description\&quot;: \&quot;Can have multiples of these\&quot;,          \&quot;variable\&quot;: \&quot;multi_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;, \&quot;three\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\\nthree\&quot;             },             {                 \&quot;type\&quot;: \&quot;integer\&quot;,                 \&quot;question_name\&quot;: \&quot;integerchoice\&quot;,                 \&quot;question_description\&quot;: \&quot;I need an int here\&quot;,                 \&quot;variable\&quot;: \&quot;int_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 1,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             },             {                 \&quot;type\&quot;: \&quot;float\&quot;,                 \&quot;question_name\&quot;: \&quot;float\&quot;,                 \&quot;question_description\&quot;: \&quot;I need a float here\&quot;,                 \&quot;variable\&quot;: \&quot;float_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 2,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             }         ]     }
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiWorkflowJobTemplatesSurveySpecDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesSurveySpecDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesSurveySpecDelete");
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
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/survey_spec/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Here is an example survey specification
     *     {         \&quot;name\&quot;: \&quot;Simple Survey\&quot;,         \&quot;description\&quot;: \&quot;Description of the simple survey\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;example question\&quot;,          \&quot;question_description\&quot;: \&quot;What is your favorite color?\&quot;,          \&quot;variable\&quot;: \&quot;favorite_color\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;blue\&quot;             }         ]     }  &#x60;name&#x60; and &#x60;description&#x60; are required elements at the beginning of the survey specification. &#x60;spec&#x60; must be a list of survey items.  Within each survey item &#x60;type&#x60; must be one of:  * text: For survey questions expecting a textual answer * password: For survey questions expecting a password or other sensitive information * integer: For survey questions expecting a whole number answer * float: For survey questions expecting a decimal number * multiplechoice: For survey questions where one option from a list is required * multiselect: For survey questions where multiple items from a presented list can be selected  Each item must contain a &#x60;question_name&#x60; and &#x60;question_description&#x60; field that describes the survey question itself. The &#x60;variable&#x60; elements of each survey items represents the key that will be given to the playbook when the workflow job template is launched.  It will contain the value as a result of the survey.  Here is a more comprehensive example showing the various question types and their acceptable parameters:      {         \&quot;name\&quot;: \&quot;Simple\&quot;,         \&quot;description\&quot;: \&quot;Description\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbeshort\&quot;,          \&quot;question_description\&quot;: \&quot;What is a long answer\&quot;,          \&quot;variable\&quot;: \&quot;long_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: 5,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;Leeloo Minai Lekarariba-Laminai-Tchai Ekbat De Sebat\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbelong\&quot;,          \&quot;question_description\&quot;: \&quot;What is a short answer\&quot;,          \&quot;variable\&quot;: \&quot;short_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: 7,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;leeloo\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;reqd\&quot;,          \&quot;question_description\&quot;: \&quot;I should be required\&quot;,          \&quot;variable\&quot;: \&quot;reqd_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: true,          \&quot;default\&quot;: \&quot;NOT OPTIONAL\&quot;             },             {          \&quot;type\&quot;: \&quot;multiplechoice\&quot;,          \&quot;question_name\&quot;: \&quot;achoice\&quot;,          \&quot;question_description\&quot;: \&quot;Need one of these\&quot;,          \&quot;variable\&quot;: \&quot;single_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\&quot;             },             {          \&quot;type\&quot;: \&quot;multiselect\&quot;,          \&quot;question_name\&quot;: \&quot;mchoice\&quot;,          \&quot;question_description\&quot;: \&quot;Can have multiples of these\&quot;,          \&quot;variable\&quot;: \&quot;multi_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;, \&quot;three\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\\nthree\&quot;             },             {                 \&quot;type\&quot;: \&quot;integer\&quot;,                 \&quot;question_name\&quot;: \&quot;integerchoice\&quot;,                 \&quot;question_description\&quot;: \&quot;I need an int here\&quot;,                 \&quot;variable\&quot;: \&quot;int_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 1,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             },             {                 \&quot;type\&quot;: \&quot;float\&quot;,                 \&quot;question_name\&quot;: \&quot;float\&quot;,                 \&quot;question_description\&quot;: \&quot;I need a float here\&quot;,                 \&quot;variable\&quot;: \&quot;float_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 2,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             }         ]     }
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesCallbackList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesCallbackList200Response apiWorkflowJobTemplatesSurveySpecList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesSurveySpecListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Here is an example survey specification
     *     {         \&quot;name\&quot;: \&quot;Simple Survey\&quot;,         \&quot;description\&quot;: \&quot;Description of the simple survey\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;example question\&quot;,          \&quot;question_description\&quot;: \&quot;What is your favorite color?\&quot;,          \&quot;variable\&quot;: \&quot;favorite_color\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;blue\&quot;             }         ]     }  &#x60;name&#x60; and &#x60;description&#x60; are required elements at the beginning of the survey specification. &#x60;spec&#x60; must be a list of survey items.  Within each survey item &#x60;type&#x60; must be one of:  * text: For survey questions expecting a textual answer * password: For survey questions expecting a password or other sensitive information * integer: For survey questions expecting a whole number answer * float: For survey questions expecting a decimal number * multiplechoice: For survey questions where one option from a list is required * multiselect: For survey questions where multiple items from a presented list can be selected  Each item must contain a &#x60;question_name&#x60; and &#x60;question_description&#x60; field that describes the survey question itself. The &#x60;variable&#x60; elements of each survey items represents the key that will be given to the playbook when the workflow job template is launched.  It will contain the value as a result of the survey.  Here is a more comprehensive example showing the various question types and their acceptable parameters:      {         \&quot;name\&quot;: \&quot;Simple\&quot;,         \&quot;description\&quot;: \&quot;Description\&quot;,         \&quot;spec\&quot;: [             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbeshort\&quot;,          \&quot;question_description\&quot;: \&quot;What is a long answer\&quot;,          \&quot;variable\&quot;: \&quot;long_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: 5,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;Leeloo Minai Lekarariba-Laminai-Tchai Ekbat De Sebat\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;cantbelong\&quot;,          \&quot;question_description\&quot;: \&quot;What is a short answer\&quot;,          \&quot;variable\&quot;: \&quot;short_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: 7,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;leeloo\&quot;             },             {          \&quot;type\&quot;: \&quot;text\&quot;,          \&quot;question_name\&quot;: \&quot;reqd\&quot;,          \&quot;question_description\&quot;: \&quot;I should be required\&quot;,          \&quot;variable\&quot;: \&quot;reqd_answer\&quot;,          \&quot;choices\&quot;: \&quot;\&quot;,          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: true,          \&quot;default\&quot;: \&quot;NOT OPTIONAL\&quot;             },             {          \&quot;type\&quot;: \&quot;multiplechoice\&quot;,          \&quot;question_name\&quot;: \&quot;achoice\&quot;,          \&quot;question_description\&quot;: \&quot;Need one of these\&quot;,          \&quot;variable\&quot;: \&quot;single_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\&quot;             },             {          \&quot;type\&quot;: \&quot;multiselect\&quot;,          \&quot;question_name\&quot;: \&quot;mchoice\&quot;,          \&quot;question_description\&quot;: \&quot;Can have multiples of these\&quot;,          \&quot;variable\&quot;: \&quot;multi_choice\&quot;,          \&quot;choices\&quot;: [\&quot;one\&quot;, \&quot;two\&quot;, \&quot;three\&quot;],          \&quot;min\&quot;: \&quot;\&quot;,          \&quot;max\&quot;: \&quot;\&quot;,          \&quot;required\&quot;: false,          \&quot;default\&quot;: \&quot;one\\nthree\&quot;             },             {                 \&quot;type\&quot;: \&quot;integer\&quot;,                 \&quot;question_name\&quot;: \&quot;integerchoice\&quot;,                 \&quot;question_description\&quot;: \&quot;I need an int here\&quot;,                 \&quot;variable\&quot;: \&quot;int_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 1,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             },             {                 \&quot;type\&quot;: \&quot;float\&quot;,                 \&quot;question_name\&quot;: \&quot;float\&quot;,                 \&quot;question_description\&quot;: \&quot;I need a float here\&quot;,                 \&quot;variable\&quot;: \&quot;float_answer\&quot;,                 \&quot;choices\&quot;: \&quot;\&quot;,                 \&quot;min\&quot;: 2,                 \&quot;max\&quot;: 5,                 \&quot;required\&quot;: false,                 \&quot;default\&quot;: \&quot;\&quot;             }         ]     }
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesCallbackList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesCallbackList200Response> apiWorkflowJobTemplatesSurveySpecListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesSurveySpecList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesSurveySpecList");
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
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/survey_spec/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowJobTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplate apiWorkflowJobTemplatesUpdate(String version, String id, WorkflowJobTemplate data) throws RestClientException {
        return apiWorkflowJobTemplatesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow job template. (integer) * &#x60;type&#x60;: Data type for this workflow job template. (choice) * &#x60;url&#x60;: URL for this workflow job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow job template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow job template. (string) * &#x60;description&#x60;: Optional description of this workflow job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;extra_vars&#x60;:  (json) * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;survey_enabled&#x60;:  (boolean) * &#x60;allow_simultaneous&#x60;:  (boolean) * &#x60;ask_variables_on_launch&#x60;:  (boolean) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;limit&#x60;:  (string) * &#x60;scm_branch&#x60;:  (string) * &#x60;ask_inventory_on_launch&#x60;:  (boolean) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean) * &#x60;ask_limit_on_launch&#x60;:  (boolean) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id) * &#x60;ask_labels_on_launch&#x60;:  (boolean) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean) * &#x60;ask_tags_on_launch&#x60;:  (boolean) * &#x60;skip_tags&#x60;:  (string) * &#x60;job_tags&#x60;:  (string)      # Update a Workflow Job Template:  Make a PUT or PATCH request to this resource to update this workflow job template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow job template. (string, required) * &#x60;description&#x60;: Optional description of this workflow job template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;extra_vars&#x60;:  (json, default&#x3D;&#x60;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;survey_enabled&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;allow_simultaneous&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_variables_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;ask_inventory_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_scm_branch_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_limit_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;webhook_service&#x60;: Service that webhook requests will be accepted from (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;github&#x60;: GitHub     - &#x60;gitlab&#x60;: GitLab     - &#x60;bitbucket_dc&#x60;: BitBucket DataCenter * &#x60;webhook_credential&#x60;: Personal Access Token for posting back the status to the service API (id, default&#x3D;&#x60;&#x60;) * &#x60;ask_labels_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_skip_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;ask_tags_on_launch&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Job Template:  Make a DELETE request to this resource to delete this workflow job template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplate> apiWorkflowJobTemplatesUpdateWithHttpInfo(String version, String id, WorkflowJobTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplatesUpdate");
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

        ParameterizedTypeReference<WorkflowJobTemplate> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
