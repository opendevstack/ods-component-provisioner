package com.boehringer.componentprovisioner.client.componentcatalog.v1.api;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.BaseApi;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageDefinition;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.RestErrorMessage;

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
@Component("com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogItemUserActionMessageDefinitionsApi")
public class CatalogItemUserActionMessageDefinitionsApi extends BaseApi {

    public CatalogItemUserActionMessageDefinitionsApi() {
        super(new ApiClient());
    }

    @Autowired
    public CatalogItemUserActionMessageDefinitionsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Get a message definition by id.
     * Returns an standard message definition 
     * <p><b>200</b> - A single message definition.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Server error.
     * @param catalogItemId id for the CatalogItem (required)
     * @param userActionId id for the CatalogItemUserAction (required)
     * @param messageDefinitionId id for the CatalogItemUserActionMessageDefinition (required)
     * @param requestBody  (required)
     * @return CatalogItemUserActionMessageDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CatalogItemUserActionMessageDefinition getMessageDefinitionByCatalogItemIdAndMessageId(String catalogItemId, String userActionId, String messageDefinitionId, Map<String, String> requestBody) throws RestClientException {
        return getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(catalogItemId, userActionId, messageDefinitionId, requestBody).getBody();
    }

    /**
     * Get a message definition by id.
     * Returns an standard message definition 
     * <p><b>200</b> - A single message definition.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Server error.
     * @param catalogItemId id for the CatalogItem (required)
     * @param userActionId id for the CatalogItemUserAction (required)
     * @param messageDefinitionId id for the CatalogItemUserActionMessageDefinition (required)
     * @param requestBody  (required)
     * @return ResponseEntity&lt;CatalogItemUserActionMessageDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CatalogItemUserActionMessageDefinition> getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(String catalogItemId, String userActionId, String messageDefinitionId, Map<String, String> requestBody) throws RestClientException {
        Object localVarPostBody = requestBody;
        
        // verify the required parameter 'catalogItemId' is set
        if (catalogItemId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'catalogItemId' when calling getMessageDefinitionByCatalogItemIdAndMessageId");
        }
        
        // verify the required parameter 'userActionId' is set
        if (userActionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userActionId' when calling getMessageDefinitionByCatalogItemIdAndMessageId");
        }
        
        // verify the required parameter 'messageDefinitionId' is set
        if (messageDefinitionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageDefinitionId' when calling getMessageDefinitionByCatalogItemIdAndMessageId");
        }
        
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestBody' when calling getMessageDefinitionByCatalogItemIdAndMessageId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("catalogItemId", catalogItemId);
        uriVariables.put("userActionId", userActionId);
        uriVariables.put("messageDefinitionId", messageDefinitionId);

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

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<CatalogItemUserActionMessageDefinition> localReturnType = new ParameterizedTypeReference<CatalogItemUserActionMessageDefinition>() {};
        return apiClient.invokeAPI("/catalog-items/{catalogItemId}/user-actions/{userActionId}/messages-definitions/{messageDefinitionId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a message definition by id.
     * Returns an standard message definition 
     * <p><b>200</b> - A single message definition.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Server error.
     * @param userActionId id for the CatalogItemUserAction (required)
     * @param messageDefinitionId id for the CatalogItemUserActionMessageDefinition (required)
     * @param requestBody  (required)
     * @return CatalogItemUserActionMessageDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public CatalogItemUserActionMessageDefinition getMessageDefinitionById(String userActionId, String messageDefinitionId, Map<String, String> requestBody) throws RestClientException {
        return getMessageDefinitionByIdWithHttpInfo(userActionId, messageDefinitionId, requestBody).getBody();
    }

    /**
     * Get a message definition by id.
     * Returns an standard message definition 
     * <p><b>200</b> - A single message definition.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Server error.
     * @param userActionId id for the CatalogItemUserAction (required)
     * @param messageDefinitionId id for the CatalogItemUserActionMessageDefinition (required)
     * @param requestBody  (required)
     * @return ResponseEntity&lt;CatalogItemUserActionMessageDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<CatalogItemUserActionMessageDefinition> getMessageDefinitionByIdWithHttpInfo(String userActionId, String messageDefinitionId, Map<String, String> requestBody) throws RestClientException {
        Object localVarPostBody = requestBody;
        
        // verify the required parameter 'userActionId' is set
        if (userActionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userActionId' when calling getMessageDefinitionById");
        }
        
        // verify the required parameter 'messageDefinitionId' is set
        if (messageDefinitionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageDefinitionId' when calling getMessageDefinitionById");
        }
        
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestBody' when calling getMessageDefinitionById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userActionId", userActionId);
        uriVariables.put("messageDefinitionId", messageDefinitionId);

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

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<CatalogItemUserActionMessageDefinition> localReturnType = new ParameterizedTypeReference<CatalogItemUserActionMessageDefinition>() {};
        return apiClient.invokeAPI("/user-actions/{userActionId}/messages-definitions/{messageDefinitionId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
