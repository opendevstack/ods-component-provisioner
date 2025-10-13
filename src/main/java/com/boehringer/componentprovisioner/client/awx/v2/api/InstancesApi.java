package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInstanceGroupsInstancesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInstancesHealthCheckList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Instance;
import com.boehringer.componentprovisioner.client.awx.v2.model.InstanceHealthCheck;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.InstancesApi")
public class InstancesApi extends BaseApi {

    public InstancesApi() {
        super(new ApiClient());
    }

    @Autowired
    public InstancesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instances associated with the selected instance group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Instance
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Instance apiInstanceGroupsInstancesCreate(String version, String id, Object data) throws RestClientException {
        return apiInstanceGroupsInstancesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instances associated with the selected instance group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Instance&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Instance> apiInstanceGroupsInstancesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsInstancesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsInstancesCreate");
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

        ParameterizedTypeReference<Instance> localReturnType = new ParameterizedTypeReference<Instance>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/instances/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instances associated with the selected instance group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsInstancesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsInstancesList200Response apiInstanceGroupsInstancesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstanceGroupsInstancesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instances associated with the selected instance group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsInstancesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsInstancesList200Response> apiInstanceGroupsInstancesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsInstancesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsInstancesList");
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

        ParameterizedTypeReference<ApiInstanceGroupsInstancesList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsInstancesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/instances/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * instances.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param data  (optional)
     * @return Instance
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Instance apiInstancesCreate(String version, Object data) throws RestClientException {
        return apiInstancesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * instances.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Instance&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Instance> apiInstancesCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesCreate");
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

        ParameterizedTypeReference<Instance> localReturnType = new ParameterizedTypeReference<Instance>() {};
        return apiClient.invokeAPI("/api/v2/instances/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Health checks are used to obtain important data about an instance.
     * Instance fields affected by the health check are shown in this view. Fundamentally, health checks require running code on the machine in question.   - For instances with &#x60;node_type&#x60; of \&quot;control\&quot; or \&quot;hybrid\&quot;, health checks are performed as part of a periodic task that runs in the background.  - For instances with &#x60;node_type&#x60; of \&quot;execution\&quot;, health checks are done by submitting a work unit through the receptor mesh.  If ran through the receptor mesh, the invoked command is:  &#x60;&#x60;&#x60; ansible-runner worker --worker-info &#x60;&#x60;&#x60;  For execution nodes, these checks are _not_ performed on a regular basis. Health checks against functional nodes will be ran when the node is first discovered. Health checks against nodes with errors will be repeated at a reduced frequency.     # Manually Initiate a Health Check For purposes of error remediation or debugging, a health check can be manually initiated by making a POST request to this endpoint.  This will submit the work unit to the target node through the receptor mesh and wait for it to finish. The model will be updated with the result. Up-to-date values of the fields will be returned in the response data.
     * <p><b>200</b>
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiInstancesHealthCheckCreate(String version, String id, InstanceHealthCheck data) throws RestClientException {
        apiInstancesHealthCheckCreateWithHttpInfo(version, id, data);
    }

    /**
     * Health checks are used to obtain important data about an instance.
     * Instance fields affected by the health check are shown in this view. Fundamentally, health checks require running code on the machine in question.   - For instances with &#x60;node_type&#x60; of \&quot;control\&quot; or \&quot;hybrid\&quot;, health checks are performed as part of a periodic task that runs in the background.  - For instances with &#x60;node_type&#x60; of \&quot;execution\&quot;, health checks are done by submitting a work unit through the receptor mesh.  If ran through the receptor mesh, the invoked command is:  &#x60;&#x60;&#x60; ansible-runner worker --worker-info &#x60;&#x60;&#x60;  For execution nodes, these checks are _not_ performed on a regular basis. Health checks against functional nodes will be ran when the node is first discovered. Health checks against nodes with errors will be repeated at a reduced frequency.     # Manually Initiate a Health Check For purposes of error remediation or debugging, a health check can be manually initiated by making a POST request to this endpoint.  This will submit the work unit to the target node through the receptor mesh and wait for it to finish. The model will be updated with the result. Up-to-date values of the fields will be returned in the response data.
     * <p><b>200</b>
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiInstancesHealthCheckCreateWithHttpInfo(String version, String id, InstanceHealthCheck data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesHealthCheckCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesHealthCheckCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInstancesHealthCheckCreate");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/health_check/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Health checks are used to obtain important data about an instance.
     * Instance fields affected by the health check are shown in this view. Fundamentally, health checks require running code on the machine in question.   - For instances with &#x60;node_type&#x60; of \&quot;control\&quot; or \&quot;hybrid\&quot;, health checks are performed as part of a periodic task that runs in the background.  - For instances with &#x60;node_type&#x60; of \&quot;execution\&quot;, health checks are done by submitting a work unit through the receptor mesh.  If ran through the receptor mesh, the invoked command is:  &#x60;&#x60;&#x60; ansible-runner worker --worker-info &#x60;&#x60;&#x60;  For execution nodes, these checks are _not_ performed on a regular basis. Health checks against functional nodes will be ran when the node is first discovered. Health checks against nodes with errors will be repeated at a reduced frequency.     # Manually Initiate a Health Check For purposes of error remediation or debugging, a health check can be manually initiated by making a POST request to this endpoint.  This will submit the work unit to the target node through the receptor mesh and wait for it to finish. The model will be updated with the result. Up-to-date values of the fields will be returned in the response data.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstancesHealthCheckList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstancesHealthCheckList200Response apiInstancesHealthCheckList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesHealthCheckListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Health checks are used to obtain important data about an instance.
     * Instance fields affected by the health check are shown in this view. Fundamentally, health checks require running code on the machine in question.   - For instances with &#x60;node_type&#x60; of \&quot;control\&quot; or \&quot;hybrid\&quot;, health checks are performed as part of a periodic task that runs in the background.  - For instances with &#x60;node_type&#x60; of \&quot;execution\&quot;, health checks are done by submitting a work unit through the receptor mesh.  If ran through the receptor mesh, the invoked command is:  &#x60;&#x60;&#x60; ansible-runner worker --worker-info &#x60;&#x60;&#x60;  For execution nodes, these checks are _not_ performed on a regular basis. Health checks against functional nodes will be ran when the node is first discovered. Health checks against nodes with errors will be repeated at a reduced frequency.     # Manually Initiate a Health Check For purposes of error remediation or debugging, a health check can be manually initiated by making a POST request to this endpoint.  This will submit the work unit to the target node through the receptor mesh and wait for it to finish. The model will be updated with the result. Up-to-date values of the fields will be returned in the response data.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstancesHealthCheckList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstancesHealthCheckList200Response> apiInstancesHealthCheckListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesHealthCheckList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesHealthCheckList");
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

        ParameterizedTypeReference<ApiInstancesHealthCheckList200Response> localReturnType = new ParameterizedTypeReference<ApiInstancesHealthCheckList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/health_check/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/instances/{id}/install_bundle/
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsInstancesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsInstancesList200Response apiInstancesInstallBundleList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesInstallBundleListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * No Description for get on /api/{version}/instances/{id}/install_bundle/
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsInstancesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsInstancesList200Response> apiInstancesInstallBundleListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesInstallBundleList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesInstallBundleList");
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

        ParameterizedTypeReference<ApiInstanceGroupsInstancesList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsInstancesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/install_bundle/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * instances.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsInstancesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsInstancesList200Response apiInstancesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * instances.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instances found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance records.    ## Results  Each instance data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)    ## Sorting  To specify that instances are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsInstancesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsInstancesList200Response> apiInstancesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesList");
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

        ParameterizedTypeReference<ApiInstanceGroupsInstancesList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsInstancesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)      # Update an Instance:  Make a PUT or PATCH request to this resource to update this instance.  The following fields may be modified:    * &#x60;hostname&#x60;:  (string, required)             * &#x60;capacity_adjustment&#x60;:  (decimal, default&#x3D;&#x60;1&#x60;)           * &#x60;enabled&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;managed_by_policy&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node (default)     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed (default)     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure   * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field, default&#x3D;&#x60;None&#x60;)  * &#x60;listener_port&#x60;:  (integer, default&#x3D;&#x60;&#x60;) * &#x60;peers_from_control_nodes&#x60;:  (boolean, default&#x3D;&#x60;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Instance
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Instance apiInstancesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiInstancesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single instance
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)      # Update an Instance:  Make a PUT or PATCH request to this resource to update this instance.  The following fields may be modified:    * &#x60;hostname&#x60;:  (string, required)             * &#x60;capacity_adjustment&#x60;:  (decimal, default&#x3D;&#x60;1&#x60;)           * &#x60;enabled&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;managed_by_policy&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node (default)     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed (default)     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure   * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field, default&#x3D;&#x60;None&#x60;)  * &#x60;listener_port&#x60;:  (integer, default&#x3D;&#x60;&#x60;) * &#x60;peers_from_control_nodes&#x60;:  (boolean, default&#x3D;&#x60;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Instance&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Instance> apiInstancesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesPartialUpdate");
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

        ParameterizedTypeReference<Instance> localReturnType = new ParameterizedTypeReference<Instance>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)      # Update an Instance:  Make a PUT or PATCH request to this resource to update this instance.  The following fields may be modified:    * &#x60;hostname&#x60;:  (string, required)             * &#x60;capacity_adjustment&#x60;:  (decimal, default&#x3D;&#x60;1&#x60;)           * &#x60;enabled&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;managed_by_policy&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node (default)     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed (default)     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure   * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field, default&#x3D;&#x60;None&#x60;)  * &#x60;listener_port&#x60;:  (integer, default&#x3D;&#x60;&#x60;) * &#x60;peers_from_control_nodes&#x60;:  (boolean, default&#x3D;&#x60;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Instance
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Instance apiInstancesRead(String version, String id) throws RestClientException {
        return apiInstancesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single instance
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)      # Update an Instance:  Make a PUT or PATCH request to this resource to update this instance.  The following fields may be modified:    * &#x60;hostname&#x60;:  (string, required)             * &#x60;capacity_adjustment&#x60;:  (decimal, default&#x3D;&#x60;1&#x60;)           * &#x60;enabled&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;managed_by_policy&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node (default)     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed (default)     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure   * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field, default&#x3D;&#x60;None&#x60;)  * &#x60;listener_port&#x60;:  (integer, default&#x3D;&#x60;&#x60;) * &#x60;peers_from_control_nodes&#x60;:  (boolean, default&#x3D;&#x60;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Instance&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Instance> apiInstancesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesRead");
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

        ParameterizedTypeReference<Instance> localReturnType = new ParameterizedTypeReference<Instance>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)      # Update an Instance:  Make a PUT or PATCH request to this resource to update this instance.  The following fields may be modified:    * &#x60;hostname&#x60;:  (string, required)             * &#x60;capacity_adjustment&#x60;:  (decimal, default&#x3D;&#x60;1&#x60;)           * &#x60;enabled&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;managed_by_policy&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node (default)     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed (default)     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure   * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field, default&#x3D;&#x60;None&#x60;)  * &#x60;listener_port&#x60;:  (integer, default&#x3D;&#x60;&#x60;) * &#x60;peers_from_control_nodes&#x60;:  (boolean, default&#x3D;&#x60;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Instance
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Instance apiInstancesUpdate(String version, String id, Instance data) throws RestClientException {
        return apiInstancesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single instance
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;type&#x60;: Data type for this instance. (choice) * &#x60;url&#x60;: URL for this instance. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;uuid&#x60;:  (string) * &#x60;created&#x60;: Timestamp when this instance was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance was last modified. (datetime) * &#x60;last_seen&#x60;: Last time instance ran its heartbeat task for main cluster nodes. Last known connection to receptor mesh for execution nodes. (datetime) * &#x60;health_check_started&#x60;: The last time a health check was initiated on this instance. (datetime) * &#x60;health_check_pending&#x60;:  (field) * &#x60;last_health_check&#x60;: Last time a health check was ran on this instance to refresh cpu, memory, and capacity. (datetime) * &#x60;errors&#x60;: Any error details from the last health check. (string) * &#x60;capacity_adjustment&#x60;:  (decimal) * &#x60;version&#x60;:  (string) * &#x60;capacity&#x60;:  (integer) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;: Count of jobs in the running or waiting state that are targeted for this instance (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance (integer) * &#x60;cpu&#x60;:  (decimal) * &#x60;memory&#x60;: Total system memory of this instance in bytes. (integer) * &#x60;cpu_capacity&#x60;:  (integer) * &#x60;mem_capacity&#x60;:  (integer) * &#x60;enabled&#x60;:  (boolean) * &#x60;managed_by_policy&#x60;:  (boolean) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure * &#x60;managed&#x60;: If True, this instance is managed by the control plane. (boolean) * &#x60;ip_address&#x60;:  (string) * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field) * &#x60;reverse_peers&#x60;:  (field) * &#x60;listener_port&#x60;:  (integer) * &#x60;peers_from_control_nodes&#x60;:  (boolean) * &#x60;protocol&#x60;:  (field)      # Update an Instance:  Make a PUT or PATCH request to this resource to update this instance.  The following fields may be modified:    * &#x60;hostname&#x60;:  (string, required)             * &#x60;capacity_adjustment&#x60;:  (decimal, default&#x3D;&#x60;1&#x60;)           * &#x60;enabled&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;managed_by_policy&#x60;:  (boolean, default&#x3D;&#x60;True&#x60;) * &#x60;node_type&#x60;: Role that this node plays in the mesh. (choice)     - &#x60;control&#x60;: Control plane node     - &#x60;execution&#x60;: Execution plane node (default)     - &#x60;hybrid&#x60;: Controller and execution     - &#x60;hop&#x60;: Message-passing node, no execution capability * &#x60;node_state&#x60;: Indicates the current life cycle stage of this instance. (choice)     - &#x60;provisioning&#x60;: Provisioning     - &#x60;provision-fail&#x60;: Provisioning Failure     - &#x60;installed&#x60;: Installed (default)     - &#x60;ready&#x60;: Ready     - &#x60;unavailable&#x60;: Unavailable     - &#x60;deprovisioning&#x60;: De-provisioning     - &#x60;deprovision-fail&#x60;: De-provisioning Failure   * &#x60;peers&#x60;: Primary keys of receptor addresses to peer to. (field, default&#x3D;&#x60;None&#x60;)  * &#x60;listener_port&#x60;:  (integer, default&#x3D;&#x60;&#x60;) * &#x60;peers_from_control_nodes&#x60;:  (boolean, default&#x3D;&#x60;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Instance&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Instance> apiInstancesUpdateWithHttpInfo(String version, String id, Instance data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInstancesUpdate");
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

        ParameterizedTypeReference<Instance> localReturnType = new ParameterizedTypeReference<Instance>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
