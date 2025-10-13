package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.AdHocCommandEvent;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiAdHocCommandsEventsList200Response;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.AdHocCommandEventsApi")
public class AdHocCommandEventsApi extends BaseApi {

    public AdHocCommandEventsApi() {
        super(new ApiClient());
    }

    @Autowired
    public AdHocCommandEventsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command event
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command event. (integer) * &#x60;type&#x60;: Data type for this ad hoc command event. (choice) * &#x60;url&#x60;: URL for this ad hoc command event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command event was last modified. (datetime) * &#x60;ad_hoc_command&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return AdHocCommandEvent
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandEvent apiAdHocCommandEventsRead(String version, String id) throws RestClientException {
        return apiAdHocCommandEventsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command event
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command event. (integer) * &#x60;type&#x60;: Data type for this ad hoc command event. (choice) * &#x60;url&#x60;: URL for this ad hoc command event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command event was last modified. (datetime) * &#x60;ad_hoc_command&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;AdHocCommandEvent&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandEvent> apiAdHocCommandEventsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandEventsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandEventsRead");
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

        ParameterizedTypeReference<AdHocCommandEvent> localReturnType = new ParameterizedTypeReference<AdHocCommandEvent>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_command_events/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc command events associated with the selected ad hoc command.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc command events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command event records.    ## Results  Each ad hoc command event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command event. (integer) * &#x60;type&#x60;: Data type for this ad hoc command event. (choice) * &#x60;url&#x60;: URL for this ad hoc command event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command event was last modified. (datetime) * &#x60;ad_hoc_command&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that ad hoc command events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsEventsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsEventsList200Response apiAdHocCommandsEventsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiAdHocCommandsEventsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc command events associated with the selected ad hoc command.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc command events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command event records.    ## Results  Each ad hoc command event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command event. (integer) * &#x60;type&#x60;: Data type for this ad hoc command event. (choice) * &#x60;url&#x60;: URL for this ad hoc command event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command event was last modified. (datetime) * &#x60;ad_hoc_command&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that ad hoc command events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsEventsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsEventsList200Response> apiAdHocCommandsEventsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsEventsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsEventsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsEventsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsEventsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/events/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc command events associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc command events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command event records.    ## Results  Each ad hoc command event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command event. (integer) * &#x60;type&#x60;: Data type for this ad hoc command event. (choice) * &#x60;url&#x60;: URL for this ad hoc command event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command event was last modified. (datetime) * &#x60;ad_hoc_command&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that ad hoc command events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsEventsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsEventsList200Response apiHostsAdHocCommandEventsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsAdHocCommandEventsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc command events associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc command events found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command event records.    ## Results  Each ad hoc command event data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command event. (integer) * &#x60;type&#x60;: Data type for this ad hoc command event. (choice) * &#x60;url&#x60;: URL for this ad hoc command event. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command event was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command event was last modified. (datetime) * &#x60;ad_hoc_command&#x60;:  (id) * &#x60;event&#x60;:  (choice)     - &#x60;runner_on_failed&#x60;: Host Failed     - &#x60;runner_on_ok&#x60;: Host OK     - &#x60;runner_on_unreachable&#x60;: Host Unreachable     - &#x60;runner_on_skipped&#x60;: Host Skipped     - &#x60;debug&#x60;: Debug     - &#x60;verbose&#x60;: Verbose     - &#x60;deprecated&#x60;: Deprecated     - &#x60;warning&#x60;: Warning     - &#x60;system_warning&#x60;: System Warning     - &#x60;error&#x60;: Error * &#x60;counter&#x60;:  (integer) * &#x60;event_display&#x60;:  (string) * &#x60;event_data&#x60;:  (json) * &#x60;failed&#x60;:  (boolean) * &#x60;changed&#x60;:  (boolean) * &#x60;uuid&#x60;:  (string) * &#x60;host&#x60;:  (id) * &#x60;host_name&#x60;:  (string) * &#x60;stdout&#x60;:  (string) * &#x60;start_line&#x60;:  (integer) * &#x60;end_line&#x60;:  (integer) * &#x60;verbosity&#x60;:  (integer)    ## Sorting  To specify that ad hoc command events are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsEventsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsEventsList200Response> apiHostsAdHocCommandEventsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsAdHocCommandEventsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsAdHocCommandEventsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsEventsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsEventsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/ad_hoc_command_events/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
