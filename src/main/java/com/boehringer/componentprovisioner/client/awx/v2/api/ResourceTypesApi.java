package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiV2ServiceIndexResourceTypesList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.ResourceType;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ResourceTypesApi")
public class ResourceTypesApi extends BaseApi {

    public ResourceTypesApi() {
        super(new ApiClient());
    }

    @Autowired
    public ResourceTypesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Index of the resource types that are configured in the system.
     * 
     * <p><b>200</b> - 
     * @param page A page number within the paginated result set. (optional)
     * @return ApiV2ServiceIndexResourceTypesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiV2ServiceIndexResourceTypesList200Response apiV2ServiceIndexResourceTypesList(Integer page) throws RestClientException {
        return apiV2ServiceIndexResourceTypesListWithHttpInfo(page).getBody();
    }

    /**
     * Index of the resource types that are configured in the system.
     * 
     * <p><b>200</b> - 
     * @param page A page number within the paginated result set. (optional)
     * @return ResponseEntity&lt;ApiV2ServiceIndexResourceTypesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiV2ServiceIndexResourceTypesList200Response> apiV2ServiceIndexResourceTypesListWithHttpInfo(Integer page) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        ParameterizedTypeReference<ApiV2ServiceIndexResourceTypesList200Response> localReturnType = new ParameterizedTypeReference<ApiV2ServiceIndexResourceTypesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resource-types/", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns the as a stream the csv of resource_id,hash for a given resource type.
     * 
     * <p><b>200</b> - 
     * @param name The name of this resource type. (required)
     * @return ResourceType
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceType apiV2ServiceIndexResourceTypesManifest(String name) throws RestClientException {
        return apiV2ServiceIndexResourceTypesManifestWithHttpInfo(name).getBody();
    }

    /**
     * Returns the as a stream the csv of resource_id,hash for a given resource type.
     * 
     * <p><b>200</b> - 
     * @param name The name of this resource type. (required)
     * @return ResponseEntity&lt;ResourceType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceType> apiV2ServiceIndexResourceTypesManifestWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling apiV2ServiceIndexResourceTypesManifest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("name", name);

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

        ParameterizedTypeReference<ResourceType> localReturnType = new ParameterizedTypeReference<ResourceType>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resource-types/{name}/manifest/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Index of the resource types that are configured in the system.
     * 
     * <p><b>200</b> - 
     * @param name The name of this resource type. (required)
     * @return ResourceType
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceType apiV2ServiceIndexResourceTypesRead(String name) throws RestClientException {
        return apiV2ServiceIndexResourceTypesReadWithHttpInfo(name).getBody();
    }

    /**
     * Index of the resource types that are configured in the system.
     * 
     * <p><b>200</b> - 
     * @param name The name of this resource type. (required)
     * @return ResponseEntity&lt;ResourceType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceType> apiV2ServiceIndexResourceTypesReadWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling apiV2ServiceIndexResourceTypesRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("name", name);

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

        ParameterizedTypeReference<ResourceType> localReturnType = new ParameterizedTypeReference<ResourceType>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resource-types/{name}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
