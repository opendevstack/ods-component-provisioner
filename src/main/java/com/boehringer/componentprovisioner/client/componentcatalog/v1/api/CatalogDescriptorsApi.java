package com.boehringer.componentprovisioner.client.componentcatalog.v1.api;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.BaseApi;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogDescriptor;
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
@Component("com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogDescriptorsApi")
public class CatalogDescriptorsApi extends BaseApi {

    public CatalogDescriptorsApi() {
        super(new ApiClient());
    }

    @Autowired
    public CatalogDescriptorsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * List of all available Catalog Descriptors.
     * Returns a list of all available Catalog Descriptors.&lt;br/&gt; 
     * <p><b>200</b> - A list of Catalog Descriptors.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>500</b> - Server error.
     * @return List&lt;CatalogDescriptor&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CatalogDescriptor> getCatalogDescriptors() throws RestClientException {
        return getCatalogDescriptorsWithHttpInfo().getBody();
    }

    /**
     * List of all available Catalog Descriptors.
     * Returns a list of all available Catalog Descriptors.&lt;br/&gt; 
     * <p><b>200</b> - A list of Catalog Descriptors.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>500</b> - Server error.
     * @return ResponseEntity&lt;List&lt;CatalogDescriptor&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CatalogDescriptor>> getCatalogDescriptorsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<List<CatalogDescriptor>> localReturnType = new ParameterizedTypeReference<List<CatalogDescriptor>>() {};
        return apiClient.invokeAPI("/catalog-descriptors", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
