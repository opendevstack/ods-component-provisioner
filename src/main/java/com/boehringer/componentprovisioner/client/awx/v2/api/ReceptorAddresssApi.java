package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInstancesPeersList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ReceptorAddress;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ReceptorAddresssApi")
public class ReceptorAddresssApi extends BaseApi {

    public ReceptorAddresssApi() {
        super(new ApiClient());
    }

    @Autowired
    public ReceptorAddresssApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * receptor addresss associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of receptor addresss found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more receptor address records.    ## Results  Each receptor address data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)    ## Sorting  To specify that receptor addresss are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstancesPeersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstancesPeersList200Response apiInstancesPeersList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesPeersListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * receptor addresss associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of receptor addresss found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more receptor address records.    ## Results  Each receptor address data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)    ## Sorting  To specify that receptor addresss are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstancesPeersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstancesPeersList200Response> apiInstancesPeersListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesPeersList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesPeersList");
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

        ParameterizedTypeReference<ApiInstancesPeersList200Response> localReturnType = new ParameterizedTypeReference<ApiInstancesPeersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/peers/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * receptor addresss associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of receptor addresss found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more receptor address records.    ## Results  Each receptor address data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)    ## Sorting  To specify that receptor addresss are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstancesPeersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstancesPeersList200Response apiInstancesReceptorAddressesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesReceptorAddressesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * receptor addresss associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of receptor addresss found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more receptor address records.    ## Results  Each receptor address data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)    ## Sorting  To specify that receptor addresss are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstancesPeersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstancesPeersList200Response> apiInstancesReceptorAddressesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesReceptorAddressesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesReceptorAddressesList");
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

        ParameterizedTypeReference<ApiInstancesPeersList200Response> localReturnType = new ParameterizedTypeReference<ApiInstancesPeersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/receptor_addresses/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * receptor addresss.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of receptor addresss found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more receptor address records.    ## Results  Each receptor address data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)    ## Sorting  To specify that receptor addresss are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstancesPeersList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstancesPeersList200Response apiReceptorAddressesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiReceptorAddressesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * receptor addresss.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of receptor addresss found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more receptor address records.    ## Results  Each receptor address data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)    ## Sorting  To specify that receptor addresss are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstancesPeersList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstancesPeersList200Response> apiReceptorAddressesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiReceptorAddressesList");
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

        ParameterizedTypeReference<ApiInstancesPeersList200Response> localReturnType = new ParameterizedTypeReference<ApiInstancesPeersList200Response>() {};
        return apiClient.invokeAPI("/api/v2/receptor_addresses/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single receptor address
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ReceptorAddress
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ReceptorAddress apiReceptorAddressesRead(String version, String id) throws RestClientException {
        return apiReceptorAddressesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single receptor address
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this receptor address. (integer) * &#x60;url&#x60;: URL for this receptor address. (string) * &#x60;address&#x60;: Routable address for this instance. (string) * &#x60;port&#x60;: Port for the address. (integer) * &#x60;protocol&#x60;: Protocol to use for the Receptor listener, &amp;#x27;tcp&amp;#x27;, &amp;#x27;wss&amp;#x27;, or &amp;#x27;ws&amp;#x27;. (choice)     - &#x60;tcp&#x60;: TCP     - &#x60;ws&#x60;: WS     - &#x60;wss&#x60;: WSS * &#x60;websocket_path&#x60;: Websocket path. (string) * &#x60;is_internal&#x60;: If True, only routable within the Kubernetes cluster. (boolean) * &#x60;canonical&#x60;: If True, this address is the canonical address for the instance. (boolean) * &#x60;instance&#x60;:  (id) * &#x60;peers_from_control_nodes&#x60;: If True, control plane cluster nodes should automatically peer to it. (boolean) * &#x60;full_address&#x60;:  (field)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ReceptorAddress&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ReceptorAddress> apiReceptorAddressesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiReceptorAddressesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiReceptorAddressesRead");
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

        ParameterizedTypeReference<ReceptorAddress> localReturnType = new ParameterizedTypeReference<ReceptorAddress>() {};
        return apiClient.invokeAPI("/api/v2/receptor_addresses/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
