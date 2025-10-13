package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiAdHocCommandsNotificationsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Notification;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.NotificationsApi")
public class NotificationsApi extends BaseApi {

    public NotificationsApi() {
        super(new ApiClient());
    }

    @Autowired
    public NotificationsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected ad hoc command.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiAdHocCommandsNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiAdHocCommandsNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected ad hoc command.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiAdHocCommandsNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiAdHocCommandsNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiAdHocCommandsNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/ad_hoc_commands/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected inventory update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiInventoryUpdatesNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventoryUpdatesNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected inventory update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiInventoryUpdatesNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventoryUpdatesNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventoryUpdatesNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_updates/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiJobsNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobsNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiJobsNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobsNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobsNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/jobs/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected notification template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiNotificationTemplatesNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiNotificationTemplatesNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected notification template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiNotificationTemplatesNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * notifications.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiNotificationsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiNotificationsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * notifications.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiNotificationsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single notification
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return Notification
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Notification apiNotificationsRead(String version, String id) throws RestClientException {
        return apiNotificationsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single notification
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Notification&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Notification> apiNotificationsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationsRead");
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

        ParameterizedTypeReference<Notification> localReturnType = new ParameterizedTypeReference<Notification>() {};
        return apiClient.invokeAPI("/api/v2/notifications/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected project update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiProjectUpdatesNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectUpdatesNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected project update.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiProjectUpdatesNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectUpdatesNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectUpdatesNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/project_updates/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected system job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiSystemJobsNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobsNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected system job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiSystemJobsNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobsNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobsNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/system_jobs/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected workflow job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiAdHocCommandsNotificationsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiAdHocCommandsNotificationsList200Response apiWorkflowJobsNotificationsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobsNotificationsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notifications associated with the selected workflow job.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notifications found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification records.    ## Results  Each notification data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification. (integer) * &#x60;type&#x60;: Data type for this notification. (choice) * &#x60;url&#x60;: URL for this notification. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification was last modified. (datetime) * &#x60;notification_template&#x60;:  (id) * &#x60;error&#x60;:  (string) * &#x60;status&#x60;:  (choice)     - &#x60;pending&#x60;: Pending     - &#x60;successful&#x60;: Successful     - &#x60;failed&#x60;: Failed * &#x60;notifications_sent&#x60;:  (integer) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;recipients&#x60;:  (string) * &#x60;subject&#x60;:  (string) * &#x60;body&#x60;: Notification body (json)    ## Sorting  To specify that notifications are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiAdHocCommandsNotificationsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiAdHocCommandsNotificationsList200Response> apiWorkflowJobsNotificationsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobsNotificationsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobsNotificationsList");
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

        ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response> localReturnType = new ParameterizedTypeReference<ApiAdHocCommandsNotificationsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_jobs/{id}/notifications/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
