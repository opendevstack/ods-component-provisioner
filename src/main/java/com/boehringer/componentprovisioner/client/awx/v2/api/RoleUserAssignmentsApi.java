package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;

import com.boehringer.componentprovisioner.client.awx.v2.model.ApiV2RoleDefinitionsUserAssignmentsList200Response;
import com.boehringer.componentprovisioner.client.awx.v2.model.RoleUserAssignment;

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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.RoleUserAssignmentsApi")
public class RoleUserAssignmentsApi extends BaseApi {

    public RoleUserAssignmentsApi() {
        super(new ApiClient());
    }

    @Autowired
    public RoleUserAssignmentsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Starting from the detail URL
     * GET /:id/user_assignments/ to show role user assignments currently in the relationship
     * <p><b>200</b> - 
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiV2RoleDefinitionsUserAssignmentsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiV2RoleDefinitionsUserAssignmentsList200Response apiV2RoleDefinitionsUserAssignmentsList(String id, String search, Integer page, Integer pageSize) throws RestClientException {
        return apiV2RoleDefinitionsUserAssignmentsListWithHttpInfo(id, search, page, pageSize).getBody();
    }

    /**
     * Starting from the detail URL
     * GET /:id/user_assignments/ to show role user assignments currently in the relationship
     * <p><b>200</b> - 
     * @param id  (required)
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiV2RoleDefinitionsUserAssignmentsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiV2RoleDefinitionsUserAssignmentsList200Response> apiV2RoleDefinitionsUserAssignmentsListWithHttpInfo(String id, String search, Integer page, Integer pageSize) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleDefinitionsUserAssignmentsList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<ApiV2RoleDefinitionsUserAssignmentsList200Response> localReturnType = new ParameterizedTypeReference<ApiV2RoleDefinitionsUserAssignmentsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/role_definitions/{id}/user_assignments/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>201</b> - 
     * @param data  (required)
     * @return RoleUserAssignment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RoleUserAssignment apiV2RoleUserAssignmentsCreate(RoleUserAssignment data) throws RestClientException {
        return apiV2RoleUserAssignmentsCreateWithHttpInfo(data).getBody();
    }

    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>201</b> - 
     * @param data  (required)
     * @return ResponseEntity&lt;RoleUserAssignment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RoleUserAssignment> apiV2RoleUserAssignmentsCreateWithHttpInfo(RoleUserAssignment data) throws RestClientException {
        Object localVarPostBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling apiV2RoleUserAssignmentsCreate");
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

        ParameterizedTypeReference<RoleUserAssignment> localReturnType = new ParameterizedTypeReference<RoleUserAssignment>() {};
        return apiClient.invokeAPI("/api/v2/role_user_assignments/", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>204</b> - 
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiV2RoleUserAssignmentsDelete(String id) throws RestClientException {
        apiV2RoleUserAssignmentsDeleteWithHttpInfo(id);
    }

    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>204</b> - 
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiV2RoleUserAssignmentsDeleteWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleUserAssignmentsDelete");
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
        return apiClient.invokeAPI("/api/v2/role_user_assignments/{id}/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>200</b> - 
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ApiV2RoleDefinitionsUserAssignmentsList200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiV2RoleDefinitionsUserAssignmentsList200Response apiV2RoleUserAssignmentsList(String search, Integer page, Integer pageSize) throws RestClientException {
        return apiV2RoleUserAssignmentsListWithHttpInfo(search, page, pageSize).getBody();
    }

    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>200</b> - 
     * @param search A search term. (optional)
     * @param page A page number within the paginated result set. (optional)
     * @param pageSize Number of results to return per page. (optional)
     * @return ResponseEntity&lt;ApiV2RoleDefinitionsUserAssignmentsList200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiV2RoleDefinitionsUserAssignmentsList200Response> apiV2RoleUserAssignmentsListWithHttpInfo(String search, Integer page, Integer pageSize) throws RestClientException {
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

        ParameterizedTypeReference<ApiV2RoleDefinitionsUserAssignmentsList200Response> localReturnType = new ParameterizedTypeReference<ApiV2RoleDefinitionsUserAssignmentsList200Response>() {};
        return apiClient.invokeAPI("/api/v2/role_user_assignments/", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>200</b> - 
     * @param id  (required)
     * @return RoleUserAssignment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RoleUserAssignment apiV2RoleUserAssignmentsRead(String id) throws RestClientException {
        return apiV2RoleUserAssignmentsReadWithHttpInfo(id).getBody();
    }

    /**
     * Use this endpoint to give a user permission to a resource or an organization.
     * The needed data is the user, the role definition, and the object id. The object must be of the type specified in the role definition. The type given in the role definition and the provided object_id are used to look up the resource.  After creation, the assignment cannot be edited, but can be deleted to remove those permissions.
     * <p><b>200</b> - 
     * @param id  (required)
     * @return ResponseEntity&lt;RoleUserAssignment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RoleUserAssignment> apiV2RoleUserAssignmentsReadWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling apiV2RoleUserAssignmentsRead");
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

        ParameterizedTypeReference<RoleUserAssignment> localReturnType = new ParameterizedTypeReference<RoleUserAssignment>() {};
        return apiClient.invokeAPI("/api/v2/role_user_assignments/{id}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
