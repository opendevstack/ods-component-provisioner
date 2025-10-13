package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiOrganizationsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Organization;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.OrganizationsApi")
public class OrganizationsApi extends BaseApi {

    public OrganizationsApi() {
        super(new ApiClient());
    }

    @Autowired
    public OrganizationsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * organizations.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return Organization
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Organization apiOrganizationsCreate(String version, Object data) throws RestClientException {
        return apiOrganizationsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * organizations.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Organization&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Organization> apiOrganizationsCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsCreate");
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

        ParameterizedTypeReference<Organization> localReturnType = new ParameterizedTypeReference<Organization>() {};
        return apiClient.invokeAPI("/api/v2/organizations/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>204</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * <p><b>409</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiOrganizationsDelete(String version, String id) throws RestClientException {
        apiOrganizationsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>204</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * <p><b>409</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiOrganizationsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsDelete");
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
        return apiClient.invokeAPI("/api/v2/organizations/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * organizations.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>401</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsList200Response apiOrganizationsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * organizations.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>401</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsList200Response> apiOrganizationsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsList");
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

        ParameterizedTypeReference<ApiOrganizationsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Organization
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Organization apiOrganizationsPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Organization&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Organization> apiOrganizationsPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsPartialUpdate");
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

        ParameterizedTypeReference<Organization> localReturnType = new ParameterizedTypeReference<Organization>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>200</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return Organization
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Organization apiOrganizationsRead(String version, String id) throws RestClientException {
        return apiOrganizationsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>200</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Organization&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Organization> apiOrganizationsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsRead");
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

        ParameterizedTypeReference<Organization> localReturnType = new ParameterizedTypeReference<Organization>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Organization
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Organization apiOrganizationsUpdate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single organization
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Organization:  Make a PUT or PATCH request to this resource to update this organization.  The following fields may be modified:          * &#x60;name&#x60;: Name of this organization. (string, required) * &#x60;description&#x60;: Optional description of this organization. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer, default&#x3D;&#x60;0&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id, default&#x3D;&#x60;&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Organization:  Make a DELETE request to this resource to delete this organization.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Organization&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Organization> apiOrganizationsUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsUpdate");
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

        ParameterizedTypeReference<Organization> localReturnType = new ParameterizedTypeReference<Organization>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * organizations of which the selected user is an admin.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsList200Response apiUsersAdminOfOrganizationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersAdminOfOrganizationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * organizations of which the selected user is an admin.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsList200Response> apiUsersAdminOfOrganizationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersAdminOfOrganizationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersAdminOfOrganizationsList");
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

        ParameterizedTypeReference<ApiOrganizationsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/admin_of_organizations/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * organizations associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsList200Response apiUsersOrganizationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersOrganizationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * organizations associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of organizations found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more organization records.    ## Results  Each organization data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this organization. (integer) * &#x60;type&#x60;: Data type for this organization. (choice) * &#x60;url&#x60;: URL for this organization. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this organization was created. (datetime) * &#x60;modified&#x60;: Timestamp when this organization was last modified. (datetime) * &#x60;name&#x60;: Name of this organization. (string) * &#x60;description&#x60;: Optional description of this organization. (string) * &#x60;max_hosts&#x60;: Maximum number of hosts allowed to be managed by this organization. (integer) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run by this organization. (id) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that organizations are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsList200Response> apiUsersOrganizationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersOrganizationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersOrganizationsList");
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

        ParameterizedTypeReference<ApiOrganizationsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/organizations/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
