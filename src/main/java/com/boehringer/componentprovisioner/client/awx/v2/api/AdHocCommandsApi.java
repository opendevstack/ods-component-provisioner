package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.AdHocCommandCancel;
import com.boehringer.componentprovisioner.client.awx.v2.model.AdHocCommandDetail;
import com.boehringer.componentprovisioner.client.awx.v2.model.AdHocCommandList;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiAdHocCommandsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiAdHocCommandsRelaunchList200Response;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.AdHocCommandsApi")
public class AdHocCommandsApi extends BaseApi {

    public AdHocCommandsApi() {
        super(new ApiClient());
    }

    @Autowired
    public AdHocCommandsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return AdHocCommandCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandCancel apiAdHocCommandsCancelCreate(String version, String id, AdHocCommandCancel data) throws RestClientException {
        return apiAdHocCommandsCancelCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;AdHocCommandCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandCancel> apiAdHocCommandsCancelCreateWithHttpInfo(String version, String id, AdHocCommandCancel data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsCancelCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsCancelCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiAdHocCommandsCancelCreate");
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

        ParameterizedTypeReference<AdHocCommandCancel> localReturnType = new ParameterizedTypeReference<AdHocCommandCancel>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/cancel/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return AdHocCommandCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandCancel apiAdHocCommandsCancelRead(String version, String id) throws RestClientException {
        return apiAdHocCommandsCancelReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;can_cancel&#x60;:  (boolean)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;AdHocCommandCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandCancel> apiAdHocCommandsCancelReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsCancelRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsCancelRead");
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

        ParameterizedTypeReference<AdHocCommandCancel> localReturnType = new ParameterizedTypeReference<AdHocCommandCancel>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/cancel/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * ad hoc commands.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return AdHocCommandList
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandList apiAdHocCommandsCreate(String version, Object data) throws RestClientException {
        return apiAdHocCommandsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * ad hoc commands.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * <p><b>403</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;AdHocCommandList&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandList> apiAdHocCommandsCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsCreate");
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

        ParameterizedTypeReference<AdHocCommandList> localReturnType = new ParameterizedTypeReference<AdHocCommandList>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json)      # Delete an Ad Hoc Command:  Make a DELETE request to this resource to delete this ad hoc command.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiAdHocCommandsDelete(String version, String id) throws RestClientException {
        apiAdHocCommandsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json)      # Delete an Ad Hoc Command:  Make a DELETE request to this resource to delete this ad hoc command.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiAdHocCommandsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsDelete");
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
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * ad hoc commands.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsList200Response apiAdHocCommandsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiAdHocCommandsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * ad hoc commands.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsList200Response> apiAdHocCommandsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json)      # Delete an Ad Hoc Command:  Make a DELETE request to this resource to delete this ad hoc command.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return AdHocCommandDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandDetail apiAdHocCommandsRead(String version, String id) throws RestClientException {
        return apiAdHocCommandsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single ad hoc command
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json)      # Delete an Ad Hoc Command:  Make a DELETE request to this resource to delete this ad hoc command.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;AdHocCommandDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandDetail> apiAdHocCommandsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsRead");
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

        ParameterizedTypeReference<AdHocCommandDetail> localReturnType = new ParameterizedTypeReference<AdHocCommandDetail>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiAdHocCommandsRelaunchCreate(String version, String id, Object data) throws RestClientException {
        return apiAdHocCommandsRelaunchCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiAdHocCommandsRelaunchCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsRelaunchCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsRelaunchCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiAdHocCommandsRelaunchCreate");
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
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/relaunch/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsRelaunchList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsRelaunchList200Response apiAdHocCommandsRelaunchList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiAdHocCommandsRelaunchListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a POST request to this resource to launch a job. If any passwords or variables are required then they should be passed in via POST data.   In order to determine what values are required in order to launch a job based on this job template you may make a GET request to this endpoint.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsRelaunchList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsRelaunchList200Response> apiAdHocCommandsRelaunchListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsRelaunchList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsRelaunchList");
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

        ParameterizedTypeReference<ApiAdHocCommandsRelaunchList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsRelaunchList200Response>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/relaunch/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return AdHocCommandList
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandList apiGroupsAdHocCommandsCreate(String version, String id, AdHocCommandList data) throws RestClientException {
        return apiGroupsAdHocCommandsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;AdHocCommandList&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandList> apiGroupsAdHocCommandsCreateWithHttpInfo(String version, String id, AdHocCommandList data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsAdHocCommandsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsAdHocCommandsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiGroupsAdHocCommandsCreate");
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

        ParameterizedTypeReference<AdHocCommandList> localReturnType = new ParameterizedTypeReference<AdHocCommandList>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/ad_hoc_commands/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsList200Response apiGroupsAdHocCommandsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiGroupsAdHocCommandsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsList200Response> apiGroupsAdHocCommandsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsAdHocCommandsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsAdHocCommandsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/ad_hoc_commands/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return AdHocCommandList
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandList apiHostsAdHocCommandsCreate(String version, String id, AdHocCommandList data) throws RestClientException {
        return apiHostsAdHocCommandsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;AdHocCommandList&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandList> apiHostsAdHocCommandsCreateWithHttpInfo(String version, String id, AdHocCommandList data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsAdHocCommandsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsAdHocCommandsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiHostsAdHocCommandsCreate");
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

        ParameterizedTypeReference<AdHocCommandList> localReturnType = new ParameterizedTypeReference<AdHocCommandList>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/ad_hoc_commands/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsList200Response apiHostsAdHocCommandsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsAdHocCommandsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsList200Response> apiHostsAdHocCommandsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsAdHocCommandsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsAdHocCommandsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/ad_hoc_commands/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return AdHocCommandList
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdHocCommandList apiInventoriesAdHocCommandsCreate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesAdHocCommandsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;AdHocCommandList&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdHocCommandList> apiInventoriesAdHocCommandsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesAdHocCommandsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesAdHocCommandsCreate");
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

        ParameterizedTypeReference<AdHocCommandList> localReturnType = new ParameterizedTypeReference<AdHocCommandList>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/ad_hoc_commands/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsList200Response apiInventoriesAdHocCommandsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesAdHocCommandsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * ad hoc commands associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of ad hoc commands found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more ad hoc command records.    ## Results  Each ad hoc command data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this ad hoc command. (integer) * &#x60;type&#x60;: Data type for this ad hoc command. (choice) * &#x60;url&#x60;: URL for this ad hoc command. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this ad hoc command was created. (datetime) * &#x60;modified&#x60;: Timestamp when this ad hoc command was last modified. (datetime) * &#x60;name&#x60;: Name of this ad hoc command. (string) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;controller_node&#x60;: The instance that managed the execution environment. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;inventory&#x60;:  (id) * &#x60;limit&#x60;:  (string) * &#x60;credential&#x60;:  (id) * &#x60;module_name&#x60;:  (choice)     - &#x60;command&#x60;     - &#x60;shell&#x60;     - &#x60;yum&#x60;     - &#x60;apt&#x60;     - &#x60;apt_key&#x60;     - &#x60;apt_repository&#x60;     - &#x60;apt_rpm&#x60;     - &#x60;service&#x60;     - &#x60;group&#x60;     - &#x60;user&#x60;     - &#x60;mount&#x60;     - &#x60;ping&#x60;     - &#x60;selinux&#x60;     - &#x60;setup&#x60;     - &#x60;win_ping&#x60;     - &#x60;win_service&#x60;     - &#x60;win_updates&#x60;     - &#x60;win_group&#x60;     - &#x60;win_user&#x60; * &#x60;module_args&#x60;:  (string) * &#x60;forks&#x60;:  (integer) * &#x60;verbosity&#x60;:  (choice)     - &#x60;0&#x60;: 0 (Normal)     - &#x60;1&#x60;: 1 (Verbose)     - &#x60;2&#x60;: 2 (More Verbose)     - &#x60;3&#x60;: 3 (Debug)     - &#x60;4&#x60;: 4 (Connection Debug)     - &#x60;5&#x60;: 5 (WinRM Debug) * &#x60;extra_vars&#x60;:  (string) * &#x60;become_enabled&#x60;:  (boolean) * &#x60;diff_mode&#x60;:  (boolean)    ## Sorting  To specify that ad hoc commands are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsList200Response> apiInventoriesAdHocCommandsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesAdHocCommandsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesAdHocCommandsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/ad_hoc_commands/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
