package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiBulkHostCreateList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiConstructedInventoriesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsCopyList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiHostsSmartInventoriesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.BulkHostCreate;
import com.boehringer.componentprovisioner.client.awx.v2.model.ConstructedInventory;
import com.boehringer.componentprovisioner.client.awx.v2.model.Copy;
import com.boehringer.componentprovisioner.client.awx.v2.model.Inventory;
import com.boehringer.componentprovisioner.client.awx.v2.model.InventoryVariableData;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.InventoriesApi")
public class InventoriesApi extends BaseApi {

    public InventoriesApi() {
        super(new ApiClient());
    }

    @Autowired
    public InventoriesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * This endpoint allows the client to create multiple hosts and associate them with an inventory. They may do this by providing the inventory ID and a list of json that would normally be provided to create hosts.
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return BulkHostCreate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BulkHostCreate apiBulkHostCreateCreate(String version, BulkHostCreate data) throws RestClientException {
        return apiBulkHostCreateCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * This endpoint allows the client to create multiple hosts and associate them with an inventory. They may do this by providing the inventory ID and a list of json that would normally be provided to create hosts.
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;BulkHostCreate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BulkHostCreate> apiBulkHostCreateCreateWithHttpInfo(String version, BulkHostCreate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiBulkHostCreateCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiBulkHostCreateCreate");
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

        ParameterizedTypeReference<BulkHostCreate> localReturnType = new ParameterizedTypeReference<BulkHostCreate>() {};
        return apiClient.invokeAPI("/api/v2/bulk/host_create/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * This endpoint allows the client to create multiple hosts and associate them with an inventory. They may do this by providing the inventory ID and a list of json that would normally be provided to create hosts.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiBulkHostCreateList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiBulkHostCreateList200Response apiBulkHostCreateList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiBulkHostCreateListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * This endpoint allows the client to create multiple hosts and associate them with an inventory. They may do this by providing the inventory ID and a list of json that would normally be provided to create hosts.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiBulkHostCreateList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiBulkHostCreateList200Response> apiBulkHostCreateListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiBulkHostCreateList");
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

        ParameterizedTypeReference<ApiBulkHostCreateList200Response> localReturnType = new ParameterizedTypeReference<ApiBulkHostCreateList200Response>() {};
        return apiClient.invokeAPI("/api/v2/bulk/host_create/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (optional)
     * @return ConstructedInventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConstructedInventory apiConstructedInventoriesCreate(String version, Object data) throws RestClientException {
        return apiConstructedInventoriesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;ConstructedInventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConstructedInventory> apiConstructedInventoriesCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConstructedInventoriesCreate");
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

        ParameterizedTypeReference<ConstructedInventory> localReturnType = new ParameterizedTypeReference<ConstructedInventory>() {};
        return apiClient.invokeAPI("/api/v2/constructed_inventories/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiConstructedInventoriesDelete(String version, String id) throws RestClientException {
        apiConstructedInventoriesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiConstructedInventoriesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConstructedInventoriesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiConstructedInventoriesDelete");
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
        return apiClient.invokeAPI("/api/v2/constructed_inventories/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiConstructedInventoriesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiConstructedInventoriesList200Response apiConstructedInventoriesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiConstructedInventoriesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiConstructedInventoriesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiConstructedInventoriesList200Response> apiConstructedInventoriesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConstructedInventoriesList");
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

        ParameterizedTypeReference<ApiConstructedInventoriesList200Response> localReturnType = new ParameterizedTypeReference<ApiConstructedInventoriesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/constructed_inventories/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ConstructedInventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConstructedInventory apiConstructedInventoriesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiConstructedInventoriesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;ConstructedInventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConstructedInventory> apiConstructedInventoriesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConstructedInventoriesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiConstructedInventoriesPartialUpdate");
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

        ParameterizedTypeReference<ConstructedInventory> localReturnType = new ParameterizedTypeReference<ConstructedInventory>() {};
        return apiClient.invokeAPI("/api/v2/constructed_inventories/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ConstructedInventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConstructedInventory apiConstructedInventoriesRead(String version, String id) throws RestClientException {
        return apiConstructedInventoriesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ConstructedInventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConstructedInventory> apiConstructedInventoriesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConstructedInventoriesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiConstructedInventoriesRead");
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

        ParameterizedTypeReference<ConstructedInventory> localReturnType = new ParameterizedTypeReference<ConstructedInventory>() {};
        return apiClient.invokeAPI("/api/v2/constructed_inventories/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ConstructedInventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConstructedInventory apiConstructedInventoriesUpdate(String version, String id, ConstructedInventory data) throws RestClientException {
        return apiConstructedInventoriesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required)  * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;source_vars&#x60;: The source_vars for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;update_cache_timeout&#x60;: The cache timeout for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;) * &#x60;limit&#x60;: The limit to restrict the returned hosts for the related auto-created inventory source, special to constructed inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;verbosity&#x60;: The verbosity level for the related auto-created inventory source, special to constructed inventory (integer, default&#x3D;&#x60;None&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ConstructedInventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConstructedInventory> apiConstructedInventoriesUpdateWithHttpInfo(String version, String id, ConstructedInventory data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConstructedInventoriesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiConstructedInventoriesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiConstructedInventoriesUpdate");
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

        ParameterizedTypeReference<ConstructedInventory> localReturnType = new ParameterizedTypeReference<ConstructedInventory>() {};
        return apiClient.invokeAPI("/api/v2/constructed_inventories/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiHostsSmartInventoriesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHostsSmartInventoriesList200Response apiHostsSmartInventoriesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsSmartInventoriesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiHostsSmartInventoriesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHostsSmartInventoriesList200Response> apiHostsSmartInventoriesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsSmartInventoriesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsSmartInventoriesList");
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

        ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response> localReturnType = new ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/smart_inventories/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for post on /api/{version}/inventories/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Copy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Copy apiInventoriesCopyCreate(String version, String id, Copy data) throws RestClientException {
        return apiInventoriesCopyCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * No Description for post on /api/{version}/inventories/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Copy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Copy> apiInventoriesCopyCreateWithHttpInfo(String version, String id, Copy data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesCopyCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesCopyCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventoriesCopyCreate");
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
        return apiClient.invokeAPI("/api/v2/inventories/{id}/copy/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/inventories/{id}/copy/
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
    public ApiCredentialsCopyList200Response apiInventoriesCopyList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesCopyListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * No Description for get on /api/{version}/inventories/{id}/copy/
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
    public ResponseEntity<ApiCredentialsCopyList200Response> apiInventoriesCopyListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesCopyList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesCopyList");
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
        return apiClient.invokeAPI("/api/v2/inventories/{id}/copy/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param data  (optional)
     * @return Inventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Inventory apiInventoriesCreate(String version, Object data) throws RestClientException {
        return apiInventoriesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Inventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Inventory> apiInventoriesCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesCreate");
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

        ParameterizedTypeReference<Inventory> localReturnType = new ParameterizedTypeReference<Inventory>() {};
        return apiClient.invokeAPI("/api/v2/inventories/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>204</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiInventoriesDelete(String version, String id) throws RestClientException {
        apiInventoriesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>204</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiInventoriesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesDelete");
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
        return apiClient.invokeAPI("/api/v2/inventories/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Inventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Inventory apiInventoriesInputInventoriesCreate(String version, String id, Inventory data) throws RestClientException {
        return apiInventoriesInputInventoriesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Inventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Inventory> apiInventoriesInputInventoriesCreateWithHttpInfo(String version, String id, Inventory data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesInputInventoriesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesInputInventoriesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventoriesInputInventoriesCreate");
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

        ParameterizedTypeReference<Inventory> localReturnType = new ParameterizedTypeReference<Inventory>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/input_inventories/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiHostsSmartInventoriesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHostsSmartInventoriesList200Response apiInventoriesInputInventoriesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesInputInventoriesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiHostsSmartInventoriesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHostsSmartInventoriesList200Response> apiInventoriesInputInventoriesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesInputInventoriesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesInputInventoriesList");
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

        ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response> localReturnType = new ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/input_inventories/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiHostsSmartInventoriesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHostsSmartInventoriesList200Response apiInventoriesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * inventories.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiHostsSmartInventoriesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHostsSmartInventoriesList200Response> apiInventoriesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesList");
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

        ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response> localReturnType = new ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Inventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Inventory apiInventoriesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Inventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Inventory> apiInventoriesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesPartialUpdate");
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

        ParameterizedTypeReference<Inventory> localReturnType = new ParameterizedTypeReference<Inventory>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Inventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Inventory apiInventoriesRead(String version, String id) throws RestClientException {
        return apiInventoriesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Inventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Inventory> apiInventoriesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesRead");
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

        ParameterizedTypeReference<Inventory> localReturnType = new ParameterizedTypeReference<Inventory>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Refer to [Dynamic Inventory](http://docs.ansible.com/intro_dynamic_inventory.html)
     * for more information on inventory scripts.  ## List Response  Make a GET request to this resource without query parameters to retrieve a JSON object containing groups, including the hosts, children and variables for each group.  The response data is equivalent to that returned by passing the &#x60;--list&#x60; argument to an inventory script.  Specify a query string of &#x60;?hostvars&#x3D;1&#x60; to retrieve the JSON object above including all host variables.  The &#x60;[&#39;_meta&#39;][&#39;hostvars&#39;]&#x60; object in the response contains an entry for each host with its variables.  This response format can be used with Ansible 1.3 and later to avoid making a separate API request for each host.  Refer to [Tuning the External Inventory Script](http://docs.ansible.com/developing_inventory.html#tuning-the-external-inventory-script) for more information on this feature.  By default, the inventory script will only return hosts that are enabled in the inventory.  This feature allows disabled hosts to be skipped when running jobs without removing them from the inventory.  Specify a query string of &#x60;?all&#x3D;1&#x60; to return all hosts, including disabled ones.  Specify a query string of &#x60;?towervars&#x3D;1&#x60; to add variables to the hostvars of each host that specifies its enabled state and database ID.  Specify a query string of &#x60;?subset&#x3D;slice2of5&#x60; to produce an inventory that has a restricted number of hosts according to the rules of job slicing.  To apply multiple query strings, join them with the &#x60;&amp;&#x60; character, like &#x60;?hostvars&#x3D;1&amp;all&#x3D;1&#x60;.  ## Host Response  Make a GET request to this resource with a query string similar to &#x60;?host&#x3D;HOSTNAME&#x60; to retrieve a JSON object containing host variables for the specified host.  The response data is equivalent to that returned by passing the &#x60;--host HOSTNAME&#x60; argument to an inventory script.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiInventoriesScriptRead(String version, String id) throws RestClientException {
        return apiInventoriesScriptReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Refer to [Dynamic Inventory](http://docs.ansible.com/intro_dynamic_inventory.html)
     * for more information on inventory scripts.  ## List Response  Make a GET request to this resource without query parameters to retrieve a JSON object containing groups, including the hosts, children and variables for each group.  The response data is equivalent to that returned by passing the &#x60;--list&#x60; argument to an inventory script.  Specify a query string of &#x60;?hostvars&#x3D;1&#x60; to retrieve the JSON object above including all host variables.  The &#x60;[&#39;_meta&#39;][&#39;hostvars&#39;]&#x60; object in the response contains an entry for each host with its variables.  This response format can be used with Ansible 1.3 and later to avoid making a separate API request for each host.  Refer to [Tuning the External Inventory Script](http://docs.ansible.com/developing_inventory.html#tuning-the-external-inventory-script) for more information on this feature.  By default, the inventory script will only return hosts that are enabled in the inventory.  This feature allows disabled hosts to be skipped when running jobs without removing them from the inventory.  Specify a query string of &#x60;?all&#x3D;1&#x60; to return all hosts, including disabled ones.  Specify a query string of &#x60;?towervars&#x3D;1&#x60; to add variables to the hostvars of each host that specifies its enabled state and database ID.  Specify a query string of &#x60;?subset&#x3D;slice2of5&#x60; to produce an inventory that has a restricted number of hosts according to the rules of job slicing.  To apply multiple query strings, join them with the &#x60;&amp;&#x60; character, like &#x60;?hostvars&#x3D;1&amp;all&#x3D;1&#x60;.  ## Host Response  Make a GET request to this resource with a query string similar to &#x60;?host&#x3D;HOSTNAME&#x60; to retrieve a JSON object containing host variables for the specified host.  The response data is equivalent to that returned by passing the &#x60;--host HOSTNAME&#x60; argument to an inventory script.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiInventoriesScriptReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesScriptRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesScriptRead");
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
        return apiClient.invokeAPI("/api/v2/inventories/{id}/script/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Inventory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Inventory apiInventoriesUpdate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)      # Update an Inventory:  Make a PUT or PATCH request to this resource to update this inventory.  The following fields may be modified:          * &#x60;name&#x60;: Name of this inventory. (string, required) * &#x60;description&#x60;: Optional description of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;: Organization containing this inventory. (id, required) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory. (default)     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json, default&#x3D;&#x60;&#x60;)         * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Inventory:  Make a DELETE request to this resource to delete this inventory.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Inventory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Inventory> apiInventoriesUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesUpdate");
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

        ParameterizedTypeReference<Inventory> localReturnType = new ParameterizedTypeReference<Inventory>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * inventory.    # Update Inventory Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a inventory.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InventoryVariableData
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InventoryVariableData apiInventoriesVariableDataPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesVariableDataPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * inventory.    # Update Inventory Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a inventory.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InventoryVariableData&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InventoryVariableData> apiInventoriesVariableDataPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesVariableDataPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesVariableDataPartialUpdate");
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

        ParameterizedTypeReference<InventoryVariableData> localReturnType = new ParameterizedTypeReference<InventoryVariableData>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/variable_data/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * inventory.    # Update Inventory Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return InventoryVariableData
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InventoryVariableData apiInventoriesVariableDataRead(String version, String id) throws RestClientException {
        return apiInventoriesVariableDataReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * inventory.    # Update Inventory Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a inventory.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;InventoryVariableData&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InventoryVariableData> apiInventoriesVariableDataReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesVariableDataRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesVariableDataRead");
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

        ParameterizedTypeReference<InventoryVariableData> localReturnType = new ParameterizedTypeReference<InventoryVariableData>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/variable_data/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * inventory.    # Update Inventory Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a inventory.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InventoryVariableData
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InventoryVariableData apiInventoriesVariableDataUpdate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesVariableDataUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve all variables defined for a
     * inventory.    # Update Inventory Variable Data:  Make a PUT or PATCH request to this resource to update variables defined for a inventory.
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InventoryVariableData&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InventoryVariableData> apiInventoriesVariableDataUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesVariableDataUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesVariableDataUpdate");
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

        ParameterizedTypeReference<InventoryVariableData> localReturnType = new ParameterizedTypeReference<InventoryVariableData>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/variable_data/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiHostsSmartInventoriesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHostsSmartInventoriesList200Response apiOrganizationsInventoriesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsInventoriesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventories associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventories found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory records.    ## Results  Each inventory data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory. (integer) * &#x60;type&#x60;: Data type for this inventory. (choice) * &#x60;url&#x60;: URL for this inventory. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory. (string) * &#x60;description&#x60;: Optional description of this inventory. (string) * &#x60;organization&#x60;: Organization containing this inventory. (id) * &#x60;kind&#x60;: Kind of inventory being represented. (choice)     - &#x60;\&quot;\&quot;&#x60;: Hosts have a direct link to this inventory.     - &#x60;smart&#x60;: Hosts for inventory generated using the host_filter property.     - &#x60;constructed&#x60;: Parse list of source inventories with the constructed inventory plugin. * &#x60;host_filter&#x60;: Filter that will be applied to the hosts of this inventory. (string) * &#x60;variables&#x60;: Inventory variables in JSON or YAML format. (json) * &#x60;has_active_failures&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether any hosts in this inventory have failed. (boolean) * &#x60;total_hosts&#x60;: This field is deprecated and will be removed in a future release. Total number of hosts in this inventory. (integer) * &#x60;hosts_with_active_failures&#x60;: This field is deprecated and will be removed in a future release. Number of hosts in this inventory with active failures. (integer) * &#x60;total_groups&#x60;: This field is deprecated and will be removed in a future release. Total number of groups in this inventory. (integer) * &#x60;has_inventory_sources&#x60;: This field is deprecated and will be removed in a future release. Flag indicating whether this inventory has any external inventory sources. (boolean) * &#x60;total_inventory_sources&#x60;: Total number of external inventory sources configured within this inventory. (integer) * &#x60;inventory_sources_with_failures&#x60;: Number of external inventory sources in this inventory with failures. (integer) * &#x60;pending_deletion&#x60;: Flag indicating the inventory is being deleted. (boolean) * &#x60;prevent_instance_group_fallback&#x60;: If enabled, the inventory will prevent adding any organization instance groups to the list of preferred instances groups to run associated job templates on.If this setting is enabled and you provided an empty list, the global instance groups will be applied. (boolean) * &#x60;opa_query_path&#x60;: The query path for the OPA policy to evaluate prior to job execution. The query path should be formatted as package/rule. (string)    ## Sorting  To specify that inventories are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiHostsSmartInventoriesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHostsSmartInventoriesList200Response> apiOrganizationsInventoriesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsInventoriesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsInventoriesList");
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

        ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response> localReturnType = new ParameterizedTypeReference<ApiHostsSmartInventoriesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/inventories/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
