package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiV2ServiceIndexResourcesList200Response;
import org.springframework.core.io.Resource;
import java.util.UUID;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.ResourcesApi")
public class ResourcesApi extends BaseApi {

    public ResourcesApi() {
        super(new ApiClient());
    }

    @Autowired
    public ResourcesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>201</b> - 
     * @param data  (required)
     * @return Resource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Resource apiV2ServiceIndexResourcesCreate(Resource data) throws RestClientException {
        return apiV2ServiceIndexResourcesCreateWithHttpInfo(data).getBody();
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>201</b> - 
     * @param data  (required)
     * @return ResponseEntity&lt;Resource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Resource> apiV2ServiceIndexResourcesCreateWithHttpInfo(Resource data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2ServiceIndexResourcesCreate");
        }
        

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

        ParameterizedTypeReference<Resource> localReturnType = new ParameterizedTypeReference<Resource>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resources/", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Index of all the resources in the system.
     * 
     * <p><b>204</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiV2ServiceIndexResourcesDelete(UUID ansibleId) throws RestClientException {
        apiV2ServiceIndexResourcesDeleteWithHttpInfo(ansibleId);
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>204</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiV2ServiceIndexResourcesDeleteWithHttpInfo(UUID ansibleId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'ansibleId' is set
        if (ansibleId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ansibleId' when calling apiV2ServiceIndexResourcesDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ansible_id", ansibleId);

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
        return apiClient.invokeAPI("/api/v2/service-index/resources/{ansible_id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param page A page number within the paginated result set. (optional)
     * @return ApiV2ServiceIndexResourcesList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiV2ServiceIndexResourcesList200Response apiV2ServiceIndexResourcesList(Integer page) throws RestClientException {
        return apiV2ServiceIndexResourcesListWithHttpInfo(page).getBody();
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param page A page number within the paginated result set. (optional)
     * @return ResponseEntity&lt;ApiV2ServiceIndexResourcesList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiV2ServiceIndexResourcesList200Response> apiV2ServiceIndexResourcesListWithHttpInfo(Integer page) throws RestClientException {
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

        ParameterizedTypeReference<ApiV2ServiceIndexResourcesList200Response> localReturnType = new ParameterizedTypeReference<ApiV2ServiceIndexResourcesList200Response>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resources/", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @param data  (required)
     * @return Resource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Resource apiV2ServiceIndexResourcesPartialUpdate(UUID ansibleId, Resource data) throws RestClientException {
        return apiV2ServiceIndexResourcesPartialUpdateWithHttpInfo(ansibleId, data).getBody();
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Resource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Resource> apiV2ServiceIndexResourcesPartialUpdateWithHttpInfo(UUID ansibleId, Resource data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'ansibleId' is set
        if (ansibleId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ansibleId' when calling apiV2ServiceIndexResourcesPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2ServiceIndexResourcesPartialUpdate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ansible_id", ansibleId);

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

        ParameterizedTypeReference<Resource> localReturnType = new ParameterizedTypeReference<Resource>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resources/{ansible_id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @return Resource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Resource apiV2ServiceIndexResourcesRead(UUID ansibleId) throws RestClientException {
        return apiV2ServiceIndexResourcesReadWithHttpInfo(ansibleId).getBody();
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @return ResponseEntity&lt;Resource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Resource> apiV2ServiceIndexResourcesReadWithHttpInfo(UUID ansibleId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'ansibleId' is set
        if (ansibleId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ansibleId' when calling apiV2ServiceIndexResourcesRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ansible_id", ansibleId);

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

        ParameterizedTypeReference<Resource> localReturnType = new ParameterizedTypeReference<Resource>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resources/{ansible_id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @param data  (required)
     * @return Resource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Resource apiV2ServiceIndexResourcesUpdate(UUID ansibleId, Resource data) throws RestClientException {
        return apiV2ServiceIndexResourcesUpdateWithHttpInfo(ansibleId, data).getBody();
    }

    /**
     * Index of all the resources in the system.
     * 
     * <p><b>200</b> - 
     * @param ansibleId A unique ID identifying this resource by the resource server. (required)
     * @param data  (required)
     * @return ResponseEntity&lt;Resource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Resource> apiV2ServiceIndexResourcesUpdateWithHttpInfo(UUID ansibleId, Resource data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'ansibleId' is set
        if (ansibleId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ansibleId' when calling apiV2ServiceIndexResourcesUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2ServiceIndexResourcesUpdate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ansible_id", ansibleId);

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

        ParameterizedTypeReference<Resource> localReturnType = new ParameterizedTypeReference<Resource>() {};
        return apiClient.invokeAPI("/api/v2/service-index/resources/{ansible_id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "Basic" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
