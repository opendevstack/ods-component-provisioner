package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiHostMetricsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.HostMetric;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.HostMetricsApi")
public class HostMetricsApi extends BaseApi {

    public HostMetricsApi() {
        super(new ApiClient());
    }

    @Autowired
    public HostMetricsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Make GET request to this resource to retrieve a single host metric
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host metric. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;url&#x60;: URL for this host metric. (string) * &#x60;first_automation&#x60;: When the host was first automated against (datetime) * &#x60;last_automation&#x60;: When the host was last automated against (datetime) * &#x60;last_deleted&#x60;: When the host was last deleted (datetime) * &#x60;automated_counter&#x60;: How many times was the host automated (integer) * &#x60;deleted_counter&#x60;: How many times was the host deleted (integer) * &#x60;deleted&#x60;: Boolean flag saying whether the host is deleted and therefore not counted into the subscription consumption (boolean) * &#x60;used_in_inventories&#x60;: How many inventories contain this host (integer)      # Delete a Host Metric:  Make a DELETE request to this resource to soft-delete this host metric.  A soft deletion will mark the &#x60;deleted&#x60; field as true and exclude the host metric from license calculations. This may be undone later if the same hostname is automated again afterwards.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiHostMetricsDelete(String version, String id) throws RestClientException {
        apiHostMetricsDeleteWithHttpInfo(version, id);
    }

    /**
     * Make GET request to this resource to retrieve a single host metric
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host metric. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;url&#x60;: URL for this host metric. (string) * &#x60;first_automation&#x60;: When the host was first automated against (datetime) * &#x60;last_automation&#x60;: When the host was last automated against (datetime) * &#x60;last_deleted&#x60;: When the host was last deleted (datetime) * &#x60;automated_counter&#x60;: How many times was the host automated (integer) * &#x60;deleted_counter&#x60;: How many times was the host deleted (integer) * &#x60;deleted&#x60;: Boolean flag saying whether the host is deleted and therefore not counted into the subscription consumption (boolean) * &#x60;used_in_inventories&#x60;: How many inventories contain this host (integer)      # Delete a Host Metric:  Make a DELETE request to this resource to soft-delete this host metric.  A soft deletion will mark the &#x60;deleted&#x60; field as true and exclude the host metric from license calculations. This may be undone later if the same hostname is automated again afterwards.
     * <p><b>204</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiHostMetricsDeleteWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostMetricsDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostMetricsDelete");
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
        return apiClient.invokeAPI("/api/v2/host_metrics/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the list of
     * host metrics.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of host metrics found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host metric records.    ## Results  Each host metric data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host metric. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;url&#x60;: URL for this host metric. (string) * &#x60;first_automation&#x60;: When the host was first automated against (datetime) * &#x60;last_automation&#x60;: When the host was last automated against (datetime) * &#x60;last_deleted&#x60;: When the host was last deleted (datetime) * &#x60;automated_counter&#x60;: How many times was the host automated (integer) * &#x60;deleted_counter&#x60;: How many times was the host deleted (integer) * &#x60;deleted&#x60;: Boolean flag saying whether the host is deleted and therefore not counted into the subscription consumption (boolean) * &#x60;used_in_inventories&#x60;: How many inventories contain this host (integer)    ## Sorting  To specify that host metrics are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiHostMetricsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHostMetricsList200Response apiHostMetricsList(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiHostMetricsListWithHttpInfo(version, search, page, pageSize).getBody();
    }

    /**
     * Make a GET request to this resource to retrieve the list of
     * host metrics.  The resulting data structure contains:      {         \&quot;count\&quot;: 99,         \&quot;next\&quot;: null,         \&quot;previous\&quot;: null,         \&quot;results\&quot;: [             ...         ]     }  The &#x60;count&#x60; field indicates the total number of host metrics found for the given query.  The &#x60;next&#x60; and &#x60;previous&#x60; fields provides links to additional results if there are more than will fit on a single page.  The &#x60;results&#x60; list contains zero or more host metric records.    ## Results  Each host metric data structure includes the following fields:  * &#x60;id&#x60;: Database ID for this host metric. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;url&#x60;: URL for this host metric. (string) * &#x60;first_automation&#x60;: When the host was first automated against (datetime) * &#x60;last_automation&#x60;: When the host was last automated against (datetime) * &#x60;last_deleted&#x60;: When the host was last deleted (datetime) * &#x60;automated_counter&#x60;: How many times was the host automated (integer) * &#x60;deleted_counter&#x60;: How many times was the host deleted (integer) * &#x60;deleted&#x60;: Boolean flag saying whether the host is deleted and therefore not counted into the subscription consumption (boolean) * &#x60;used_in_inventories&#x60;: How many inventories contain this host (integer)    ## Sorting  To specify that host metrics are returned in a particular order, use the &#x60;order_by&#x60; query string parameter on the GET request.      ?order_by&#x3D;name  Prefix the field name with a dash &#x60;-&#x60; to sort in reverse:      ?order_by&#x3D;-name  Multiple sorting fields may be specified by separating the field names with a comma &#x60;,&#x60;:      ?order_by&#x3D;name,some_other_field  ## Pagination  Use the &#x60;page_size&#x60; query string parameter to change the number of results returned for each request.  Use the &#x60;page&#x60; query string parameter to retrieve a particular page of results.      ?page_size&#x3D;100&amp;page&#x3D;2  The &#x60;previous&#x60; and &#x60;next&#x60; links returned with the results will set these query string parameters automatically.  ## Searching  Use the &#x60;search&#x60; query string parameter to perform a case-insensitive search within all designated text fields of a model.      ?search&#x3D;findme  (_Added in Ansible Tower 3.1.0_) Search across related fields:      ?related__search&#x3D;findme
     * <p><b>200</b> - 
     * @param version  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiHostMetricsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHostMetricsList200Response> apiHostMetricsListWithHttpInfo(String version, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostMetricsList");
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

        ParameterizedTypeReference<ApiHostMetricsList200Response> localReturnType = new ParameterizedTypeReference<ApiHostMetricsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/host_metrics/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make GET request to this resource to retrieve a single host metric
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host metric. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;url&#x60;: URL for this host metric. (string) * &#x60;first_automation&#x60;: When the host was first automated against (datetime) * &#x60;last_automation&#x60;: When the host was last automated against (datetime) * &#x60;last_deleted&#x60;: When the host was last deleted (datetime) * &#x60;automated_counter&#x60;: How many times was the host automated (integer) * &#x60;deleted_counter&#x60;: How many times was the host deleted (integer) * &#x60;deleted&#x60;: Boolean flag saying whether the host is deleted and therefore not counted into the subscription consumption (boolean) * &#x60;used_in_inventories&#x60;: How many inventories contain this host (integer)      # Delete a Host Metric:  Make a DELETE request to this resource to soft-delete this host metric.  A soft deletion will mark the &#x60;deleted&#x60; field as true and exclude the host metric from license calculations. This may be undone later if the same hostname is automated again afterwards.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return HostMetric
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HostMetric apiHostMetricsRead(String version, String id) throws RestClientException {
        return apiHostMetricsReadWithHttpInfo(version, id).getBody();
    }

    /**
     * Make GET request to this resource to retrieve a single host metric
     * record containing the following fields:  * &#x60;id&#x60;: Database ID for this host metric. (integer) * &#x60;hostname&#x60;:  (string) * &#x60;url&#x60;: URL for this host metric. (string) * &#x60;first_automation&#x60;: When the host was first automated against (datetime) * &#x60;last_automation&#x60;: When the host was last automated against (datetime) * &#x60;last_deleted&#x60;: When the host was last deleted (datetime) * &#x60;automated_counter&#x60;: How many times was the host automated (integer) * &#x60;deleted_counter&#x60;: How many times was the host deleted (integer) * &#x60;deleted&#x60;: Boolean flag saying whether the host is deleted and therefore not counted into the subscription consumption (boolean) * &#x60;used_in_inventories&#x60;: How many inventories contain this host (integer)      # Delete a Host Metric:  Make a DELETE request to this resource to soft-delete this host metric.  A soft deletion will mark the &#x60;deleted&#x60; field as true and exclude the host metric from license calculations. This may be undone later if the same hostname is automated again afterwards.
     * <p><b>200</b> - 
     * @param version  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;HostMetric&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HostMetric> apiHostMetricsReadWithHttpInfo(String version, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiHostMetricsRead");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiHostMetricsRead");
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

        ParameterizedTypeReference<HostMetric> localReturnType = new ParameterizedTypeReference<HostMetric>() {};
        return apiClient.invokeAPI("/api/v2/host_metrics/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
