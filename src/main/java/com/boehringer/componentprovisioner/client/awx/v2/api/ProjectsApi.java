package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsCopyList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiOrganizationsProjectsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Copy;
import com.boehringer.componentprovisioner.client.awx.v2.model.Project;
import com.boehringer.componentprovisioner.client.awx.v2.model.ProjectInventories;
import com.boehringer.componentprovisioner.client.awx.v2.model.ProjectPlaybooks;
import com.boehringer.componentprovisioner.client.awx.v2.model.ProjectUpdateView;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ProjectsApi")
public class ProjectsApi extends BaseApi {

    public ProjectsApi() {
        super(new ApiClient());
    }

    @Autowired
    public ProjectsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Project
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Project apiOrganizationsProjectsCreate(String version, String id, Project data) throws RestClientException {
        return apiOrganizationsProjectsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Project&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Project> apiOrganizationsProjectsCreateWithHttpInfo(String version, String id, Project data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsProjectsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsProjectsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsProjectsCreate");
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

        ParameterizedTypeReference<Project> localReturnType = new ParameterizedTypeReference<Project>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/projects/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsProjectsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsProjectsList200Response apiOrganizationsProjectsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsProjectsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsProjectsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsProjectsList200Response> apiOrganizationsProjectsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsProjectsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsProjectsList");
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

        ParameterizedTypeReference<ApiOrganizationsProjectsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsProjectsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/projects/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for post on /api/{version}/projects/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Copy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Copy apiProjectsCopyCreate(String version, String id, Copy data) throws RestClientException {
        return apiProjectsCopyCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * No Description for post on /api/{version}/projects/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Copy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Copy> apiProjectsCopyCreateWithHttpInfo(String version, String id, Copy data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsCopyCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsCopyCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectsCopyCreate");
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
        return apiClient.invokeAPI("/api/v2/projects/{id}/copy/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/projects/{id}/copy/
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
    public ApiCredentialsCopyList200Response apiProjectsCopyList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsCopyListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * No Description for get on /api/{version}/projects/{id}/copy/
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
    public ResponseEntity<ApiCredentialsCopyList200Response> apiProjectsCopyListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsCopyList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsCopyList");
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
        return apiClient.invokeAPI("/api/v2/projects/{id}/copy/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * projects.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>415</b>
     * @param version  (required)
     * @param data  (optional)
     * @return Project
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Project apiProjectsCreate(String version, Object data) throws RestClientException {
        return apiProjectsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * projects.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>415</b>
     * @param version  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Project&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Project> apiProjectsCreateWithHttpInfo(String version, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsCreate");
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

        ParameterizedTypeReference<Project> localReturnType = new ParameterizedTypeReference<Project>() {};
        return apiClient.invokeAPI("/api/v2/projects/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiProjectsDelete(String version, String id) throws RestClientException {
        apiProjectsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiProjectsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsDelete");
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
        return apiClient.invokeAPI("/api/v2/projects/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;inventory_files&#x60;: Array of inventory files and directories available within this project, not comprehensive. (json)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ProjectInventories
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectInventories apiProjectsInventoriesRead(String version, String id) throws RestClientException {
        return apiProjectsInventoriesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;inventory_files&#x60;: Array of inventory files and directories available within this project, not comprehensive. (json)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ProjectInventories&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectInventories> apiProjectsInventoriesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsInventoriesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsInventoriesRead");
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

        ParameterizedTypeReference<ProjectInventories> localReturnType = new ParameterizedTypeReference<ProjectInventories>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/inventories/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * projects.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsProjectsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsProjectsList200Response apiProjectsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * projects.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsProjectsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsProjectsList200Response> apiProjectsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsList");
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

        ParameterizedTypeReference<ApiOrganizationsProjectsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsProjectsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return Project
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Project apiProjectsPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiProjectsPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;Project&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Project> apiProjectsPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsPartialUpdate");
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

        ParameterizedTypeReference<Project> localReturnType = new ParameterizedTypeReference<Project>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a list of playbooks available
     * for a project.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ProjectPlaybooks
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectPlaybooks apiProjectsPlaybooksRead(String version, String id) throws RestClientException {
        return apiProjectsPlaybooksReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a list of playbooks available
     * for a project.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ProjectPlaybooks&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectPlaybooks> apiProjectsPlaybooksReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsPlaybooksRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsPlaybooksRead");
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

        ParameterizedTypeReference<ProjectPlaybooks> localReturnType = new ParameterizedTypeReference<ProjectPlaybooks>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/playbooks/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Project
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Project apiProjectsRead(String version, String id) throws RestClientException {
        return apiProjectsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Project&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Project> apiProjectsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsRead");
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

        ParameterizedTypeReference<Project> localReturnType = new ParameterizedTypeReference<Project>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Project
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Project apiProjectsUpdate(String version, String id, Project data) throws RestClientException {
        return apiProjectsUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single project
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)      # Update a Project:  Make a PUT or PATCH request to this resource to update this project.  The following fields may be modified:          * &#x60;name&#x60;: Name of this project. (string, required) * &#x60;description&#x60;: Optional description of this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual (default)     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer, default&#x3D;&#x60;0&#x60;)      * &#x60;organization&#x60;: The organization used to determine access to this template. (id, default&#x3D;&#x60;&#x60;) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean, default&#x3D;&#x60;False&#x60;) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean, default&#x3D;&#x60;False&#x60;)  * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id, default&#x3D;&#x60;&#x60;) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id, default&#x3D;&#x60;&#x60;)         For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Project:  Make a DELETE request to this resource to delete this project.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Project&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Project> apiProjectsUpdateWithHttpInfo(String version, String id, Project data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectsUpdate");
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

        ParameterizedTypeReference<Project> localReturnType = new ParameterizedTypeReference<Project>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the project can be updated
     * from its SCM source.  The response will include the following field:  * &#x60;can_update&#x60;: Flag indicating if this project can be updated (boolean,   read-only)  Make a POST request to this resource to update the project.  If the project cannot be updated, a 405 status code will be returned.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ProjectUpdateView
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectUpdateView apiProjectsUpdateCreate(String version, String id, ProjectUpdateView data) throws RestClientException {
        return apiProjectsUpdateCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the project can be updated
     * from its SCM source.  The response will include the following field:  * &#x60;can_update&#x60;: Flag indicating if this project can be updated (boolean,   read-only)  Make a POST request to this resource to update the project.  If the project cannot be updated, a 405 status code will be returned.
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;ProjectUpdateView&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectUpdateView> apiProjectsUpdateCreateWithHttpInfo(String version, String id, ProjectUpdateView data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsUpdateCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsUpdateCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectsUpdateCreate");
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

        ParameterizedTypeReference<ProjectUpdateView> localReturnType = new ParameterizedTypeReference<ProjectUpdateView>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/update/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to determine if the project can be updated
     * from its SCM source.  The response will include the following field:  * &#x60;can_update&#x60;: Flag indicating if this project can be updated (boolean,   read-only)  Make a POST request to this resource to update the project.  If the project cannot be updated, a 405 status code will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ProjectUpdateView
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProjectUpdateView apiProjectsUpdateRead(String version, String id) throws RestClientException {
        return apiProjectsUpdateReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make a GET request to this resource to determine if the project can be updated
     * from its SCM source.  The response will include the following field:  * &#x60;can_update&#x60;: Flag indicating if this project can be updated (boolean,   read-only)  Make a POST request to this resource to update the project.  If the project cannot be updated, a 405 status code will be returned.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ProjectUpdateView&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProjectUpdateView> apiProjectsUpdateReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsUpdateRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsUpdateRead");
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

        ParameterizedTypeReference<ProjectUpdateView> localReturnType = new ParameterizedTypeReference<ProjectUpdateView>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/update/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsProjectsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsProjectsList200Response apiTeamsProjectsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiTeamsProjectsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsProjectsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsProjectsList200Response> apiTeamsProjectsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsProjectsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsProjectsList");
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

        ParameterizedTypeReference<ApiOrganizationsProjectsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsProjectsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/projects/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiOrganizationsProjectsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiOrganizationsProjectsList200Response apiUsersProjectsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersProjectsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * projects associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of projects found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more project records.    ## Results  Each project data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this project. (integer) * &#x60;type&#x60;: Data type for this project. (choice) * &#x60;url&#x60;: URL for this project. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this project was created. (datetime) * &#x60;modified&#x60;: Timestamp when this project was last modified. (datetime) * &#x60;name&#x60;: Name of this project. (string) * &#x60;description&#x60;: Optional description of this project. (string) * &#x60;local_path&#x60;: Local path (relative to PROJECTS_ROOT) containing playbooks and related files for this project. (string) * &#x60;scm_type&#x60;: Specifies the source control system used to store the project. (choice)     - &#x60;\&quot;\&quot;&#x60;: Manual     - &#x60;git&#x60;: Git     - &#x60;svn&#x60;: Subversion     - &#x60;insights&#x60;: Red Hat Insights     - &#x60;archive&#x60;: Remote Archive * &#x60;scm_url&#x60;: The location where the project is stored. (string) * &#x60;scm_branch&#x60;: Specific branch, tag or commit to checkout. (string) * &#x60;scm_refspec&#x60;: For git projects, an additional refspec to fetch. (string) * &#x60;scm_clean&#x60;: Discard any local changes before syncing the project. (boolean) * &#x60;scm_track_submodules&#x60;: Track submodules latest commits on defined branch. (boolean) * &#x60;scm_delete_on_update&#x60;: Delete the project before syncing. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;timeout&#x60;: The amount of time (in seconds) to run before the task is canceled. (integer) * &#x60;scm_revision&#x60;: The last revision fetched by a project update (string) * &#x60;last_job_run&#x60;:  (datetime) * &#x60;last_job_failed&#x60;:  (boolean) * &#x60;next_job_run&#x60;:  (datetime) * &#x60;status&#x60;:  (choice)     - &#x60;new&#x60;: New     - &#x60;pending&#x60;: Pending     - &#x60;waiting&#x60;: Waiting     - &#x60;running&#x60;: Running     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed     - &#x60;error&#x60;: Error     - &#x60;canceled&#x60;: Canceled     - &#x60;never updated&#x60;: Never Updated     - &#x60;ok&#x60;: OK     - &#x60;missing&#x60;: Missing * &#x60;organization&#x60;: The organization used to determine access to this template. (id) * &#x60;scm_update_on_launch&#x60;: Update the project when a job is launched that uses the project. (boolean) * &#x60;scm_update_cache_timeout&#x60;: The number of seconds after the last project update ran that a new project update will be launched as a job dependency. (integer) * &#x60;allow_override&#x60;: Allow changing the SCM branch or revision in a job template that uses this project. (boolean) * &#x60;custom_virtualenv&#x60;: Local absolute file path containing a custom Python virtualenv to use (string) * &#x60;default_environment&#x60;: The default execution environment for jobs run using this project. (id) * &#x60;signature_validation_credential&#x60;: An optional credential used for validating files in the project against unexpected changes. (id) * &#x60;last_update_failed&#x60;:  (boolean) * &#x60;last_updated&#x60;:  (datetime)    ## Sorting  To specify that projects are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiOrganizationsProjectsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiOrganizationsProjectsList200Response> apiUsersProjectsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersProjectsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersProjectsList");
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

        ParameterizedTypeReference<ApiOrganizationsProjectsList200Response> localReturnType = new ParameterizedTypeReference<ApiOrganizationsProjectsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/projects/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
