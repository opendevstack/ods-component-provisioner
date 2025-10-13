package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsAccessListList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsOwnerUsersList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.User;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.UsersApi")
public class UsersApi extends BaseApi {

    public UsersApi() {
        super(new ApiClient());
    }

    @Autowired
    public UsersApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiCredentialsAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialsAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiCredentialsAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsOwnerUsersList200Response apiCredentialsOwnerUsersList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialsOwnerUsersListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiCredentialsOwnerUsersListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsOwnerUsersList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsOwnerUsersList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/owner_users/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiInstanceGroupsAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstanceGroupsAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiInstanceGroupsAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiInventoriesAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiInventoriesAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiJobTemplatesAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiJobTemplatesAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to retrieve user information about the current user.
     * One result should be returned containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    Use the primary URL for the user (/api/v2/users/N/) to modify the user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsOwnerUsersList200Response apiMeList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiMeListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to retrieve user information about the current user.
     * One result should be returned containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    Use the primary URL for the user (/api/v2/users/N/) to modify the user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiMeListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiMeList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/me/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiOrganizationsAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiOrganizationsAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * admin users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of admin users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more admin user records.    ## Results  Each admin user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that admin users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiOrganizationsAdminsCreate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsAdminsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * admin users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of admin users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more admin user records.    ## Results  Each admin user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that admin users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiOrganizationsAdminsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsAdminsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsAdminsCreate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/admins/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * admin users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of admin users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more admin user records.    ## Results  Each admin user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that admin users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsOwnerUsersList200Response apiOrganizationsAdminsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsAdminsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * admin users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of admin users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more admin user records.    ## Results  Each admin user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that admin users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiOrganizationsAdminsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsAdminsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsAdminsList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/admins/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiOrganizationsUsersCreate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsUsersCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiOrganizationsUsersCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsUsersCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsUsersCreate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/users/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsOwnerUsersList200Response apiOrganizationsUsersList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsUsersListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiOrganizationsUsersListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsUsersList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsUsersList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/users/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiProjectsAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiProjectsAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users for a Role
     *  Make a GET request to this resource to retrieve a list of users associated with the selected role.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public User apiRolesUsersCreate(String version, String id, Object data) throws RestClientException {
        return apiRolesUsersCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     *  List Users for a Role
     *  Make a GET request to this resource to retrieve a list of users associated with the selected role.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<User> apiRolesUsersCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiRolesUsersCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiRolesUsersCreate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/roles/{id}/users/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users for a Role
     *  Make a GET request to this resource to retrieve a list of users associated with the selected role.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsOwnerUsersList200Response apiRolesUsersList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiRolesUsersListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users for a Role
     *  Make a GET request to this resource to retrieve a list of users associated with the selected role.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiRolesUsersListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiRolesUsersList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiRolesUsersList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/roles/{id}/users/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiTeamsAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiTeamsAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiTeamsAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiTeamsUsersCreate(String version, String id, User data) throws RestClientException {
        return apiTeamsUsersCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiTeamsUsersCreateWithHttpInfo(String version, String id, User data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsUsersCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsUsersCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiTeamsUsersCreate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/users/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsOwnerUsersList200Response apiTeamsUsersList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiTeamsUsersListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * users associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiTeamsUsersListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsUsersList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsUsersList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/users/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiUsersAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiUsersAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param data  (optional)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiUsersCreate(String version, Object data) throws RestClientException {
        return apiUsersCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiUsersCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersCreate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/users/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiUsersDelete(String version, String id) throws RestClientException {
        apiUsersDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiUsersDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersDelete");
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
        return apiClient.invokeAPI("/api/v2/users/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsOwnerUsersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialsOwnerUsersList200Response apiUsersList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsOwnerUsersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialsOwnerUsersList200Response> apiUsersListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersList");
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

        ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsOwnerUsersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiUsersPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiUsersPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiUsersPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersPartialUpdate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiUsersRead(String version, String id) throws RestClientException {
        return apiUsersReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiUsersReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersRead");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User apiUsersUpdate(String version, String id, User data) throws RestClientException {
        return apiUsersUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single user
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)      # Update a User:  Make a PUT or PATCH request to this resource to update this user.  The following fields may be modified:          * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string, required) * &#x60;first_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;last_name&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;email&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;is_system_auditor&#x60;:  (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;password&#x60;: Field used to change the password. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a User:  Make a DELETE request to this resource to delete this user.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> apiUsersUpdateWithHttpInfo(String version, String id, User data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiUsersUpdate");
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

        ParameterizedTypeReference<User> localReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialsAccessListList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ApiCredentialsAccessListList200Response apiWorkflowJobTemplatesAccessListList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesAccessListListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     *  List Users
     *  Make a GET request to this resource to retrieve the list of users.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of users found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more user records.    ## Results  Each user data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this user. (integer) * &#x60;type&#x60;: Data type for this user. (choice) * &#x60;url&#x60;: URL for this user. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this user was created. (datetime) * &#x60;modified&#x60;: Timestamp when this user was last modified. (datetime) * &#x60;username&#x60;: Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only. (string) * &#x60;first_name&#x60;:  (string) * &#x60;last_name&#x60;:  (string) * &#x60;email&#x60;:  (string) * &#x60;is_superuser&#x60;: Designates that this user has all permissions without explicitly assigning them. (boolean) * &#x60;is_system_auditor&#x60;:  (boolean) * &#x60;password&#x60;: Field used to change the password. (string) * &#x60;last_login&#x60;:  (datetime)    ## Sorting  To specify that users are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;username  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-username  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;username,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialsAccessListList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ApiCredentialsAccessListList200Response> apiWorkflowJobTemplatesAccessListListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesAccessListList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesAccessListList");
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

        ParameterizedTypeReference<ApiCredentialsAccessListList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialsAccessListList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/access_list/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
