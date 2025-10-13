package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInventorySourcesSchedulesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Schedule;
import com.boehringer.componentprovisioner.client.awx.v2.model.SchedulePreview;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.SchedulesApi")
public class SchedulesApi extends BaseApi {

    public SchedulesApi() {
        super(new ApiClient());
    }

    @Autowired
    public SchedulesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiInventorySourcesSchedulesCreate(String version, String id, Schedule data) throws RestClientException {
        return apiInventorySourcesSchedulesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiInventorySourcesSchedulesCreateWithHttpInfo(String version, String id, Schedule data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesSchedulesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesSchedulesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventorySourcesSchedulesCreate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/schedules/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesSchedulesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesSchedulesList200Response apiInventorySourcesSchedulesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesSchedulesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesSchedulesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesSchedulesList200Response> apiInventorySourcesSchedulesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesSchedulesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesSchedulesList");
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

        ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/schedules/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiJobTemplatesSchedulesCreate(String version, String id, Object data) throws RestClientException {
        return apiJobTemplatesSchedulesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiJobTemplatesSchedulesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesSchedulesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesSchedulesCreate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/schedules/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesSchedulesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesSchedulesList200Response apiJobTemplatesSchedulesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesSchedulesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesSchedulesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesSchedulesList200Response> apiJobTemplatesSchedulesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesSchedulesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesSchedulesList");
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

        ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/schedules/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiProjectsSchedulesCreate(String version, String id, Schedule data) throws RestClientException {
        return apiProjectsSchedulesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiProjectsSchedulesCreateWithHttpInfo(String version, String id, Schedule data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsSchedulesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsSchedulesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectsSchedulesCreate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/schedules/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesSchedulesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesSchedulesList200Response apiProjectsSchedulesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsSchedulesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesSchedulesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesSchedulesList200Response> apiProjectsSchedulesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsSchedulesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsSchedulesList");
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

        ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/schedules/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * schedules.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiSchedulesCreate(String version, Object data) throws RestClientException {
        return apiSchedulesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * schedules.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiSchedulesCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesCreate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/schedules/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiSchedulesDelete(String version, String id) throws RestClientException {
        apiSchedulesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiSchedulesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesDelete");
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
        return apiClient.invokeAPI("/api/v2/schedules/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * schedules.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesSchedulesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesSchedulesList200Response apiSchedulesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSchedulesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * schedules.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesSchedulesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesSchedulesList200Response> apiSchedulesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesList");
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

        ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/schedules/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiSchedulesPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiSchedulesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiSchedulesPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesPartialUpdate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for post on /api/{version}/schedules/preview/
     * 
     * <p><b>200</b>
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiSchedulesPreviewCreate(String version, Object data) throws RestClientException {
        apiSchedulesPreviewCreateWithHttpInfo(version, data);
    }

    /**
     * No Description for post on /api/{version}/schedules/preview/
     * 
     * <p><b>200</b>
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiSchedulesPreviewCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesPreviewCreate");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/schedules/preview/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiSchedulesRead(String version, String id) throws RestClientException {
        return apiSchedulesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiSchedulesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesRead");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiSchedulesUpdate(String version, String id, Schedule data) throws RestClientException {
        return apiSchedulesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single schedule
     * record containing the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)      # Update a Schedule:  Make a PUT or PATCH request to this resource to update this schedule.  The following fields may be modified:   * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string, required)        * &#x60;name&#x60;: Name of this schedule. (string, required) * &#x60;description&#x60;: Optional description of this schedule. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;extra_data&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_branch&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;skip_tags&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;limit&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;diff_mode&#x60;:  (boolean, default&#x3D;&#x60;None&#x60;) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: --------- (default)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id, default&#x3D;&#x60;&#x60;) * &#x60;forks&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;job_slice_count&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;timeout&#x60;:  (integer, default&#x3D;&#x60;None&#x60;) * &#x60;unified_job_template&#x60;:  (id, required) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean, default&#x3D;&#x60;True&#x60;)            For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Schedule:  Make a DELETE request to this resource to delete this schedule.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiSchedulesUpdateWithHttpInfo(String version, String id, Schedule data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSchedulesUpdate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiSystemJobTemplatesSchedulesCreate(String version, String id, Schedule data) throws RestClientException {
        return apiSystemJobTemplatesSchedulesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiSystemJobTemplatesSchedulesCreateWithHttpInfo(String version, String id, Schedule data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesSchedulesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesSchedulesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSystemJobTemplatesSchedulesCreate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/schedules/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesSchedulesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesSchedulesList200Response apiSystemJobTemplatesSchedulesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobTemplatesSchedulesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesSchedulesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesSchedulesList200Response> apiSystemJobTemplatesSchedulesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesSchedulesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesSchedulesList");
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

        ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/schedules/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Schedule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Schedule apiWorkflowJobTemplatesSchedulesCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplatesSchedulesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Schedule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Schedule> apiWorkflowJobTemplatesSchedulesCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesSchedulesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesSchedulesCreate");
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

        ParameterizedTypeReference<Schedule> localReturnType = new ParameterizedTypeReference<Schedule>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/schedules/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesSchedulesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesSchedulesList200Response apiWorkflowJobTemplatesSchedulesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesSchedulesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * schedules associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of schedules found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more schedule records.    ## Results  Each schedule data structure includes the following fields:  * &#x60;rrule&#x60;: A value representing the schedules iCal recurrence rule. (string) * &#x60;id&#x60;: Database ID for this schedule. (integer) * &#x60;type&#x60;: Data type for this schedule. (choice) * &#x60;url&#x60;: URL for this schedule. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this schedule was created. (datetime) * &#x60;modified&#x60;: Timestamp when this schedule was last modified. (datetime) * &#x60;name&#x60;: Name of this schedule. (string) * &#x60;description&#x60;: Optional description of this schedule. (string) * &#x60;extra_data&#x60;:  (json) * &#x60;inventory&#x60;: Inventory applied as a prompt, assuming job template prompts for inventory (id) * &#x60;scm_branch&#x60;:  (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;\&quot;\&quot;&#x60;: ---------     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;:  (string) * &#x60;skip_tags&#x60;:  (string) * &#x60;limit&#x60;:  (string) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;verbosity&#x60;:  (choice)     - &#x60;None&#x60;: ---------     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;forks&#x60;:  (integer) * &#x60;job_slice_count&#x60;:  (integer) * &#x60;timeout&#x60;:  (integer) * &#x60;unified_job_template&#x60;:  (id) * &#x60;enabled&#x60;: Enables processing of this schedule. (boolean) * &#x60;dtstart&#x60;: The first occurrence of the schedule occurs on or after this time. (datetime) * &#x60;dtend&#x60;: The last occurrence of the schedule occurs before this time, aftewards the schedule expires. (datetime) * &#x60;next_run&#x60;: The next time that the scheduled action will run. (datetime) * &#x60;timezone&#x60;: The timezone this schedule runs in. This field is extracted from the RRULE. If the timezone in the RRULE is a link to another timezone, the link will be reflected in this field. (field) * &#x60;until&#x60;: The date this schedule will end. This field is computed from the RRULE. If the schedule does not end an empty string will be returned (field)    ## Sorting  To specify that schedules are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesSchedulesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesSchedulesList200Response> apiWorkflowJobTemplatesSchedulesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesSchedulesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesSchedulesList");
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

        ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesSchedulesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/schedules/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
