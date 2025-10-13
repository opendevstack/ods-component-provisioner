package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiGroupsJobEventsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.JobEvent;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.JobEventsApi")
public class JobEventsApi extends BaseApi {

    public JobEventsApi() {
        super(new ApiClient());
    }

    @Autowired
    public JobEventsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobEventsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobEventsList200Response apiGroupsJobEventsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiGroupsJobEventsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobEventsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobEventsList200Response> apiGroupsJobEventsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsJobEventsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsJobEventsList");
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

        ParameterizedTypeReference<ApiGroupsJobEventsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobEventsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/job_events/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobEventsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobEventsList200Response apiHostsJobEventsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsJobEventsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobEventsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobEventsList200Response> apiHostsJobEventsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsJobEventsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsJobEventsList");
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

        ParameterizedTypeReference<ApiGroupsJobEventsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobEventsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/job_events/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected job event.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobEventsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobEventsList200Response apiJobEventsChildrenList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobEventsChildrenListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected job event.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobEventsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobEventsList200Response> apiJobEventsChildrenListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobEventsChildrenList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobEventsChildrenList");
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

        ParameterizedTypeReference<ApiGroupsJobEventsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobEventsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_events/{id}/children/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single job event
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return JobEvent
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobEvent apiJobEventsRead(String version, String id) throws RestClientException {
        return apiJobEventsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single job event
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;JobEvent&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobEvent> apiJobEventsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobEventsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobEventsRead");
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

        ParameterizedTypeReference<JobEvent> localReturnType = new ParameterizedTypeReference<JobEvent>() {};
        return apiClient.invokeAPI("/api/v2/job_events/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiGroupsJobEventsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiGroupsJobEventsList200Response apiJobsJobEventsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobsJobEventsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * job events associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of job events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more job event records.    ## Results  Each job event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this job event. (integer) * &#x60;type&#x60;: Data type for this job event. (choice) * &#x60;url&#x60;: URL for this job event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this job event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this job event was last modified. (datetime) * &#x60;job&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_start&#x60;: Host Started     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_error&#x60;: Host Failure     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_no_hosts&#x60;: No Hosts Remaining     - &#x60;runner_on_async_poll&#x60;: Host Polling     - &#x60;runner_on_async_ok&#x60;: Host Async OK     - &#x60;runner_on_async_failed&#x60;: Host Async Failure     - &#x60;runner_item_on_ok&#x60;: Item OK     - &#x60;runner_item_on_failed&#x60;: Item Failed     - &#x60;runner_item_on_skipped&#x60;: Item Skipped     - &#x60;runner_retry&#x60;: Host Retry     - &#x60;runner_on_file_diff&#x60;: File Difference     - &#x60;playbook_on_start&#x60;: Playbook Started     - &#x60;playbook_on_notify&#x60;: Running Handlers     - &#x60;playbook_on_include&#x60;: Including File     - &#x60;playbook_on_no_hosts_matched&#x60;: No Hosts Matched     - &#x60;playbook_on_no_hosts_remaining&#x60;: No Hosts Remaining     - &#x60;playbook_on_task_start&#x60;: Task Started     - &#x60;playbook_on_vars_prompt&#x60;: Variables Prompted     - &#x60;playbook_on_setup&#x60;: Gathering Facts     - &#x60;playbook_on_import_for_host&#x60;: internal: on Import for Host     - &#x60;playbook_on_not_import_for_host&#x60;: internal: on Not Import for Host     - &#x60;playbook_on_play_start&#x60;: Play Started     - &#x60;playbook_on_stats&#x60;: Playbook Complete     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;event_level&#x60;:  (integer) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;parent_uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;playbook&#x60;:  (string) * &#x60;play&#x60;:  (string) * &#x60;task&#x60;:  (string) * &#x60;role&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that job events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiGroupsJobEventsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiGroupsJobEventsList200Response> apiJobsJobEventsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsJobEventsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsJobEventsList");
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

        ParameterizedTypeReference<ApiGroupsJobEventsList200Response> localReturnType = new ParameterizedTypeReference<ApiGroupsJobEventsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/job_events/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
