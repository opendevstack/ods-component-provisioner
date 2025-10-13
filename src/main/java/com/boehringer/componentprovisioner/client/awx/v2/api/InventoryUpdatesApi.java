package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInventorySourcesInventoryUpdatesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.InventoryUpdateCancel;
import com.boehringer.componentprovisioner.client.awx.v2.model.InventoryUpdateDetail;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.InventoryUpdatesApi")
public class InventoryUpdatesApi extends BaseApi {

    public InventoryUpdatesApi() {
        super(new ApiClient());
    }

    @Autowired
    public InventoryUpdatesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventory updates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventory updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory update records.    ## Results  Each inventory update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string)    ## Sorting  To specify that inventory updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesInventoryUpdatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesInventoryUpdatesList200Response apiInventorySourcesInventoryUpdatesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesInventoryUpdatesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventory updates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventory updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory update records.    ## Results  Each inventory update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string)    ## Sorting  To specify that inventory updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesInventoryUpdatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesInventoryUpdatesList200Response> apiInventorySourcesInventoryUpdatesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesInventoryUpdatesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesInventoryUpdatesList");
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

        ParameterizedTypeReference<ApiInventorySourcesInventoryUpdatesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesInventoryUpdatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/inventory_updates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return InventoryUpdateCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InventoryUpdateCancel apiInventoryUpdatesCancelCreate(String version, String id, InventoryUpdateCancel data) throws RestClientException {
        return apiInventoryUpdatesCancelCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;InventoryUpdateCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InventoryUpdateCancel> apiInventoryUpdatesCancelCreateWithHttpInfo(String version, String id, InventoryUpdateCancel data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesCancelCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesCancelCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventoryUpdatesCancelCreate");
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

        ParameterizedTypeReference<InventoryUpdateCancel> localReturnType = new ParameterizedTypeReference<InventoryUpdateCancel>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/cancel/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return InventoryUpdateCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InventoryUpdateCancel apiInventoryUpdatesCancelRead(String version, String id) throws RestClientException {
        return apiInventoryUpdatesCancelReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;InventoryUpdateCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InventoryUpdateCancel> apiInventoryUpdatesCancelReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesCancelRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesCancelRead");
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

        ParameterizedTypeReference<InventoryUpdateCancel> localReturnType = new ParameterizedTypeReference<InventoryUpdateCancel>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/cancel/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string) * &#x60;source_project&#x60;: The project used for this job. (field)      # Delete an Inventory Update:  Make a DELETE request to this resource to delete this inventory update.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiInventoryUpdatesDelete(String version, String id) throws RestClientException {
        apiInventoryUpdatesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string) * &#x60;source_project&#x60;: The project used for this job. (field)      # Delete an Inventory Update:  Make a DELETE request to this resource to delete this inventory update.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiInventoryUpdatesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesDelete");
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
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * inventory updates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventory updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory update records.    ## Results  Each inventory update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string)    ## Sorting  To specify that inventory updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesInventoryUpdatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesInventoryUpdatesList200Response apiInventoryUpdatesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoryUpdatesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * inventory updates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventory updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory update records.    ## Results  Each inventory update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string)    ## Sorting  To specify that inventory updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesInventoryUpdatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesInventoryUpdatesList200Response> apiInventoryUpdatesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesList");
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

        ParameterizedTypeReference<ApiInventorySourcesInventoryUpdatesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesInventoryUpdatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string) * &#x60;source_project&#x60;: The project used for this job. (field)      # Delete an Inventory Update:  Make a DELETE request to this resource to delete this inventory update.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return InventoryUpdateDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InventoryUpdateDetail apiInventoryUpdatesRead(String version, String id) throws RestClientException {
        return apiInventoryUpdatesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single inventory update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string) * &#x60;source_project&#x60;: The project used for this job. (field)      # Delete an Inventory Update:  Make a DELETE request to this resource to delete this inventory update.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;InventoryUpdateDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InventoryUpdateDetail> apiInventoryUpdatesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesRead");
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

        ParameterizedTypeReference<InventoryUpdateDetail> localReturnType = new ParameterizedTypeReference<InventoryUpdateDetail>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * inventory updates associated with the selected project update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventory updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory update records.    ## Results  Each inventory update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string)    ## Sorting  To specify that inventory updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesInventoryUpdatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesInventoryUpdatesList200Response apiProjectUpdatesScmInventoryUpdatesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectUpdatesScmInventoryUpdatesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * inventory updates associated with the selected project update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of inventory updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more inventory update records.    ## Results  Each inventory update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this inventory update. (integer) * &#x60;type&#x60;: Data type for this inventory update. (choice) * &#x60;url&#x60;: URL for this inventory update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this inventory update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this inventory update was last modified. (datetime) * &#x60;name&#x60;: Name of this inventory update. (string) * &#x60;description&#x60;: Optional description of this inventory update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;source&#x60;:  (choice)     - &#x60;azure_rm&#x60;: Microsoft Azure Resource Manager     - &#x60;controller&#x60;: Red Hat Ansible Automation Platform     - &#x60;ec2&#x60;: Amazon EC2     - &#x60;gce&#x60;: Google Compute Engine     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;openshift_virtualization&#x60;: OpenShift Virtualization     - &#x60;openstack&#x60;: OpenStack     - &#x60;rhv&#x60;: Red Hat Virtualization     - &#x60;satellite6&#x60;: Red Hat Satellite 6     - &#x60;terraform&#x60;: Terraform State     - &#x60;vmware&#x60;: VMware vCenter     - &#x60;scm&#x60;: Sourced from a Project     - &#x60;constructed&#x60;: Template additional groups and hostvars at runtime * &#x60;source_path&#x60;:  (string) * &#x60;source_vars&#x60;: Inventory source variables in YAML or JSON format. (string) * &#x60;scm_branch&#x60;: Inventory source SCM branch. Project default used if blank. Only allowed if project allow_override field is set to true. (string) * &#x60;credential&#x60;: Cloud credential to use for inventory updates. (integer) * &#x60;enabled_var&#x60;: Retrieve the enabled state from the given dict of host variables. The enabled variable may be specified as &amp;quot;foo.bar&amp;quot;, in which case the lookup will traverse into nested dicts, equivalent to: from_dict.get(&amp;quot;foo&amp;quot;, {}).get(&amp;quot;bar&amp;quot;, default) (string) * &#x60;enabled_value&#x60;: Only used when enabled_var is set. Value when the host is considered enabled. For example if enabled_var&#x3D;&amp;quot;status.power_state&amp;quot;and enabled_value&#x3D;&amp;quot;powered_on&amp;quot; with host variables:{   &amp;quot;status&amp;quot;: {     &amp;quot;power_state&amp;quot;: &amp;quot;powered_on&amp;quot;,     &amp;quot;created&amp;quot;: &amp;quot;2018-02-01T08:00:00.000000Z:00&amp;quot;,     &amp;quot;healthy&amp;quot;: true    },    &amp;quot;name&amp;quot;: &amp;quot;foobar&amp;quot;,    &amp;quot;ip_address&amp;quot;: &amp;quot;192.168.2.1&amp;quot;}The host would be marked enabled. If power_state where any value other than powered_on then the host would be disabled when imported. If the key is not found then the host will be enabled (string) * &#x60;host_filter&#x60;: This field is deprecated and will be removed in a future release. Regex where only matching hosts will be imported. (string) * &#x60;overwrite&#x60;: Overwrite local groups and hosts from remote inventory source. (boolean) * &#x60;overwrite_vars&#x60;: Overwrite local variables from remote inventory source. (boolean) * &#x60;custom_virtualenv&#x60;:  (string) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (WARNING)     - &#x60;1&#x60;: 1 (INFO)     - &#x60;2&#x60;: 2 (DEBUG) * &#x60;limit&#x60;: Enter host, group or pattern match (string) * &#x60;inventory&#x60;:  (id) * &#x60;inventory_source&#x60;:  (id) * &#x60;license_error&#x60;:  (boolean) * &#x60;org_host_limit_error&#x60;:  (boolean) * &#x60;source_project_update&#x60;: Inventory files from this Project Update were used for the inventory update. (id) * &#x60;instance_group&#x60;: The Instance group the job was run under (id) * &#x60;scm_revision&#x60;: The SCM Revision from the Project used for this inventory update.  Only applicable to inventories source from scm (string)    ## Sorting  To specify that inventory updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesInventoryUpdatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesInventoryUpdatesList200Response> apiProjectUpdatesScmInventoryUpdatesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesScmInventoryUpdatesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesScmInventoryUpdatesList");
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

        ParameterizedTypeReference<ApiInventorySourcesInventoryUpdatesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesInventoryUpdatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/scm_inventory_updates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
