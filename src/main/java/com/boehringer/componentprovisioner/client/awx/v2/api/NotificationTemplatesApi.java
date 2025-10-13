package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiCredentialsCopyList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ApiInventorySourcesNotificationTemplatesErrorList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.Copy;
import com.boehringer.componentprovisioner.client.awx.v2.model.NotificationTemplate;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.NotificationTemplatesApi")
public class NotificationTemplatesApi extends BaseApi {

    public NotificationTemplatesApi() {
        super(new ApiClient());
    }

    @Autowired
    public NotificationTemplatesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiInventorySourcesNotificationTemplatesErrorCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiInventorySourcesNotificationTemplatesErrorCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiInventorySourcesNotificationTemplatesErrorCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventorySourcesNotificationTemplatesErrorCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/notification_templates_error/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiInventorySourcesNotificationTemplatesErrorList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesNotificationTemplatesErrorListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiInventorySourcesNotificationTemplatesErrorListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesNotificationTemplatesErrorList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesNotificationTemplatesErrorList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/notification_templates_error/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiInventorySourcesNotificationTemplatesStartedCreate(String version, String id, Object data) throws RestClientException {
        return apiInventorySourcesNotificationTemplatesStartedCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiInventorySourcesNotificationTemplatesStartedCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesNotificationTemplatesStartedCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesNotificationTemplatesStartedCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/notification_templates_started/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiInventorySourcesNotificationTemplatesStartedList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesNotificationTemplatesStartedListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiInventorySourcesNotificationTemplatesStartedListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesNotificationTemplatesStartedList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesNotificationTemplatesStartedList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/notification_templates_started/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiInventorySourcesNotificationTemplatesSuccessCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiInventorySourcesNotificationTemplatesSuccessCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * <p><b>400</b>
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiInventorySourcesNotificationTemplatesSuccessCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiInventorySourcesNotificationTemplatesSuccessCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/notification_templates_success/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiInventorySourcesNotificationTemplatesSuccessList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiInventorySourcesNotificationTemplatesSuccessListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected inventory source.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiInventorySourcesNotificationTemplatesSuccessListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiInventorySourcesNotificationTemplatesSuccessList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiInventorySourcesNotificationTemplatesSuccessList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/inventory_sources/{id}/notification_templates_success/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiJobTemplatesNotificationTemplatesErrorCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiJobTemplatesNotificationTemplatesErrorCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiJobTemplatesNotificationTemplatesErrorCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiJobTemplatesNotificationTemplatesErrorCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/notification_templates_error/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiJobTemplatesNotificationTemplatesErrorList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesNotificationTemplatesErrorListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiJobTemplatesNotificationTemplatesErrorListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesNotificationTemplatesErrorList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesNotificationTemplatesErrorList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/notification_templates_error/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiJobTemplatesNotificationTemplatesStartedCreate(String version, String id, Object data) throws RestClientException {
        return apiJobTemplatesNotificationTemplatesStartedCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiJobTemplatesNotificationTemplatesStartedCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesNotificationTemplatesStartedCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesNotificationTemplatesStartedCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/notification_templates_started/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiJobTemplatesNotificationTemplatesStartedList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesNotificationTemplatesStartedListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiJobTemplatesNotificationTemplatesStartedListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesNotificationTemplatesStartedList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesNotificationTemplatesStartedList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/notification_templates_started/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiJobTemplatesNotificationTemplatesSuccessCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiJobTemplatesNotificationTemplatesSuccessCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiJobTemplatesNotificationTemplatesSuccessCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiJobTemplatesNotificationTemplatesSuccessCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/notification_templates_success/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiJobTemplatesNotificationTemplatesSuccessList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiJobTemplatesNotificationTemplatesSuccessListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiJobTemplatesNotificationTemplatesSuccessListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiJobTemplatesNotificationTemplatesSuccessList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiJobTemplatesNotificationTemplatesSuccessList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/job_templates/{id}/notification_templates_success/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for post on /api/{version}/notification_templates/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Copy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Copy apiNotificationTemplatesCopyCreate(String version, String id, Copy data) throws RestClientException {
        return apiNotificationTemplatesCopyCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * No Description for post on /api/{version}/notification_templates/{id}/copy/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Copy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Copy> apiNotificationTemplatesCopyCreateWithHttpInfo(String version, String id, Copy data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesCopyCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesCopyCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiNotificationTemplatesCopyCreate");
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
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/copy/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/notification_templates/{id}/copy/
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
    public ApiCredentialsCopyList200Response apiNotificationTemplatesCopyList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiNotificationTemplatesCopyListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * No Description for get on /api/{version}/notification_templates/{id}/copy/
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
    public ResponseEntity<ApiCredentialsCopyList200Response> apiNotificationTemplatesCopyListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesCopyList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesCopyList");
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
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/copy/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * notification templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiNotificationTemplatesCreate(String version, NotificationTemplate data) throws RestClientException {
        return apiNotificationTemplatesCreateWithHttpInfo(version, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * notification templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiNotificationTemplatesCreateWithHttpInfo(String version, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiNotificationTemplatesCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/notification_templates/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiNotificationTemplatesDelete(String version, String id) throws RestClientException {
        apiNotificationTemplatesDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiNotificationTemplatesDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesDelete");
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
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * notification templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiNotificationTemplatesList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiNotificationTemplatesListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * notification templates.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * <p><b>403</b>
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiNotificationTemplatesListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/notification_templates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiNotificationTemplatesPartialUpdate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiNotificationTemplatesPartialUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiNotificationTemplatesPartialUpdateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesPartialUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiNotificationTemplatesPartialUpdate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiNotificationTemplatesRead(String version, String id) throws RestClientException {
        return apiNotificationTemplatesReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiNotificationTemplatesReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesRead");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Test a Notification Template
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object apiNotificationTemplatesTestCreate(String version, String id, Object data) throws RestClientException {
        return apiNotificationTemplatesTestCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Test a Notification Template
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> apiNotificationTemplatesTestCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesTestCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesTestCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiNotificationTemplatesTestCreate");
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
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/test/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiNotificationTemplatesUpdate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiNotificationTemplatesUpdateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single notification template
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)      # Update a Notification Template:  Make a PUT or PATCH request to this resource to update this notification template.  The following fields may be modified:          * &#x60;name&#x60;: Name of this notification template. (string, required) * &#x60;description&#x60;: Optional description of this notification template. (string, default&#x3D;&#x60;\&quot;\&quot;&#x60;) * &#x60;organization&#x60;:  (id, required) * &#x60;notification_type&#x60;:  (choice, required)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json, default&#x3D;&#x60;{}&#x60;) * &#x60;messages&#x60;: Optional custom messages for notification template. (json, default&#x3D;&#x60;{&amp;#x27;started&amp;#x27;: None, &amp;#x27;success&amp;#x27;: None, &amp;#x27;error&amp;#x27;: None, &amp;#x27;workflow_approval&amp;#x27;: None}&#x60;)       For a PUT request, include **all** fields in the request.    For a PATCH request, include only the fields that are being modified.    # Delete a Notification Template:  Make a DELETE request to this resource to delete this notification template.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiNotificationTemplatesUpdateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiNotificationTemplatesUpdate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiNotificationTemplatesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiNotificationTemplatesUpdate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/notification_templates/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiOrganizationsNotificationTemplatesApprovalsCreate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsNotificationTemplatesApprovalsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiOrganizationsNotificationTemplatesApprovalsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesApprovalsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesApprovalsCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_approvals/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiOrganizationsNotificationTemplatesApprovalsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsNotificationTemplatesApprovalsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiOrganizationsNotificationTemplatesApprovalsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesApprovalsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesApprovalsList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_approvals/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiOrganizationsNotificationTemplatesCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiOrganizationsNotificationTemplatesCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiOrganizationsNotificationTemplatesCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsNotificationTemplatesCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiOrganizationsNotificationTemplatesErrorCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiOrganizationsNotificationTemplatesErrorCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiOrganizationsNotificationTemplatesErrorCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsNotificationTemplatesErrorCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_error/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiOrganizationsNotificationTemplatesErrorList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsNotificationTemplatesErrorListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiOrganizationsNotificationTemplatesErrorListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesErrorList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesErrorList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_error/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiOrganizationsNotificationTemplatesList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsNotificationTemplatesListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiOrganizationsNotificationTemplatesListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiOrganizationsNotificationTemplatesStartedCreate(String version, String id, Object data) throws RestClientException {
        return apiOrganizationsNotificationTemplatesStartedCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiOrganizationsNotificationTemplatesStartedCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesStartedCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesStartedCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_started/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiOrganizationsNotificationTemplatesStartedList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsNotificationTemplatesStartedListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiOrganizationsNotificationTemplatesStartedListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesStartedList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesStartedList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_started/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiOrganizationsNotificationTemplatesSuccessCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiOrganizationsNotificationTemplatesSuccessCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiOrganizationsNotificationTemplatesSuccessCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiOrganizationsNotificationTemplatesSuccessCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_success/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiOrganizationsNotificationTemplatesSuccessList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiOrganizationsNotificationTemplatesSuccessListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected organization.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiOrganizationsNotificationTemplatesSuccessListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiOrganizationsNotificationTemplatesSuccessList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiOrganizationsNotificationTemplatesSuccessList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/organizations/{id}/notification_templates_success/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiProjectsNotificationTemplatesErrorCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiProjectsNotificationTemplatesErrorCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiProjectsNotificationTemplatesErrorCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectsNotificationTemplatesErrorCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/notification_templates_error/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiProjectsNotificationTemplatesErrorList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsNotificationTemplatesErrorListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiProjectsNotificationTemplatesErrorListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsNotificationTemplatesErrorList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsNotificationTemplatesErrorList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/notification_templates_error/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiProjectsNotificationTemplatesStartedCreate(String version, String id, Object data) throws RestClientException {
        return apiProjectsNotificationTemplatesStartedCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiProjectsNotificationTemplatesStartedCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsNotificationTemplatesStartedCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsNotificationTemplatesStartedCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/notification_templates_started/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiProjectsNotificationTemplatesStartedList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsNotificationTemplatesStartedListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiProjectsNotificationTemplatesStartedListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsNotificationTemplatesStartedList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsNotificationTemplatesStartedList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/notification_templates_started/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiProjectsNotificationTemplatesSuccessCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiProjectsNotificationTemplatesSuccessCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiProjectsNotificationTemplatesSuccessCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiProjectsNotificationTemplatesSuccessCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/notification_templates_success/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiProjectsNotificationTemplatesSuccessList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiProjectsNotificationTemplatesSuccessListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected project.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiProjectsNotificationTemplatesSuccessListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiProjectsNotificationTemplatesSuccessList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiProjectsNotificationTemplatesSuccessList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/projects/{id}/notification_templates_success/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiSystemJobTemplatesNotificationTemplatesErrorCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiSystemJobTemplatesNotificationTemplatesErrorCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiSystemJobTemplatesNotificationTemplatesErrorCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSystemJobTemplatesNotificationTemplatesErrorCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/notification_templates_error/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiSystemJobTemplatesNotificationTemplatesErrorList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobTemplatesNotificationTemplatesErrorListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiSystemJobTemplatesNotificationTemplatesErrorListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesNotificationTemplatesErrorList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesNotificationTemplatesErrorList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/notification_templates_error/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiSystemJobTemplatesNotificationTemplatesStartedCreate(String version, String id, Object data) throws RestClientException {
        return apiSystemJobTemplatesNotificationTemplatesStartedCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiSystemJobTemplatesNotificationTemplatesStartedCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesNotificationTemplatesStartedCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesNotificationTemplatesStartedCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/notification_templates_started/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiSystemJobTemplatesNotificationTemplatesStartedList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobTemplatesNotificationTemplatesStartedListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiSystemJobTemplatesNotificationTemplatesStartedListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesNotificationTemplatesStartedList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesNotificationTemplatesStartedList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/notification_templates_started/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiSystemJobTemplatesNotificationTemplatesSuccessCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiSystemJobTemplatesNotificationTemplatesSuccessCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiSystemJobTemplatesNotificationTemplatesSuccessCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiSystemJobTemplatesNotificationTemplatesSuccessCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/notification_templates_success/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiSystemJobTemplatesNotificationTemplatesSuccessList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiSystemJobTemplatesNotificationTemplatesSuccessListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected system job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiSystemJobTemplatesNotificationTemplatesSuccessListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSystemJobTemplatesNotificationTemplatesSuccessList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiSystemJobTemplatesNotificationTemplatesSuccessList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/system_job_templates/{id}/notification_templates_success/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiWorkflowJobTemplatesNotificationTemplatesApprovalsCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesApprovalsCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiWorkflowJobTemplatesNotificationTemplatesApprovalsCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesApprovalsCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesApprovalsCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_approvals/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiWorkflowJobTemplatesNotificationTemplatesApprovalsList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesApprovalsListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiWorkflowJobTemplatesNotificationTemplatesApprovalsListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesApprovalsList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesApprovalsList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_approvals/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiWorkflowJobTemplatesNotificationTemplatesErrorCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesErrorCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiWorkflowJobTemplatesNotificationTemplatesErrorCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesErrorCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplatesNotificationTemplatesErrorCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_error/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiWorkflowJobTemplatesNotificationTemplatesErrorList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesErrorListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiWorkflowJobTemplatesNotificationTemplatesErrorListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesErrorList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesErrorList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_error/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiWorkflowJobTemplatesNotificationTemplatesStartedCreate(String version, String id, Object data) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesStartedCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (optional)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiWorkflowJobTemplatesNotificationTemplatesStartedCreateWithHttpInfo(String version, String id, Object data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesStartedCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesStartedCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_started/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiWorkflowJobTemplatesNotificationTemplatesStartedList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesStartedListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiWorkflowJobTemplatesNotificationTemplatesStartedListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesStartedList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesStartedList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_started/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return NotificationTemplate
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NotificationTemplate apiWorkflowJobTemplatesNotificationTemplatesSuccessCreate(String version, String id, NotificationTemplate data) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesSuccessCreateWithHttpInfo(version, id, data).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>201</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param data  (required)
     * @return ResponseEntity&lt;NotificationTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NotificationTemplate> apiWorkflowJobTemplatesNotificationTemplatesSuccessCreateWithHttpInfo(String version, String id, NotificationTemplate data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesSuccessCreate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiWorkflowJobTemplatesNotificationTemplatesSuccessCreate");
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

        ParameterizedTypeReference<NotificationTemplate> localReturnType = new ParameterizedTypeReference<NotificationTemplate>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_success/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiInventorySourcesNotificationTemplatesErrorList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiInventorySourcesNotificationTemplatesErrorList200Response apiWorkflowJobTemplatesNotificationTemplatesSuccessList(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiWorkflowJobTemplatesNotificationTemplatesSuccessListWithHttpInfo(version, id, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve a list of
     * notification templates associated with the selected workflow job template.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of notification templates found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more notification template records.    ## Results  Each notification template data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this notification template. (integer) * &#x60;type&#x60;: Data type for this notification template. (choice) * &#x60;url&#x60;: URL for this notification template. (string) * &#x60;related&#x60;: Data structure with URLs of related resources. (object) * &#x60;summary_fields&#x60;: Data structure with name/description for related resources.  The output for some objects may be limited for performance reasons. (object) * &#x60;created&#x60;: Timestamp when this notification template was created. (datetime) * &#x60;modified&#x60;: Timestamp when this notification template was last modified. (datetime) * &#x60;name&#x60;: Name of this notification template. (string) * &#x60;description&#x60;: Optional description of this notification template. (string) * &#x60;organization&#x60;:  (id) * &#x60;notification_type&#x60;:  (choice)     - &#x60;awssns&#x60;: AWS SNS     - &#x60;email&#x60;: Email     - &#x60;grafana&#x60;: Grafana     - &#x60;irc&#x60;: IRC     - &#x60;mattermost&#x60;: Mattermost     - &#x60;pagerduty&#x60;: Pagerduty     - &#x60;rocketchat&#x60;: Rocket.Chat     - &#x60;slack&#x60;: Slack     - &#x60;twilio&#x60;: Twilio     - &#x60;webhook&#x60;: Webhook * &#x60;notification_configuration&#x60;:  (json) * &#x60;messages&#x60;: Optional custom messages for notification template. (json)    ## Sorting  To specify that notification templates are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiInventorySourcesNotificationTemplatesErrorList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiInventorySourcesNotificationTemplatesErrorList200Response> apiWorkflowJobTemplatesNotificationTemplatesSuccessListWithHttpInfo(String version, String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiWorkflowJobTemplatesNotificationTemplatesSuccessList");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiWorkflowJobTemplatesNotificationTemplatesSuccessList");
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

        ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response> localReturnType = new ParameterizedTypeReference<ApiInventorySourcesNotificationTemplatesErrorList200Response>() {};
        return apiClient.invokeAPI("/api/v2/workflow_job_templates/{id}/notification_templates_success/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
