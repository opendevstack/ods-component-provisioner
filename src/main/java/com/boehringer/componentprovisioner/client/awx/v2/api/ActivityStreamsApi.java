package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ActivityStream;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiActivityStreamList200Response;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ActivityStreamsApi")
public class ActivityStreamsApi extends BaseApi {

    public ActivityStreamsApi() {
        super(new ApiClient());
    }

    @Autowired
    public ActivityStreamsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * activity streams.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiActivityStreamList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiActivityStreamListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * activity streams.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiActivityStreamListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single activity stream
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ActivityStream
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ActivityStream apiActivityStreamRead(String version, String id) throws RestClientException {
        return apiActivityStreamReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single activity stream
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;ActivityStream&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ActivityStream> apiActivityStreamReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiActivityStreamRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiActivityStreamRead");
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

        ParameterizedTypeReference<ActivityStream> localReturnType = new ParameterizedTypeReference<ActivityStream>() {};
        return apiClient.invokeAPI("/api/v2/activity_stream/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected ad hoc command.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiAdHocCommandsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiAdHocCommandsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected ad hoc command.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiAdHocCommandsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected credential type.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiCredentialTypesActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialTypesActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected credential type.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiCredentialTypesActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialTypesActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialTypesActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credential_types/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiCredentialsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiCredentialsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected credential.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiCredentialsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiCredentialsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiCredentialsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/credentials/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected execution environment.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiExecutionEnvironmentsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiExecutionEnvironmentsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected execution environment.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiExecutionEnvironmentsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiExecutionEnvironmentsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiExecutionEnvironmentsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/execution_environments/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiGroupsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiGroupsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected group.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiGroupsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiGroupsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiGroupsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/groups/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiHostsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected host.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiHostsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/hosts/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiInventoriesActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoriesActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected inventory.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiInventoriesActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoriesActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoriesActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventories/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiInventorySourcesActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiInventorySourcesActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiJobTemplatesActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiJobTemplatesActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiJobsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiJobsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiOrganizationsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiOrganizationsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiProjectsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiProjectsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiTeamsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiTeamsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected team.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiTeamsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiTeamsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiTeamsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/teams/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiUsersActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiUsersActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected user.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiUsersActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiUsersActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiUsersActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/users/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiWorkflowJobTemplatesActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiWorkflowJobTemplatesActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected workflow job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiActivityStreamList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiActivityStreamList200Response apiWorkflowJobsActivityStreamList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobsActivityStreamListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * activity streams associated with the selected workflow job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of activity streams found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more activity stream records.    ## Results  Each activity stream data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this activity stream. (integer) * &#x60;type&#x60;: Data type for this activity stream. (choice) * &#x60;url&#x60;: URL for this activity stream. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;timestamp&#x60;:  (datetime) * &#x60;operation&#x60;: The action taken with respect to the given object(s). (choice)     - &#x60;create&#x60;: Entity Created     - &#x60;update&#x60;: Entity Updated     - &#x60;delete&#x60;: Entity Deleted     - &#x60;associate&#x60;: Entity Associated with another Entity     - &#x60;disassociate&#x60;: Entity was Disassociated with another Entity * &#x60;changes&#x60;: A summary of the new and changed values when an object is created, updated, or deleted (json) * &#x60;object1&#x60;: For create, update, and delete events this is the object type that was affected. For associate and disassociate events this is the object type associated or disassociated with object2. (string) * &#x60;object2&#x60;: Unpopulated for create, update, and delete events. For associate and disassociate events this is the object type that object1 is being associated with. (string) * &#x60;object_association&#x60;: When present, shows the field name of the role or relationship that changed. (field) * &#x60;action_node&#x60;: The cluster node the activity took place on. (string) * &#x60;object_type&#x60;: When present, shows the model on which the role or relationship was defined. (field)    ## Sorting  To specify that activity streams are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiActivityStreamList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiActivityStreamList200Response> apiWorkflowJobsActivityStreamListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsActivityStreamList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsActivityStreamList");
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

        ParameterizedTypeReference<ApiActivityStreamList200Response> localReturnType = new ParameterizedTypeReference<ApiActivityStreamList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/activity_stream/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
