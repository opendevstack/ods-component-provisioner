package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInstanceGroupsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.InstanceGroup;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.InstanceGroupsApi")
public class InstanceGroupsApi extends BaseApi {

    public InstanceGroupsApi() {
        super(new ApiClient());
    }

    @Autowired
    public InstanceGroupsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * instance groups.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiInstanceGroupsCreate(String version, InstanceGroup data) throws RestClientException {
        return apiInstanceGroupsCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * instance groups.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiInstanceGroupsCreateWithHttpInfo(String version, InstanceGroup data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * <p><b>409</b>
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiInstanceGroupsDelete(String version, String id) throws RestClientException {
        apiInstanceGroupsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>204</b> - 
     * <p><b>403</b>
     * <p><b>409</b>
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiInstanceGroupsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsDelete");
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
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * instance groups.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiInstanceGroupsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstanceGroupsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * instance groups.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiInstanceGroupsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiInstanceGroupsPartialUpdate(String version, String id, Object data) throws RestClientException {
        return apiInstanceGroupsPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>200</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiInstanceGroupsPartialUpdateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsPartialUpdate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiInstanceGroupsRead(String version, String id) throws RestClientException {
        return apiInstanceGroupsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiInstanceGroupsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsRead");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiInstanceGroupsUpdate(String version, String id, InstanceGroup data) throws RestClientException {
        return apiInstanceGroupsUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single instance group
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)      # Update an Instance Group:  Make a PUT or PATCH request to this resource to update this instance group.  The following fields may be modified:       * &#x60;name&#x60;: Name of this instance group. (string, required)       * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer, default&#x3D;&#x60;0&#x60;)   * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean, default&#x3D;&#x60;&#x60;) * &#x60;credential&#x60;:  (id, default&#x3D;&#x60;&#x60;) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer, default&#x3D;&#x60;0&#x60;) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json, default&#x3D;&#x60;&#x60;) * &#x60;pod_spec_override&#x60;:  (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;)        For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete an Instance Group:  Make a DELETE request to this resource to delete this instance group.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiInstanceGroupsUpdateWithHttpInfo(String version, String id, InstanceGroup data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstanceGroupsUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstanceGroupsUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInstanceGroupsUpdate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/instance_groups/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiInstancesInstanceGroupsCreate(String version, String id, Object data) throws RestClientException {
        return apiInstancesInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiInstancesInstanceGroupsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiInstancesInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInstancesInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected instance.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiInstancesInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInstancesInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInstancesInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/instances/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiInventoriesInstanceGroupsCreate(String version, String id, Object data) throws RestClientException {
        return apiInventoriesInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiInventoriesInstanceGroupsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiInventoriesInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiInventoriesInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiJobTemplatesInstanceGroupsCreate(String version, String id, Object data) throws RestClientException {
        return apiJobTemplatesInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiJobTemplatesInstanceGroupsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiJobTemplatesInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiJobTemplatesInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiOrganizationsInstanceGroupsCreate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiOrganizationsInstanceGroupsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiOrganizationsInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiOrganizationsInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiSchedulesInstanceGroupsCreate(String version, String id, InstanceGroup data) throws RestClientException {
        return apiSchedulesInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiSchedulesInstanceGroupsCreateWithHttpInfo(String version, String id, InstanceGroup data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSchedulesInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiSchedulesInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSchedulesInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected schedule.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiSchedulesInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSchedulesInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/schedules/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiWorkflowJobNodesInstanceGroupsCreate(String version, String id, InstanceGroup data) throws RestClientException {
        return apiWorkflowJobNodesInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiWorkflowJobNodesInstanceGroupsCreateWithHttpInfo(String version, String id, InstanceGroup data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobNodesInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiWorkflowJobNodesInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobNodesInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiWorkflowJobNodesInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobNodesInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobNodesInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_nodes/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return InstanceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InstanceGroup apiWorkflowJobTemplateNodesInstanceGroupsCreate(String version, String id, InstanceGroup data) throws RestClientException {
        return apiWorkflowJobTemplateNodesInstanceGroupsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;InstanceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InstanceGroup> apiWorkflowJobTemplateNodesInstanceGroupsCreateWithHttpInfo(String version, String id, InstanceGroup data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesInstanceGroupsCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplateNodesInstanceGroupsCreate");
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

        ParameterizedTypeReference<InstanceGroup> localReturnType = new ParameterizedTypeReference<InstanceGroup>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/instance_groups/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInstanceGroupsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInstanceGroupsList200Response apiWorkflowJobTemplateNodesInstanceGroupsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplateNodesInstanceGroupsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * instance groups associated with the selected workflow job template node.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of instance groups found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more instance group records.    ## Results  Each instance group data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this instance group. (integer) * &#x60;type&#x60;: Data type for this instance group. (choice) * &#x60;url&#x60;: URL for this instance group. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;name&#x60;: Name of this instance group. (string) * &#x60;created&#x60;: Timestamp when this instance group was created. (datetime) * &#x60;modified&#x60;: Timestamp when this instance group was last modified. (datetime) * &#x60;capacity&#x60;:  (field) * &#x60;consumed_capacity&#x60;:  (field) * &#x60;percent_capacity_remaining&#x60;:  (field) * &#x60;jobs_running&#x60;:  (field) * &#x60;max_concurrent_jobs&#x60;: Maximum number of concurrent jobs to run on a group. When set to zero, no maximum is enforced. (integer) * &#x60;max_forks&#x60;: Maximum number of forks to execute concurrently on a group. When set to zero, no maximum is enforced. (integer) * &#x60;jobs_total&#x60;: Count of all jobs that target this instance group (integer) * &#x60;instances&#x60;:  (field) * &#x60;is_container_group&#x60;: Indicates whether instances in this group are containerized.Containerized groups have a designated Openshift or Kubernetes cluster. (boolean) * &#x60;credential&#x60;:  (id) * &#x60;policy_instance_percentage&#x60;: Minimum percentage of all instances that will be automatically assigned to this group when new instances come online. (integer) * &#x60;policy_instance_minimum&#x60;: Static minimum number of Instances that will be automatically assign to this group when new instances come online. (integer) * &#x60;policy_instance_list&#x60;: List of exact-match Instances that will be assigned to this group (json) * &#x60;pod_spec_override&#x60;:  (string) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object)    ## Sorting  To specify that instance groups are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInstanceGroupsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInstanceGroupsList200Response> apiWorkflowJobTemplateNodesInstanceGroupsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplateNodesInstanceGroupsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplateNodesInstanceGroupsList");
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

        ParameterizedTypeReference<ApiInstanceGroupsList200Response> localReturnType = new ParameterizedTypeReference<ApiInstanceGroupsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_template_nodes/{id}/instance_groups/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
