package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiProjectUpdatesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ProjectUpdateCancel;
import com.boehringer.componentprovisioner.client.awx.v2.model.ProjectUpdateDetail;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ProjectUpdatesApi")
public class ProjectUpdatesApi extends BaseApi {

    public ProjectUpdatesApi() {
        super(new ApiClient());
    }

    @Autowired
    public ProjectUpdatesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to determine if the project update can be
     * canceled.  The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this update can be canceled (boolean,   read-only)  Make a POST request to this resource to cancel a pending or running project update.  The response status code will be 202 if successful, or 405 if the update cannot be canceled.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ProjectUpdateCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectUpdateCancel apiProjectUpdatesCancelCreate(String version, String id, ProjectUpdateCancel data) throws RestClientException {
        return apiProjectUpdatesCancelCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the project update can be
     * canceled.  The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this update can be canceled (boolean,   read-only)  Make a POST request to this resource to cancel a pending or running project update.  The response status code will be 202 if successful, or 405 if the update cannot be canceled.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ProjectUpdateCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectUpdateCancel> apiProjectUpdatesCancelCreateWithHttpInfo(String version, String id, ProjectUpdateCancel data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesCancelCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesCancelCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectUpdatesCancelCreate");
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

        ParameterizedTypeReference<ProjectUpdateCancel> localReturnType = new ParameterizedTypeReference<ProjectUpdateCancel>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/cancel/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the project update can be
     * canceled.  The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this update can be canceled (boolean,   read-only)  Make a POST request to this resource to cancel a pending or running project update.  The response status code will be 202 if successful, or 405 if the update cannot be canceled.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ProjectUpdateCancel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectUpdateCancel apiProjectUpdatesCancelRead(String version, String id) throws RestClientException {
        return apiProjectUpdatesCancelReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the project update can be
     * canceled.  The response will include the following field:  * &#x60;can_cancel&#x60;: Indicates whether this update can be canceled (boolean,   read-only)  Make a POST request to this resource to cancel a pending or running project update.  The response status code will be 202 if successful, or 405 if the update cannot be canceled.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ProjectUpdateCancel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectUpdateCancel> apiProjectUpdatesCancelReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesCancelRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesCancelRead");
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

        ParameterizedTypeReference<ProjectUpdateCancel> localReturnType = new ParameterizedTypeReference<ProjectUpdateCancel>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/cancel/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field)      # Delete a Project Update:  Make a DELETE request to this resource to delete this project update.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiProjectUpdatesDelete(String version, String id) throws RestClientException {
        apiProjectUpdatesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single project update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field)      # Delete a Project Update:  Make a DELETE request to this resource to delete this project update.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiProjectUpdatesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesDelete");
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
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * project updates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of project updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project update records.    ## Results  Each project update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string)    ## Sorting  To specify that project updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiProjectUpdatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiProjectUpdatesList200Response apiProjectUpdatesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectUpdatesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * project updates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of project updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project update records.    ## Results  Each project update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string)    ## Sorting  To specify that project updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiProjectUpdatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiProjectUpdatesList200Response> apiProjectUpdatesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesList");
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

        ParameterizedTypeReference<ApiProjectUpdatesList200Response> localReturnType = new ParameterizedTypeReference<ApiProjectUpdatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field)      # Delete a Project Update:  Make a DELETE request to this resource to delete this project update.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ProjectUpdateDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectUpdateDetail apiProjectUpdatesRead(String version, String id) throws RestClientException {
        return apiProjectUpdatesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single project update
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_args&#x60;:  (string) * &#x60;job_cwd&#x60;:  (string) * &#x60;job_env&#x60;:  (json) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;result_traceback&#x60;:  (string) * &#x60;event_processing_finished&#x60;: Indicates whether all of the events generated by this unified job have been saved to the database. (boolean) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string) * &#x60;host_status_counts&#x60;: Playbook stats from the Ansible playbook_on_stats event. (json) * &#x60;playbook_counts&#x60;: A count of all plays and tasks for the job run. (field)      # Delete a Project Update:  Make a DELETE request to this resource to delete this project update.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ProjectUpdateDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectUpdateDetail> apiProjectUpdatesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesRead");
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

        ParameterizedTypeReference<ProjectUpdateDetail> localReturnType = new ParameterizedTypeReference<ProjectUpdateDetail>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * project updates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of project updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project update records.    ## Results  Each project update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string)    ## Sorting  To specify that project updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiProjectUpdatesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiProjectUpdatesList200Response apiProjectsProjectUpdatesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsProjectUpdatesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * project updates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of project updates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project update records.    ## Results  Each project update data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project update. (integer) * &#x60;type&#x60;: Data type for this project update. (choice) * &#x60;url&#x60;: URL for this project update. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project update was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project update was last modified. (datetime) * &#x60;name&#x60;: Name of this project update. (string) * &#x60;description&#x60;: Optional description of this project update. (string) * &#x60;unified_job_template&#x60;:  (id) * &#x60;launch_type&#x60;:  (choice)     - &#x60;manual&#x60;: Manual     - &#x60;relaunch&#x60;: Relaunch     - &#x60;callback&#x60;: Callback     - &#x60;scheduled&#x60;: Scheduled     - &#x60;dependency&#x60;: Dependency     - &#x60;workflow&#x60;: Workflow     - &#x60;webhook&#x60;: Webhook     - &#x60;sync&#x60;: Sync     - &#x60;scm&#x60;: SCM Update * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled * &#x60;execution_environment&#x60;: The container image to be used for execution. (id) * &#x60;failed&#x60;:  (boolean) * &#x60;started&#x60;: The date and time the job was queued for starting. (datetime) * &#x60;finished&#x60;: The date and time the job finished execution. (datetime) * &#x60;canceled_on&#x60;: The date and time when the cancel request was sent. (datetime) * &#x60;elapsed&#x60;: Elapsed time in seconds that the job ran. (decimal) * &#x60;job_explanation&#x60;: A status field to indicate the state of the job if it wasn&amp;#x27;t able to run and capture stdout (string) * &#x60;execution_node&#x60;: The node the job executed on. (string) * &#x60;launched_by&#x60;:  (field) * &#x60;work_unit_id&#x60;: The Receptor work unit ID associated with this job. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The SCM Revision discovered by this update for the given project and branch. (string) * &#x60;project&#x60;:  (id) * &#x60;job_type&#x60;:  (choice)     - &#x60;run&#x60;: Run     - &#x60;check&#x60;: Check * &#x60;job_tags&#x60;: Parts of the project update playbook that will be run. (string)    ## Sorting  To specify that project updates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiProjectUpdatesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiProjectUpdatesList200Response> apiProjectsProjectUpdatesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsProjectUpdatesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsProjectUpdatesList");
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

        ParameterizedTypeReference<ApiProjectUpdatesList200Response> localReturnType = new ParameterizedTypeReference<ApiProjectUpdatesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/project_updates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
