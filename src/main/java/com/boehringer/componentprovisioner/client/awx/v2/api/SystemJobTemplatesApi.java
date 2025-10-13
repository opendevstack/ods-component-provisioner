package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiJobTemplatesCallbackList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiSystemJobTemplatesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.SystemJobTemplate;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.SystemJobTemplatesApi")
public class SystemJobTemplatesApi extends BaseApi {

    public SystemJobTemplatesApi() {
        super(new ApiClient());
    }

    @Autowired
    public SystemJobTemplatesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a POST request to this resource to launch the system job template.
     * Variables specified inside of the parameter &#x60;extra_vars&#x60; are passed to the system job task as command line parameters. These tasks can be run manually on the host system via the &#x60;awx-manage&#x60; command.  For example on &#x60;cleanup_jobs&#x60; and &#x60;cleanup_activitystream&#x60;:  &#x60;{\&quot;extra_vars\&quot;: {\&quot;days\&quot;: 30}}&#x60;  Which will act on data older than 30 days.  For &#x60;cleanup_activitystream&#x60; and &#x60;cleanup_jobs&#x60; commands, providing &#x60;\&quot;dry_run\&quot;: true&#x60; inside of &#x60;extra_vars&#x60; will show items that will be removed without deleting them.  Each individual system job task has its own default values, which are applicable either when running it from the command line or launching its system job template with empty &#x60;extra_vars&#x60;.   - Defaults for &#x60;cleanup_activitystream&#x60;: days&#x3D;90  - Defaults for &#x60;cleanup_jobs&#x60;: days&#x3D;90  If successful, the response status code will be 202.  If the job cannot be launched, a 405 status code will be returned.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiSystemJobTemplatesLaunchCreate(String version, String id, Object data) throws RestClientException {
        return apiSystemJobTemplatesLaunchCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a POST request to this resource to launch the system job template.
     * Variables specified inside of the parameter &#x60;extra_vars&#x60; are passed to the system job task as command line parameters. These tasks can be run manually on the host system via the &#x60;awx-manage&#x60; command.  For example on &#x60;cleanup_jobs&#x60; and &#x60;cleanup_activitystream&#x60;:  &#x60;{\&quot;extra_vars\&quot;: {\&quot;days\&quot;: 30}}&#x60;  Which will act on data older than 30 days.  For &#x60;cleanup_activitystream&#x60; and &#x60;cleanup_jobs&#x60; commands, providing &#x60;\&quot;dry_run\&quot;: true&#x60; inside of &#x60;extra_vars&#x60; will show items that will be removed without deleting them.  Each individual system job task has its own default values, which are applicable either when running it from the command line or launching its system job template with empty &#x60;extra_vars&#x60;.   - Defaults for &#x60;cleanup_activitystream&#x60;: days&#x3D;90  - Defaults for &#x60;cleanup_jobs&#x60;: days&#x3D;90  If successful, the response status code will be 202.  If the job cannot be launched, a 405 status code will be returned.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiSystemJobTemplatesLaunchCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesLaunchCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesLaunchCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSystemJobTemplatesLaunchCreate");
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
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/launch/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this resource to launch the system job template.
     * Variables specified inside of the parameter &#x60;extra_vars&#x60; are passed to the system job task as command line parameters. These tasks can be run manually on the host system via the &#x60;awx-manage&#x60; command.  For example on &#x60;cleanup_jobs&#x60; and &#x60;cleanup_activitystream&#x60;:  &#x60;{\&quot;extra_vars\&quot;: {\&quot;days\&quot;: 30}}&#x60;  Which will act on data older than 30 days.  For &#x60;cleanup_activitystream&#x60; and &#x60;cleanup_jobs&#x60; commands, providing &#x60;\&quot;dry_run\&quot;: true&#x60; inside of &#x60;extra_vars&#x60; will show items that will be removed without deleting them.  Each individual system job task has its own default values, which are applicable either when running it from the command line or launching its system job template with empty &#x60;extra_vars&#x60;.   - Defaults for &#x60;cleanup_activitystream&#x60;: days&#x3D;90  - Defaults for &#x60;cleanup_jobs&#x60;: days&#x3D;90  If successful, the response status code will be 202.  If the job cannot be launched, a 405 status code will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiJobTemplatesCallbackList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiJobTemplatesCallbackList200Response apiSystemJobTemplatesLaunchList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobTemplatesLaunchListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a POST request to this resource to launch the system job template.
     * Variables specified inside of the parameter &#x60;extra_vars&#x60; are passed to the system job task as command line parameters. These tasks can be run manually on the host system via the &#x60;awx-manage&#x60; command.  For example on &#x60;cleanup_jobs&#x60; and &#x60;cleanup_activitystream&#x60;:  &#x60;{\&quot;extra_vars\&quot;: {\&quot;days\&quot;: 30}}&#x60;  Which will act on data older than 30 days.  For &#x60;cleanup_activitystream&#x60; and &#x60;cleanup_jobs&#x60; commands, providing &#x60;\&quot;dry_run\&quot;: true&#x60; inside of &#x60;extra_vars&#x60; will show items that will be removed without deleting them.  Each individual system job task has its own default values, which are applicable either when running it from the command line or launching its system job template with empty &#x60;extra_vars&#x60;.   - Defaults for &#x60;cleanup_activitystream&#x60;: days&#x3D;90  - Defaults for &#x60;cleanup_jobs&#x60;: days&#x3D;90  If successful, the response status code will be 202.  If the job cannot be launched, a 405 status code will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiJobTemplatesCallbackList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiJobTemplatesCallbackList200Response> apiSystemJobTemplatesLaunchListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesLaunchList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesLaunchList");
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
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/launch/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * system job templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of system job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more system job template records.    ## Results  Each system job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this system job template. (integer) * &#x60;type&#x60;: Data type for this system job template. (choice) * &#x60;url&#x60;: URL for this system job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this system job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this system job template was last modified. (datetime) * &#x60;name&#x60;: Name of this system job template. (string) * &#x60;description&#x60;: Optional description of this system job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;cleanup_jobs&#x60;: Remove jobs older than a certain number of days     - &#x60;cleanup_activitystream&#x60;: Remove activity stream entries older than a certain number of days     - &#x60;cleanup_sessions&#x60;: Removes expired browser sessions from the database    ## Sorting  To specify that system job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiSystemJobTemplatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiSystemJobTemplatesList200Response apiSystemJobTemplatesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobTemplatesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * system job templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of system job templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more system job template records.    ## Results  Each system job template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this system job template. (integer) * &#x60;type&#x60;: Data type for this system job template. (choice) * &#x60;url&#x60;: URL for this system job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this system job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this system job template was last modified. (datetime) * &#x60;name&#x60;: Name of this system job template. (string) * &#x60;description&#x60;: Optional description of this system job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;cleanup_jobs&#x60;: Remove jobs older than a certain number of days     - &#x60;cleanup_activitystream&#x60;: Remove activity stream entries older than a certain number of days     - &#x60;cleanup_sessions&#x60;: Removes expired browser sessions from the database    ## Sorting  To specify that system job templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiSystemJobTemplatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiSystemJobTemplatesList200Response> apiSystemJobTemplatesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesList");
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

        ParameterizedTypeReference<ApiSystemJobTemplatesList200Response> localReturnType = new ParameterizedTypeReference<ApiSystemJobTemplatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single system job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this system job template. (integer) * &#x60;type&#x60;: Data type for this system job template. (choice) * &#x60;url&#x60;: URL for this system job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this system job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this system job template was last modified. (datetime) * &#x60;name&#x60;: Name of this system job template. (string) * &#x60;description&#x60;: Optional description of this system job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;cleanup_jobs&#x60;: Remove jobs older than a certain number of days     - &#x60;cleanup_activitystream&#x60;: Remove activity stream entries older than a certain number of days     - &#x60;cleanup_sessions&#x60;: Removes expired browser sessions from the database
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return SystemJobTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SystemJobTemplate apiSystemJobTemplatesRead(String version, String id) throws RestClientException {
        return apiSystemJobTemplatesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single system job template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this system job template. (integer) * &#x60;type&#x60;: Data type for this system job template. (choice) * &#x60;url&#x60;: URL for this system job template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this system job template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this system job template was last modified. (datetime) * &#x60;name&#x60;: Name of this system job template. (string) * &#x60;description&#x60;: Optional description of this system job template. (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing     - &#x60;none&#x60;: No External Source     - &#x60;updating&#x60;: Updating * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;cleanup_jobs&#x60;: Remove jobs older than a certain number of days     - &#x60;cleanup_activitystream&#x60;: Remove activity stream entries older than a certain number of days     - &#x60;cleanup_sessions&#x60;: Removes expired browser sessions from the database
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;SystemJobTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SystemJobTemplate> apiSystemJobTemplatesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesRead");
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

        ParameterizedTypeReference<SystemJobTemplate> localReturnType = new ParameterizedTypeReference<SystemJobTemplate>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
