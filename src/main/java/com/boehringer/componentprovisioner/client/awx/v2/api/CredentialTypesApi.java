package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialTypesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.CredentialType;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.CredentialTypesApi")
public class CredentialTypesApi extends BaseApi {

    public CredentialTypesApi() {
        super(new ApiClient());
    }

    @Autowired
    public CredentialTypesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credential types.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential types found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential type records.    ## Results  Each credential type data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)    ## Sorting  To specify that credential types are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return CredentialType
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialType apiCredentialTypesCreate(String version, Object data) throws RestClientException {
        return apiCredentialTypesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credential types.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential types found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential type records.    ## Results  Each credential type data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)    ## Sorting  To specify that credential types are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;CredentialType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialType> apiCredentialTypesCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesCreate");
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

        ParameterizedTypeReference<CredentialType> localReturnType = new ParameterizedTypeReference<CredentialType>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>204</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiCredentialTypesDelete(String version, String id) throws RestClientException {
        apiCredentialTypesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>204</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiCredentialTypesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesDelete");
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
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * credential types.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential types found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential type records.    ## Results  Each credential type data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)    ## Sorting  To specify that credential types are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>401</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiCredentialTypesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiCredentialTypesList200Response apiCredentialTypesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialTypesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * credential types.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of credential types found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more credential type records.    ## Results  Each credential type data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)    ## Sorting  To specify that credential types are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>401</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiCredentialTypesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiCredentialTypesList200Response> apiCredentialTypesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesList");
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

        ParameterizedTypeReference<ApiCredentialTypesList200Response> localReturnType = new ParameterizedTypeReference<ApiCredentialTypesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>200</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return CredentialType
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialType apiCredentialTypesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiCredentialTypesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>200</b> - 
     * <p><b>401</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;CredentialType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialType> apiCredentialTypesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesPartialUpdate");
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

        ParameterizedTypeReference<CredentialType> localReturnType = new ParameterizedTypeReference<CredentialType>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return CredentialType
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialType apiCredentialTypesRead(String version, String id) throws RestClientException {
        return apiCredentialTypesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;CredentialType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialType> apiCredentialTypesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesRead");
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

        ParameterizedTypeReference<CredentialType> localReturnType = new ParameterizedTypeReference<CredentialType>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential type
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
    public Object apiCredentialTypesTestCreate(String version, String id, Object data) throws RestClientException {
        return apiCredentialTypesTestCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential type
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
    public ResponseEntity<Object> apiCredentialTypesTestCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesTestCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesTestCreate");
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
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/test/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiCredentialTypesTestRead(String version, String id) throws RestClientException {
        return apiCredentialTypesTestReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiCredentialTypesTestReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesTestRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesTestRead");
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
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/test/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return CredentialType
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CredentialType apiCredentialTypesUpdate(String version, String id, CredentialType data) throws RestClientException {
        return apiCredentialTypesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single credential type
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this credential type. (integer) * &#x60;type&#x60;: Data type for this credential type. (choice) * &#x60;url&#x60;: URL for this credential type. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this credential type was created. (datetime) * &#x60;modified&#x60;: Timestamp when this credential type was last modified. (datetime) * &#x60;name&#x60;: Name of this credential type. (string) * &#x60;description&#x60;: Optional description of this credential type. (string) * &#x60;kind&#x60;:  (choice)     - &#x60;ssh&#x60;: Machine     - &#x60;vault&#x60;: Vault     - &#x60;net&#x60;: Network     - &#x60;scm&#x60;: Source Control     - &#x60;cloud&#x60;: Cloud     - &#x60;registry&#x60;: Container Registry     - &#x60;token&#x60;: Personal Access Token     - &#x60;insights&#x60;: Insights     - &#x60;external&#x60;: External     - &#x60;kubernetes&#x60;: Kubernetes     - &#x60;galaxy&#x60;: Galaxy/Automation Hub     - &#x60;cryptography&#x60;: Cryptography * &#x60;namespace&#x60;:  (string) * &#x60;managed&#x60;:  (boolean) * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json)      # Update a Credential Type:  Make a PUT or PATCH request to this resource to update this credential type.  The following fields may be modified:          * &#x60;name&#x60;: Name of this credential type. (string, required) * &#x60;description&#x60;: Optional description of this credential type. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;kind&#x60;:  (choice, required)     - &#x60;net&#x60;: Network     - &#x60;cloud&#x60;: Cloud   * &#x60;inputs&#x60;: Enter inputs using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;) * &#x60;injectors&#x60;: Enter injectors using either JSON or YAML syntax. Refer to the documentation for example syntax. (json, default&#x3D;&#x60;{}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Credential Type:  Make a DELETE request to this resource to delete this credential type.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;CredentialType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CredentialType> apiCredentialTypesUpdateWithHttpInfo(String version, String id, CredentialType data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiCredentialTypesUpdate");
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

        ParameterizedTypeReference<CredentialType> localReturnType = new ParameterizedTypeReference<CredentialType>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
