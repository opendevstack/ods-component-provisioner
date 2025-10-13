package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.AnsibleFacts;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiBulkHostDeleteList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiGroupsAllHostsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.BulkHostDelete;
import com.boehringer.componentprovisioner.client.awx.v2.model.Host;
import com.boehringer.componentprovisioner.client.awx.v2.model.HostVariableData;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.HostsApi")
public class HostsApi extends BaseApi {

    public HostsApi() {
        super(new ApiClient());
    }

    @Autowired
    public HostsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * This endpoint allows the client to delete multiple hosts from inventories.
     * They may do this by providing a list of hosts ID&#39;s to be deleted.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return BulkHostDelete
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BulkHostDelete apiBulkHostDeleteCreate(String version, BulkHostDelete data) throws RestClientException {
        return apiBulkHostDeleteCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * This endpoint allows the client to delete multiple hosts from inventories.
     * They may do this by providing a list of hosts ID&#39;s to be deleted.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;BulkHostDelete&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BulkHostDelete> apiBulkHostDeleteCreateWithHttpInfo(String version, BulkHostDelete data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiBulkHostDeleteCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiBulkHostDeleteCreate");
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

        ParameterizedTypeReference<BulkHostDelete> localReturnType = new ParameterizedTypeReference<BulkHostDelete>() {};
        return apiClient.invokeAPI("/api/v2/bulk/host_delete/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * This endpoint allows the client to delete multiple hosts from inventories.
     * They may do this by providing a list of hosts ID&#39;s to be deleted.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiBulkHostDeleteList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiBulkHostDeleteList200Response apiBulkHostDeleteList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiBulkHostDeleteListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * This endpoint allows the client to delete multiple hosts from inventories.
     * They may do this by providing a list of hosts ID&#39;s to be deleted.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiBulkHostDeleteList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiBulkHostDeleteList200Response> apiBulkHostDeleteListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiBulkHostDeleteList");
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

        ParameterizedTypeReference<ApiBulkHostDeleteList200Response> localReturnType = new ParameterizedTypeReference<ApiBulkHostDeleteList200Response>() {};
        return apiClient.invokeAPI("/api/v2/bulk/host_delete/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of all
     * hosts directly or indirectly belonging to this group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsAllHostsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsAllHostsList200Response apiGroupsAllHostsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiGroupsAllHostsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of all
     * hosts directly or indirectly belonging to this group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsAllHostsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsAllHostsList200Response> apiGroupsAllHostsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsAllHostsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsAllHostsList");
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

        ParameterizedTypeReference<ApiGroupsAllHostsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsAllHostsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/all_hosts/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Host
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Host apiGroupsHostsCreate(String version, String id, Object data) throws RestClientException {
        return apiGroupsHostsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Host&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Host> apiGroupsHostsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsHostsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsHostsCreate");
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

        ParameterizedTypeReference<Host> localReturnType = new ParameterizedTypeReference<Host>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/hosts/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsAllHostsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsAllHostsList200Response apiGroupsHostsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiGroupsHostsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsAllHostsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsAllHostsList200Response> apiGroupsHostsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsHostsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsHostsList");
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

        ParameterizedTypeReference<ApiGroupsAllHostsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsAllHostsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/hosts/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return AnsibleFacts
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AnsibleFacts apiHostsAnsibleFactsRead(String version, String id) throws RestClientException {
        return apiHostsAnsibleFactsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;AnsibleFacts&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AnsibleFacts> apiHostsAnsibleFactsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsAnsibleFactsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsAnsibleFactsRead");
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

        ParameterizedTypeReference<AnsibleFacts> localReturnType = new ParameterizedTypeReference<AnsibleFacts>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/ansible_facts/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * hosts.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return Host
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Host apiHostsCreate(String version, Host data) throws RestClientException {
        return apiHostsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * hosts.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Host&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Host> apiHostsCreateWithHttpInfo(String version, Host data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiHostsCreate");
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

        ParameterizedTypeReference<Host> localReturnType = new ParameterizedTypeReference<Host>() {};
        return apiClient.invokeAPI("/api/v2/hosts/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiHostsDelete(String version, String id) throws RestClientException {
        apiHostsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiHostsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsDelete");
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
        return apiClient.invokeAPI("/api/v2/hosts/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * hosts.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsAllHostsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsAllHostsList200Response apiHostsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * hosts.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsAllHostsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsAllHostsList200Response> apiHostsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsList");
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

        ParameterizedTypeReference<ApiGroupsAllHostsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsAllHostsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Host
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Host apiHostsPartialUpdate(String version, String id, Host data) throws RestClientException {
        return apiHostsPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Host&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Host> apiHostsPartialUpdateWithHttpInfo(String version, String id, Host data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiHostsPartialUpdate");
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

        ParameterizedTypeReference<Host> localReturnType = new ParameterizedTypeReference<Host>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Host
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Host apiHostsRead(String version, String id) throws RestClientException {
        return apiHostsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Host&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Host> apiHostsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsRead");
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

        ParameterizedTypeReference<Host> localReturnType = new ParameterizedTypeReference<Host>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Host
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Host apiHostsUpdate(String version, String id, Object data) throws RestClientException {
        return apiHostsUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single host
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)      # Update a Host:  Make a PUT or PATCH request to this resource to update this host.  The following fields may be modified:          * &#x60;name&#x60;: Name of this host. (string, required) * &#x60;description&#x60;: Optional description of this host. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;inventory&#x60;:  (id, required) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Host:  Make a DELETE request to this resource to delete this host.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Host&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Host> apiHostsUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsUpdate");
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

        ParameterizedTypeReference<Host> localReturnType = new ParameterizedTypeReference<Host>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * host.    # Update Host Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return HostVariableData
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HostVariableData apiHostsVariableDataPartialUpdate(String version, String id, HostVariableData data) throws RestClientException {
        return apiHostsVariableDataPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * host.    # Update Host Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;HostVariableData&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HostVariableData> apiHostsVariableDataPartialUpdateWithHttpInfo(String version, String id, HostVariableData data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsVariableDataPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsVariableDataPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiHostsVariableDataPartialUpdate");
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
            "application/json", "application/yaml"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json", "application/yaml"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<HostVariableData> localReturnType = new ParameterizedTypeReference<HostVariableData>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/variable_data/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * host.    # Update Host Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return HostVariableData
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HostVariableData apiHostsVariableDataRead(String version, String id) throws RestClientException {
        return apiHostsVariableDataReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * host.    # Update Host Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;HostVariableData&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HostVariableData> apiHostsVariableDataReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsVariableDataRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsVariableDataRead");
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
            "application/json", "application/yaml"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<HostVariableData> localReturnType = new ParameterizedTypeReference<HostVariableData>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/variable_data/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * host.    # Update Host Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return HostVariableData
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HostVariableData apiHostsVariableDataUpdate(String version, String id, HostVariableData data) throws RestClientException {
        return apiHostsVariableDataUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * host.    # Update Host Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a host.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;HostVariableData&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HostVariableData> apiHostsVariableDataUpdateWithHttpInfo(String version, String id, HostVariableData data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsVariableDataUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsVariableDataUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiHostsVariableDataUpdate");
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
            "application/json", "application/yaml"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json", "application/yaml"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<HostVariableData> localReturnType = new ParameterizedTypeReference<HostVariableData>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/variable_data/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Host
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Host apiInventoriesHostsCreate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesHostsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Host&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Host> apiInventoriesHostsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesHostsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesHostsCreate");
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

        ParameterizedTypeReference<Host> localReturnType = new ParameterizedTypeReference<Host>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/hosts/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsAllHostsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsAllHostsList200Response apiInventoriesHostsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesHostsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsAllHostsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsAllHostsList200Response> apiInventoriesHostsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesHostsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesHostsList");
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

        ParameterizedTypeReference<ApiGroupsAllHostsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsAllHostsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/hosts/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiInventorySourcesHostsDelete(String version, String id) throws RestClientException {
        apiInventorySourcesHostsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiInventorySourcesHostsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesHostsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesHostsDelete");
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
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/hosts/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsAllHostsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsAllHostsList200Response apiInventorySourcesHostsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesHostsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * hosts associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of hosts found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host records.    ## Results  Each host data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host. (integer) * &#x60;type&#x60;: Data type for this host. (choice) * &#x60;url&#x60;: URL for this host. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this host was created. (datetime) * &#x60;modified&#x60;: Timestamp when this host was last modified. (datetime) * &#x60;name&#x60;: Name of this host. (string) * &#x60;description&#x60;: Optional description of this host. (string) * &#x60;inventory&#x60;:  (id) * &#x60;enabled&#x60;: Is this host online and available for running jobs? (boolean) * &#x60;instance_id&#x60;: The value used by the remote inventory source to uniquely identify the host (string) * &#x60;variables&#x60;: Host variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;:  (field) * &#x60;has_inventory_sources&#x60;:  (field) * &#x60;last_job&#x60;:  (id) * &#x60;last_job_host_summary&#x60;:  (id) * &#x60;ansible_facts_modified&#x60;: The date and time ansible_facts was last modified. (datetime)    ## Sorting  To specify that hosts are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsAllHostsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsAllHostsList200Response> apiInventorySourcesHostsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesHostsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesHostsList");
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

        ParameterizedTypeReference<ApiGroupsAllHostsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsAllHostsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/hosts/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
