package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialTypesCredentialsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsCopyList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiOrganizationsCredentialsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiTeamsCredentialsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiUsersCredentialsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Copy;
import com.boehringer.componentprovisioner.client.awx.v2.model.Credential;
import com.boehringer.componentprovisioner.client.awx.v2.model.CredentialSerializerCreate;
import com.boehringer.componentprovisioner.client.awx.v2.model.OrganizationCredentialSerializerCreate;
import com.boehringer.componentprovisioner.client.awx.v2.model.TeamCredentialSerializerCreate;
import com.boehringer.componentprovisioner.client.awx.v2.model.UserCredentialSerializerCreate;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.CredentialsApi")
public class CredentialsApi extends BaseApi {

    public CredentialsApi() {
        super(new ApiClient());
    }

    @Autowired
    public CredentialsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected credential type.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiCredentialTypesCredentialsCreate(String version, String id, Credential data) throws RestClientException {
        return apiCredentialTypesCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected credential type.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiCredentialTypesCredentialsCreateWithHttpInfo(String version, String id, Credential data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesCredentialsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiCredentialTypesCredentialsCreate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected credential type.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiCredentialTypesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialTypesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected credential type.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiCredentialTypesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for post on /api/{version}/credentials/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Copy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Copy apiCredentialsCopyCreate(String version, String id, Copy data) throws RestClientException {
        return apiCredentialsCopyCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * No Description for post on /api/{version}/credentials/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Copy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Copy> apiCredentialsCopyCreateWithHttpInfo(String version, String id, Copy data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsCopyCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsCopyCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiCredentialsCopyCreate");
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
        return apiClient.invokeAPI("/api/v2/credentials/{id}/copy/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/credentials/{id}/copy/
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
    public ApiCredentialsCopyList200Response apiCredentialsCopyList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialsCopyListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * No Description for get on /api/{version}/credentials/{id}/copy/
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
    public ResponseEntity<ApiCredentialsCopyList200Response> apiCredentialsCopyListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsCopyList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsCopyList");
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
        return apiClient.invokeAPI("/api/v2/credentials/{id}/copy/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * credentials.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return CredentialSerializerCreate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialSerializerCreate apiCredentialsCreate(String version, Object data) throws RestClientException {
        return apiCredentialsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credentials.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;CredentialSerializerCreate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialSerializerCreate> apiCredentialsCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsCreate");
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

        ParameterizedTypeReference<CredentialSerializerCreate> localReturnType = new ParameterizedTypeReference<CredentialSerializerCreate>() {};
        return apiClient.invokeAPI("/api/v2/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiCredentialsDelete(String version, String id) throws RestClientException {
        apiCredentialsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiCredentialsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsDelete");
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
        return apiClient.invokeAPI("/api/v2/credentials/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * credentials.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsList200Response apiCredentialsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credentials.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsList200Response> apiCredentialsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiCredentialsPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiCredentialsPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiCredentialsPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsPartialUpdate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiCredentialsRead(String version, String id) throws RestClientException {
        return apiCredentialsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiCredentialsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsRead");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:
     * <p><b>201</b> - 
     * <p><b>202</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiCredentialsTestCreate(String version, String id, Object data) throws RestClientException {
        return apiCredentialsTestCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:
     * <p><b>201</b> - 
     * <p><b>202</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiCredentialsTestCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsTestCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsTestCreate");
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
        return apiClient.invokeAPI("/api/v2/credentials/{id}/test/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiCredentialsTestRead(String version, String id) throws RestClientException {
        return apiCredentialsTestReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiCredentialsTestReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsTestRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsTestRead");
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

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/test/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiCredentialsUpdate(String version, String id, Object data) throws RestClientException {
        return apiCredentialsUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)      # Update a Credential:  Make a PUT or PATCH request to this resource to update this credential.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential. (string, required) * &#x60;description&#x60;: Optional description of this credential. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, default&#x3D;&#x60;None&#x60;) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id, required)  * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)          For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential:  Make a DELETE request to this resource to delete this credential.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiCredentialsUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsUpdate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiInventorySourcesCredentialsCreate(String version, String id, Credential data) throws RestClientException {
        return apiInventorySourcesCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiInventorySourcesCredentialsCreateWithHttpInfo(String version, String id, Credential data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesCredentialsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventorySourcesCredentialsCreate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiInventorySourcesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiInventorySourcesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected inventory update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiInventoryUpdatesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoryUpdatesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected inventory update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiInventoryUpdatesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiJobTemplatesCredentialsCreate(String version, String id, Object data) throws RestClientException {
        return apiJobTemplatesCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiJobTemplatesCredentialsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesCredentialsCreate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiJobTemplatesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiJobTemplatesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiJobsCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobsCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiJobsCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return OrganizationCredentialSerializerCreate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OrganizationCredentialSerializerCreate apiOrganizationsCredentialsCreate(String version, String id, OrganizationCredentialSerializerCreate data) throws RestClientException {
        return apiOrganizationsCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;OrganizationCredentialSerializerCreate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OrganizationCredentialSerializerCreate> apiOrganizationsCredentialsCreateWithHttpInfo(String version, String id, OrganizationCredentialSerializerCreate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsCredentialsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsCredentialsCreate");
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

        ParameterizedTypeReference<OrganizationCredentialSerializerCreate> localReturnType = new ParameterizedTypeReference<OrganizationCredentialSerializerCreate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsCredentialsList200Response apiOrganizationsCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;: Inherit permissions from organization roles. If provided on creation, do not give either user or team. (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsCredentialsList200Response> apiOrganizationsCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsCredentialsList");
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

        ParameterizedTypeReference<ApiOrganizationsCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiOrganizationsGalaxyCredentialsCreate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsGalaxyCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiOrganizationsGalaxyCredentialsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsGalaxyCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsGalaxyCredentialsCreate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/galaxy_credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiOrganizationsGalaxyCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsGalaxyCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiOrganizationsGalaxyCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsGalaxyCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsGalaxyCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/galaxy_credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiSchedulesCredentialsCreate(String version, String id, Credential data) throws RestClientException {
        return apiSchedulesCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiSchedulesCredentialsCreateWithHttpInfo(String version, String id, Credential data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesCredentialsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSchedulesCredentialsCreate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiSchedulesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSchedulesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiSchedulesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return TeamCredentialSerializerCreate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TeamCredentialSerializerCreate apiTeamsCredentialsCreate(String version, String id, Object data) throws RestClientException {
        return apiTeamsCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;TeamCredentialSerializerCreate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TeamCredentialSerializerCreate> apiTeamsCredentialsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsCredentialsCreate");
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

        ParameterizedTypeReference<TeamCredentialSerializerCreate> localReturnType = new ParameterizedTypeReference<TeamCredentialSerializerCreate>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiTeamsCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiTeamsCredentialsList200Response apiTeamsCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiTeamsCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiTeamsCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiTeamsCredentialsList200Response> apiTeamsCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsCredentialsList");
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

        ParameterizedTypeReference<ApiTeamsCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiTeamsCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return UserCredentialSerializerCreate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserCredentialSerializerCreate apiUsersCredentialsCreate(String version, String id, Object data) throws RestClientException {
        return apiUsersCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;UserCredentialSerializerCreate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserCredentialSerializerCreate> apiUsersCredentialsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersCredentialsCreate");
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

        ParameterizedTypeReference<UserCredentialSerializerCreate> localReturnType = new ParameterizedTypeReference<UserCredentialSerializerCreate>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiUsersCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiUsersCredentialsList200Response apiUsersCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)     ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiUsersCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiUsersCredentialsList200Response> apiUsersCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersCredentialsList");
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

        ParameterizedTypeReference<ApiUsersCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiUsersCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiWorkflowJobNodesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobNodesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiWorkflowJobNodesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Credential
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Credential apiWorkflowJobTemplateNodesCredentialsCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplateNodesCredentialsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Credential&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Credential> apiWorkflowJobTemplateNodesCredentialsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesCredentialsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesCredentialsCreate");
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

        ParameterizedTypeReference<Credential> localReturnType = new ParameterizedTypeReference<Credential>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/credentials/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesCredentialsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesCredentialsList200Response apiWorkflowJobTemplateNodesCredentialsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplateNodesCredentialsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * credentials associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credentials found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential records.    ## Results  Each credential data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential. (integer) * &#x60;type&#x60;: Data type for this credential. (choice) * &#x60;url&#x60;: URL for this credential. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential was last modified. (datetime) * &#x60;name&#x60;: Name of this credential. (string) * &#x60;description&#x60;: Optional description of this credential. (string) * &#x60;organization&#x60;:  (id) * &#x60;credential_type&#x60;: Specify the type of credential you want to create. Refer to the documentation for details on each type. (id) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;kind&#x60;:  (field) * &#x60;cloud&#x60;:  (field) * &#x60;kubernetes&#x60;:  (field)    ## Sorting  To specify that credentials are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesCredentialsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesCredentialsList200Response> apiWorkflowJobTemplateNodesCredentialsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesCredentialsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesCredentialsList");
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

        ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesCredentialsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/credentials/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
