package com.boehringer.componentprovisioner.client.componentcatalog.v1.api;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.BaseApi;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemFilter;
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
@Component("com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogFiltersApi")
public class CatalogFiltersApi extends BaseApi {

    public CatalogFiltersApi() {
        super(new ApiClient());
    }

    @Autowired
    public CatalogFiltersApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * List of all CatalogItemFilters.
     * Returns the list of all CatalogItemFilters for the CatalogItems on the Catalog identified by catalogId.&lt;br/&gt; CatalogItemFilters are built based on the contents of the Catalog and its CatalogItems.&lt;br/&gt; Catalog or CatalogItems **with errors** will affect the number and/or contents of the returned CatalogItemFilters. 
     * <p><b>200</b> - A list of CatalogItemFilters.
     * <p><b>400</b> - Invalid parameters provided on the request.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>500</b> - Server error.
     * @param catalogId id for the Catalog. (required)
     * @return List&lt;CatalogItemFilter&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CatalogItemFilter> getCatalogFilters(String catalogId) throws RestClientException {
        return getCatalogFiltersWithHttpInfo(catalogId).getBody();
    }

    /**
     * List of all CatalogItemFilters.
     * Returns the list of all CatalogItemFilters for the CatalogItems on the Catalog identified by catalogId.&lt;br/&gt; CatalogItemFilters are built based on the contents of the Catalog and its CatalogItems.&lt;br/&gt; Catalog or CatalogItems **with errors** will affect the number and/or contents of the returned CatalogItemFilters. 
     * <p><b>200</b> - A list of CatalogItemFilters.
     * <p><b>400</b> - Invalid parameters provided on the request.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>500</b> - Server error.
     * @param catalogId id for the Catalog. (required)
     * @return ResponseEntity&lt;List&lt;CatalogItemFilter&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CatalogItemFilter>> getCatalogFiltersWithHttpInfo(String catalogId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'catalogId' when calling getCatalogFilters");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "catalogId", catalogId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<List<CatalogItemFilter>> localReturnType = new ParameterizedTypeReference<List<CatalogItemFilter>>() {};
        return apiClient.invokeAPI("/catalog-filters", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
