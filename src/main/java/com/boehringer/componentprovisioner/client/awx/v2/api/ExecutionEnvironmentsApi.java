package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsCopyList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiExecutionEnvironmentsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Copy;
import com.boehringer.componentprovisioner.client.awx.v2.model.ExecutionEnvironment;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ExecutionEnvironmentsApi")
public class ExecutionEnvironmentsApi extends BaseApi {

    public ExecutionEnvironmentsApi() {
        super(new ApiClient());
    }

    @Autowired
    public ExecutionEnvironmentsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * No Description for post on /api/{version}/execution_environments/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Copy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Copy apiExecutionEnvironmentsCopyCreate(String version, String id, Copy data) throws RestClientException {
        return apiExecutionEnvironmentsCopyCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * No Description for post on /api/{version}/execution_environments/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Copy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Copy> apiExecutionEnvironmentsCopyCreateWithHttpInfo(String version, String id, Copy data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsCopyCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsCopyCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiExecutionEnvironmentsCopyCreate");
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
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/copy/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/execution_environments/{id}/copy/
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsCopyList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsCopyList200Response apiExecutionEnvironmentsCopyList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiExecutionEnvironmentsCopyListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * No Description for get on /api/{version}/execution_environments/{id}/copy/
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsCopyList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsCopyList200Response> apiExecutionEnvironmentsCopyListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsCopyList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsCopyList");
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
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/copy/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * execution environments.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ExecutionEnvironment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExecutionEnvironment apiExecutionEnvironmentsCreate(String version, ExecutionEnvironment data) throws RestClientException {
        return apiExecutionEnvironmentsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * execution environments.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ExecutionEnvironment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExecutionEnvironment> apiExecutionEnvironmentsCreateWithHttpInfo(String version, ExecutionEnvironment data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiExecutionEnvironmentsCreate");
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

        ParameterizedTypeReference<ExecutionEnvironment> localReturnType = new ParameterizedTypeReference<ExecutionEnvironment>() {};
        return apiClient.invokeAPI("/api/v2/execution_environments/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiExecutionEnvironmentsDelete(String version, String id) throws RestClientException {
        apiExecutionEnvironmentsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiExecutionEnvironmentsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsDelete");
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
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * execution environments.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiExecutionEnvironmentsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiExecutionEnvironmentsList200Response apiExecutionEnvironmentsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiExecutionEnvironmentsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * execution environments.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiExecutionEnvironmentsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiExecutionEnvironmentsList200Response> apiExecutionEnvironmentsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsList");
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

        ParameterizedTypeReference<ApiExecutionEnvironmentsList200Response> localReturnType = new ParameterizedTypeReference<ApiExecutionEnvironmentsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/execution_environments/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ExecutionEnvironment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExecutionEnvironment apiExecutionEnvironmentsPartialUpdate(String version, String id, ExecutionEnvironment data) throws RestClientException {
        return apiExecutionEnvironmentsPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ExecutionEnvironment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExecutionEnvironment> apiExecutionEnvironmentsPartialUpdateWithHttpInfo(String version, String id, ExecutionEnvironment data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiExecutionEnvironmentsPartialUpdate");
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

        ParameterizedTypeReference<ExecutionEnvironment> localReturnType = new ParameterizedTypeReference<ExecutionEnvironment>() {};
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ExecutionEnvironment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExecutionEnvironment apiExecutionEnvironmentsRead(String version, String id) throws RestClientException {
        return apiExecutionEnvironmentsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ExecutionEnvironment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExecutionEnvironment> apiExecutionEnvironmentsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsRead");
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

        ParameterizedTypeReference<ExecutionEnvironment> localReturnType = new ParameterizedTypeReference<ExecutionEnvironment>() {};
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ExecutionEnvironment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExecutionEnvironment apiExecutionEnvironmentsUpdate(String version, String id, ExecutionEnvironment data) throws RestClientException {
        return apiExecutionEnvironmentsUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single execution environment
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.      # Update an Execution Environment:  Make a PUT or PATCH request to this resource to update this execution environment.  The following fields may be modified:          * &#x60;name&#x60;: Name of this execution environment. (string, required) * &#x60;description&#x60;: Optional description of this execution environment. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id, default&#x3D;&#x60;&#x60;) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string, required)  * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: --------- (default)     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Execution Environment:  Make a DELETE request to this resource to delete this execution environment.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ExecutionEnvironment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExecutionEnvironment> apiExecutionEnvironmentsUpdateWithHttpInfo(String version, String id, ExecutionEnvironment data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiExecutionEnvironmentsUpdate");
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

        ParameterizedTypeReference<ExecutionEnvironment> localReturnType = new ParameterizedTypeReference<ExecutionEnvironment>() {};
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * execution environments associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ExecutionEnvironment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExecutionEnvironment apiOrganizationsExecutionEnvironmentsCreate(String version, String id, ExecutionEnvironment data) throws RestClientException {
        return apiOrganizationsExecutionEnvironmentsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * execution environments associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ExecutionEnvironment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExecutionEnvironment> apiOrganizationsExecutionEnvironmentsCreateWithHttpInfo(String version, String id, ExecutionEnvironment data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsExecutionEnvironmentsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsExecutionEnvironmentsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsExecutionEnvironmentsCreate");
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

        ParameterizedTypeReference<ExecutionEnvironment> localReturnType = new ParameterizedTypeReference<ExecutionEnvironment>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/execution_environments/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * execution environments associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiExecutionEnvironmentsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiExecutionEnvironmentsList200Response apiOrganizationsExecutionEnvironmentsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsExecutionEnvironmentsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * execution environments associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of execution environments found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more execution environment records.    ## Results  Each execution environment data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this execution environment. (integer) * &#x60;type&#x60;: Data type for this execution environment. (choice) * &#x60;url&#x60;: URL for this execution environment. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this execution environment was created. (datetime) * &#x60;modified&#x60;: Timestamp when this execution environment was last modified. (datetime) * &#x60;name&#x60;: Name of this execution environment. (string) * &#x60;description&#x60;: Optional description of this execution environment. (string) * &#x60;organization&#x60;: The organization used to determine access to this execution environment. (id) * &#x60;image&#x60;: The full image location, including the container registry, image name, and version tag. (string) * &#x60;managed&#x60;:  (boolean) * &#x60;credential&#x60;:  (id) * &#x60;pull&#x60;: Pull image before running? (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;always&#x60;: Always pull container before running.     - &#x60;missing&#x60;: Only pull the image if not present before running.     - &#x60;never&#x60;: Never pull container before running.    ## Sorting  To specify that execution environments are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiExecutionEnvironmentsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiExecutionEnvironmentsList200Response> apiOrganizationsExecutionEnvironmentsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsExecutionEnvironmentsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsExecutionEnvironmentsList");
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

        ParameterizedTypeReference<ApiExecutionEnvironmentsList200Response> localReturnType = new ParameterizedTypeReference<ApiExecutionEnvironmentsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/execution_environments/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
