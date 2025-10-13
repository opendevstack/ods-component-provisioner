package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiGroupsJobHostSummariesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.JobHostSummary;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.JobHostSummariesApi")
public class JobHostSummariesApi extends BaseApi {

    public JobHostSummariesApi() {
        super(new ApiClient());
    }

    @Autowired
    public JobHostSummariesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job host summaries associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job host summaries found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job host summary records.    ## Results  Each job host summary data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)    ## Sorting  To specify that job host summaries are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobHostSummariesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobHostSummariesList200Response apiGroupsJobHostSummariesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiGroupsJobHostSummariesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job host summaries associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job host summaries found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job host summary records.    ## Results  Each job host summary data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)    ## Sorting  To specify that job host summaries are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobHostSummariesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobHostSummariesList200Response> apiGroupsJobHostSummariesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsJobHostSummariesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsJobHostSummariesList");
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

        ParameterizedTypeReference<ApiGroupsJobHostSummariesList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobHostSummariesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/job_host_summaries/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * job host summaries associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job host summaries found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job host summary records.    ## Results  Each job host summary data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)    ## Sorting  To specify that job host summaries are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobHostSummariesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobHostSummariesList200Response apiHostsJobHostSummariesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsJobHostSummariesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job host summaries associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job host summaries found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job host summary records.    ## Results  Each job host summary data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)    ## Sorting  To specify that job host summaries are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobHostSummariesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobHostSummariesList200Response> apiHostsJobHostSummariesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsJobHostSummariesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsJobHostSummariesList");
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

        ParameterizedTypeReference<ApiGroupsJobHostSummariesList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobHostSummariesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/job_host_summaries/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single job host summary
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return JobHostSummary
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobHostSummary apiJobHostSummariesRead(String version, String id) throws RestClientException {
        return apiJobHostSummariesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single job host summary
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;JobHostSummary&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobHostSummary> apiJobHostSummariesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobHostSummariesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobHostSummariesRead");
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

        ParameterizedTypeReference<JobHostSummary> localReturnType = new ParameterizedTypeReference<JobHostSummary>() {};
        return apiClient.invokeAPI("/api/v2/job_host_summaries/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * job host summaries associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job host summaries found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job host summary records.    ## Results  Each job host summary data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)    ## Sorting  To specify that job host summaries are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobHostSummariesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobHostSummariesList200Response apiJobsJobHostSummariesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobsJobHostSummariesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job host summaries associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job host summaries found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job host summary records.    ## Results  Each job host summary data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job host summary. (integer) * &#x60;type&#x60;: Data type for this job host summary. (choice) * &#x60;url&#x60;: URL for this job host summary. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job host summary was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job host summary was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;host&#x60;:  (id) * &#x60;constructed_host&#x60;: Only for jobs run against constructed inventories, this links to the host inside the constructed inventory. (id) * &#x60;host_name&#x60;:  (string) * &#x60;changed&#x60;:  (integer) * &#x60;dark&#x60;:  (integer) * &#x60;failures&#x60;:  (integer) * &#x60;ok&#x60;:  (integer) * &#x60;processed&#x60;:  (integer) * &#x60;skipped&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;ignored&#x60;:  (integer) * &#x60;rescued&#x60;:  (integer)    ## Sorting  To specify that job host summaries are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobHostSummariesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobHostSummariesList200Response> apiJobsJobHostSummariesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsJobHostSummariesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsJobHostSummariesList");
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

        ParameterizedTypeReference<ApiGroupsJobHostSummariesList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobHostSummariesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/job_host_summaries/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
