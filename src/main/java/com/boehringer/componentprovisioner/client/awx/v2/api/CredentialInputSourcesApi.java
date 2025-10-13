package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialInputSourcesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.CredentialInputSource;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.CredentialInputSourcesApi")
public class CredentialInputSourcesApi extends BaseApi {

    public CredentialInputSourcesApi() {
        super(new ApiClient());
    }

    @Autowired
    public CredentialInputSourcesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credential input sources.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return CredentialInputSource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialInputSource apiCredentialInputSourcesCreate(String version, Object data) throws RestClientException {
        return apiCredentialInputSourcesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credential input sources.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;CredentialInputSource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialInputSource> apiCredentialInputSourcesCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialInputSourcesCreate");
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

        ParameterizedTypeReference<CredentialInputSource> localReturnType = new ParameterizedTypeReference<CredentialInputSource>() {};
        return apiClient.invokeAPI("/api/v2/credential_input_sources/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiCredentialInputSourcesDelete(String version, String id) throws RestClientException {
        apiCredentialInputSourcesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiCredentialInputSourcesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialInputSourcesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialInputSourcesDelete");
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
        return apiClient.invokeAPI("/api/v2/credential_input_sources/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * credential input sources.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialInputSourcesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialInputSourcesList200Response apiCredentialInputSourcesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialInputSourcesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credential input sources.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialInputSourcesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialInputSourcesList200Response> apiCredentialInputSourcesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialInputSourcesList");
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

        ParameterizedTypeReference<ApiCredentialInputSourcesList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialInputSourcesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credential_input_sources/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return CredentialInputSource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialInputSource apiCredentialInputSourcesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiCredentialInputSourcesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;CredentialInputSource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialInputSource> apiCredentialInputSourcesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialInputSourcesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialInputSourcesPartialUpdate");
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

        ParameterizedTypeReference<CredentialInputSource> localReturnType = new ParameterizedTypeReference<CredentialInputSource>() {};
        return apiClient.invokeAPI("/api/v2/credential_input_sources/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return CredentialInputSource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialInputSource apiCredentialInputSourcesRead(String version, String id) throws RestClientException {
        return apiCredentialInputSourcesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;CredentialInputSource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialInputSource> apiCredentialInputSourcesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialInputSourcesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialInputSourcesRead");
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

        ParameterizedTypeReference<CredentialInputSource> localReturnType = new ParameterizedTypeReference<CredentialInputSource>() {};
        return apiClient.invokeAPI("/api/v2/credential_input_sources/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return CredentialInputSource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialInputSource apiCredentialInputSourcesUpdate(String version, String id, CredentialInputSource data) throws RestClientException {
        return apiCredentialInputSourcesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential input source
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)      # Update a Credential Input Source:  Make a PUT or PATCH request to this resource to update this credential input source.  The following fields may be modified:          * &#x60;description&#x60;: Optional description of this credential input source. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;input_field_name&#x60;:  (string, required) * &#x60;metadata&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;target_credential&#x60;:  (id, required) * &#x60;source_credential&#x60;:  (id, required)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Input Source:  Make a DELETE request to this resource to delete this credential input source.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;CredentialInputSource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialInputSource> apiCredentialInputSourcesUpdateWithHttpInfo(String version, String id, CredentialInputSource data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialInputSourcesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialInputSourcesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiCredentialInputSourcesUpdate");
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

        ParameterizedTypeReference<CredentialInputSource> localReturnType = new ParameterizedTypeReference<CredentialInputSource>() {};
        return apiClient.invokeAPI("/api/v2/credential_input_sources/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credential input sources associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return CredentialInputSource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialInputSource apiCredentialsInputSourcesCreate(String version, String id, Object data) throws RestClientException {
        return apiCredentialsInputSourcesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credential input sources associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;CredentialInputSource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialInputSource> apiCredentialsInputSourcesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsInputSourcesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsInputSourcesCreate");
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

        ParameterizedTypeReference<CredentialInputSource> localReturnType = new ParameterizedTypeReference<CredentialInputSource>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/input_sources/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credential input sources associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialInputSourcesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialInputSourcesList200Response apiCredentialsInputSourcesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialsInputSourcesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credential input sources associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential input sources found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential input source records.    ## Results  Each credential input source data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential input source. (integer) * &#x60;type&#x60;: Data type for this credential input source. (choice) * &#x60;url&#x60;: URL for this credential input source. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential input source was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential input source was last modified. (datetime) * &#x60;description&#x60;: Optional description of this credential input source. (string) * &#x60;input_field_name&#x60;:  (string) * &#x60;metadata&#x60;:  (json) * &#x60;target_credential&#x60;:  (id) * &#x60;source_credential&#x60;:  (id)    ## Sorting  To specify that credential input sources are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialInputSourcesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialInputSourcesList200Response> apiCredentialsInputSourcesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsInputSourcesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsInputSourcesList");
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

        ParameterizedTypeReference<ApiCredentialInputSourcesList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialInputSourcesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/input_sources/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
