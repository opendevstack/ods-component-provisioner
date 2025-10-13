package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowApprovalTemplate;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobTemplateNodeCreateApproval;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.WorkflowApprovalTemplatesApi")
public class WorkflowApprovalTemplatesApi extends BaseApi {

    public WorkflowApprovalTemplatesApi() {
        super(new ApiClient());
    }

    @Autowired
    public WorkflowApprovalTemplatesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiWorkflowApprovalTemplatesDelete(String version, String id) throws RestClientException {
        apiWorkflowApprovalTemplatesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiWorkflowApprovalTemplatesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowApprovalTemplatesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowApprovalTemplatesDelete");
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
        return apiClient.invokeAPI("/api/v2/workflow_approval_templates/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowApprovalTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowApprovalTemplate apiWorkflowApprovalTemplatesPartialUpdate(String version, String id, WorkflowApprovalTemplate data) throws RestClientException {
        return apiWorkflowApprovalTemplatesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowApprovalTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowApprovalTemplate> apiWorkflowApprovalTemplatesPartialUpdateWithHttpInfo(String version, String id, WorkflowApprovalTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowApprovalTemplatesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowApprovalTemplatesPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowApprovalTemplatesPartialUpdate");
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

        ParameterizedTypeReference<WorkflowApprovalTemplate> localReturnType = new ParameterizedTypeReference<WorkflowApprovalTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_approval_templates/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowApprovalTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowApprovalTemplate apiWorkflowApprovalTemplatesRead(String version, String id) throws RestClientException {
        return apiWorkflowApprovalTemplatesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowApprovalTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowApprovalTemplate> apiWorkflowApprovalTemplatesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowApprovalTemplatesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowApprovalTemplatesRead");
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

        ParameterizedTypeReference<WorkflowApprovalTemplate> localReturnType = new ParameterizedTypeReference<WorkflowApprovalTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_approval_templates/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return WorkflowApprovalTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowApprovalTemplate apiWorkflowApprovalTemplatesUpdate(String version, String id, WorkflowApprovalTemplate data) throws RestClientException {
        return apiWorkflowApprovalTemplatesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow approval template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this workflow approval template. (integer) * &#x60;type&#x60;: Data type for this workflow approval template. (choice) * &#x60;url&#x60;: URL for this workflow approval template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this workflow approval template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this workflow approval template was last modified. (datetime) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer)      # Update a Workflow Approval Template:  Make a PUT or PATCH request to this resource to update this workflow approval template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this workflow approval template. (string, required) * &#x60;description&#x60;: Optional description of this workflow approval template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)     * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer, default&#x3D;&#x60;0&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Workflow Approval Template:  Make a DELETE request to this resource to delete this workflow approval template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;WorkflowApprovalTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowApprovalTemplate> apiWorkflowApprovalTemplatesUpdateWithHttpInfo(String version, String id, WorkflowApprovalTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowApprovalTemplatesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowApprovalTemplatesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowApprovalTemplatesUpdate");
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

        ParameterizedTypeReference<WorkflowApprovalTemplate> localReturnType = new ParameterizedTypeReference<WorkflowApprovalTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_approval_templates/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string)
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return WorkflowJobTemplateNodeCreateApproval
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNodeCreateApproval apiWorkflowJobTemplateNodesCreateApprovalTemplateCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplateNodesCreateApprovalTemplateCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string)
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;WorkflowJobTemplateNodeCreateApproval&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNodeCreateApproval> apiWorkflowJobTemplateNodesCreateApprovalTemplateCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesCreateApprovalTemplateCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesCreateApprovalTemplateCreate");
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

        ParameterizedTypeReference<WorkflowJobTemplateNodeCreateApproval> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNodeCreateApproval>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/create_approval_template/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string)
     * <p><b>200</b> - 
     * <p><b>404</b>
     * @param version  (required)
     * @param id  (required)
     * @return WorkflowJobTemplateNodeCreateApproval
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public WorkflowJobTemplateNodeCreateApproval apiWorkflowJobTemplateNodesCreateApprovalTemplateRead(String version, String id) throws RestClientException {
        return apiWorkflowJobTemplateNodesCreateApprovalTemplateReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single workflow job template node
     * record containing the following fields:  * &#x60;timeout&#x60;: The amount of time (in seconds) before the approval node expires and fails. (integer) * &#x60;name&#x60;: Name of this workflow approval template. (string) * &#x60;description&#x60;: Optional description of this workflow approval template. (string)
     * <p><b>200</b> - 
     * <p><b>404</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;WorkflowJobTemplateNodeCreateApproval&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<WorkflowJobTemplateNodeCreateApproval> apiWorkflowJobTemplateNodesCreateApprovalTemplateReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesCreateApprovalTemplateRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesCreateApprovalTemplateRead");
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

        ParameterizedTypeReference<WorkflowJobTemplateNodeCreateApproval> localReturnType = new ParameterizedTypeReference<WorkflowJobTemplateNodeCreateApproval>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/create_approval_template/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
