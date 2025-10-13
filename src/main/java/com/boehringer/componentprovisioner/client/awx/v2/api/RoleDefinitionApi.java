package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiV2RoleDefinitionsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.RoleDefinition;
import com.boehringer.componentprovisioner.client.awx.v2.model.RoleDefinitionDetail;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.RoleDefinitionApi")
public class RoleDefinitionApi extends BaseApi {

    public RoleDefinitionApi() {
        super(new ApiClient());
    }

    @Autowired
    public RoleDefinitionApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>201</b> - 
     * @param data  (required)
     * @return RoleDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RoleDefinition apiV2RoleDefinitionsCreate(RoleDefinition data) throws RestClientException {
        return apiV2RoleDefinitionsCreateWithHttpInfo(data).getBody();
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>201</b> - 
     * @param data  (required)
     * @return ResponseEntity&lt;RoleDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RoleDefinition> apiV2RoleDefinitionsCreateWithHttpInfo(RoleDefinition data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2RoleDefinitionsCreate");
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

        ParameterizedTypeReference<RoleDefinition> localReturnType = new ParameterizedTypeReference<RoleDefinition>() {};
        return apiClient.invokeAPI("/api/v2/role_definitions/", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>204</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiV2RoleDefinitionsDelete(Integer id) throws RestClientException {
        apiV2RoleDefinitionsDeleteWithHttpInfo(id);
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>204</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiV2RoleDefinitionsDeleteWithHttpInfo(Integer id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleDefinitionsDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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
        return apiClient.invokeAPI("/api/v2/role_definitions/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiV2RoleDefinitionsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiV2RoleDefinitionsList200Response apiV2RoleDefinitionsList(String search, Integer page, Integer pageSize) throws RestClientException {
        return apiV2RoleDefinitionsListWithHttpInfo(search, page, pageSize).getBody();
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiV2RoleDefinitionsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiV2RoleDefinitionsList200Response> apiV2RoleDefinitionsListWithHttpInfo(String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ApiV2RoleDefinitionsList200Response> localReturnType = new ParameterizedTypeReference<ApiV2RoleDefinitionsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/role_definitions/", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @param data  (required)
     * @return RoleDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RoleDefinition apiV2RoleDefinitionsPartialUpdate(Integer id, RoleDefinition data) throws RestClientException {
        return apiV2RoleDefinitionsPartialUpdateWithHttpInfo(id, data).getBody();
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @param data  (required)
     * @return ResponseEntity&lt;RoleDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RoleDefinition> apiV2RoleDefinitionsPartialUpdateWithHttpInfo(Integer id, RoleDefinition data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleDefinitionsPartialUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2RoleDefinitionsPartialUpdate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RoleDefinition> localReturnType = new ParameterizedTypeReference<RoleDefinition>() {};
        return apiClient.invokeAPI("/api/v2/role_definitions/{id}/", HttpMethod.PATCH, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @return RoleDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RoleDefinition apiV2RoleDefinitionsRead(Integer id) throws RestClientException {
        return apiV2RoleDefinitionsReadWithHttpInfo(id).getBody();
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @return ResponseEntity&lt;RoleDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RoleDefinition> apiV2RoleDefinitionsReadWithHttpInfo(Integer id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleDefinitionsRead");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RoleDefinition> localReturnType = new ParameterizedTypeReference<RoleDefinition>() {};
        return apiClient.invokeAPI("/api/v2/role_definitions/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @param data  (required)
     * @return RoleDefinitionDetail
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RoleDefinitionDetail apiV2RoleDefinitionsUpdate(Integer id, RoleDefinitionDetail data) throws RestClientException {
        return apiV2RoleDefinitionsUpdateWithHttpInfo(id, data).getBody();
    }

    /**
     * Role Definitions (roles) contain a list of permissions and can be used to
     * assign those permissions to a user or team through the respective assignment endpoints.  Custom roles can be created, modified, and deleted through this endpoint. System-managed roles are shown here, which cannot be edited or deleted, but can be assigned to users.
     * <p><b>200</b> - 
     * @param id A unique integer value identifying this role definition. (required)
     * @param data  (required)
     * @return ResponseEntity&lt;RoleDefinitionDetail&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RoleDefinitionDetail> apiV2RoleDefinitionsUpdateWithHttpInfo(Integer id, RoleDefinitionDetail data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleDefinitionsUpdate");
        }
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2RoleDefinitionsUpdate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RoleDefinitionDetail> localReturnType = new ParameterizedTypeReference<RoleDefinitionDetail>() {};
        return apiClient.invokeAPI("/api/v2/role_definitions/{id}/", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
