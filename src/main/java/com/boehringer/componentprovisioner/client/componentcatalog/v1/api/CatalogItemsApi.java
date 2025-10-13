package com.boehringer.componentprovisioner.client.componentcatalog.v1.api;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.BaseApi;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItem;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.RestErrorMessage;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.SortOrder;

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
@Component("com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogItemsApi")
public class CatalogItemsApi extends BaseApi {

    public CatalogItemsApi() {
        super(new ApiClient());
    }

    @Autowired
    public CatalogItemsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Returns the CatalogItem associated to the provided id.
     * Returns the CatalogItem associated to the provided id, unless: &lt;ul&gt; &lt;li&gt; The id is not associated to any CatalogItem. &lt;/li&gt; &lt;li&gt; Or the associated CatalogItem is invalid and can&#39;t be processed to create a response. &lt;/li&gt; &lt;/ul&gt; 
     * <p><b>200</b> - The CatalogItem.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - No CatalogItem associated to the provided id.
     * <p><b>422</b> - Invalid CatalogItem associated to the provided id.
     * <p><b>500</b> - Server error.
     * @param id id for the CatalogItem. (required)
     * @return CatalogItem
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CatalogItem getCatalogItemById(String id) throws RestClientException {
        return getCatalogItemByIdWithHttpInfo(id).getBody();
    }

    /**
     * Returns the CatalogItem associated to the provided id.
     * Returns the CatalogItem associated to the provided id, unless: &lt;ul&gt; &lt;li&gt; The id is not associated to any CatalogItem. &lt;/li&gt; &lt;li&gt; Or the associated CatalogItem is invalid and can&#39;t be processed to create a response. &lt;/li&gt; &lt;/ul&gt; 
     * <p><b>200</b> - The CatalogItem.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - No CatalogItem associated to the provided id.
     * <p><b>422</b> - Invalid CatalogItem associated to the provided id.
     * <p><b>500</b> - Server error.
     * @param id id for the CatalogItem. (required)
     * @return ResponseEntity&lt;CatalogItem&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CatalogItem> getCatalogItemByIdWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getCatalogItemById");
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

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<CatalogItem> localReturnType = new ParameterizedTypeReference<CatalogItem>() {};
        return apiClient.invokeAPI("/catalog-items/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * List of all CatalogItems.
     * Returns a list of all CatalogItems for the given Catalog identified by catalogId.&lt;br/&gt; CatalogItems referenced on a Catalog that are either invalid or non-existent are **excluded** from the response. 
     * <p><b>200</b> - A list of valid CatalogItems.
     * <p><b>400</b> - Invalid parameters provided on the request.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>500</b> - Server error.
     * @param catalogId id for the Catalog. (required)
     * @param sortByTitle Sort the returned CatalogItems by title, either in ascending or descending order. (required)
     * @return List&lt;CatalogItem&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CatalogItem> getCatalogItems(String catalogId, SortOrder sortByTitle) throws RestClientException {
        return getCatalogItemsWithHttpInfo(catalogId, sortByTitle).getBody();
    }

    /**
     * List of all CatalogItems.
     * Returns a list of all CatalogItems for the given Catalog identified by catalogId.&lt;br/&gt; CatalogItems referenced on a Catalog that are either invalid or non-existent are **excluded** from the response. 
     * <p><b>200</b> - A list of valid CatalogItems.
     * <p><b>400</b> - Invalid parameters provided on the request.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>500</b> - Server error.
     * @param catalogId id for the Catalog. (required)
     * @param sortByTitle Sort the returned CatalogItems by title, either in ascending or descending order. (required)
     * @return ResponseEntity&lt;List&lt;CatalogItem&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CatalogItem>> getCatalogItemsWithHttpInfo(String catalogId, SortOrder sortByTitle) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'catalogId' when calling getCatalogItems");
        }
        
        // verify the required parameter 'sortByTitle' is set
        if (sortByTitle == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'sortByTitle' when calling getCatalogItems");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "catalogId", catalogId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortByTitle", sortByTitle));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<List<CatalogItem>> localReturnType = new ParameterizedTypeReference<List<CatalogItem>>() {};
        return apiClient.invokeAPI("/catalog-items", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
