package com.boehringer.componentprovisioner.client.componentcatalog.v1.api;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.BaseApi;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.FileFormat;
import org.springframework.core.io.Resource;
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
@Component("com.boehringer.componentprovisioner.client.componentcatalog.v1.api.FilesApi")
public class FilesApi extends BaseApi {

    public FilesApi() {
        super(new ApiClient());
    }

    @Autowired
    public FilesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Returns the contents of a File.
     * Returns the contents of a File entity associated to the provided id, unless: &lt;ul&gt; &lt;li&gt;The id is not associated to any File.&lt;/li&gt; &lt;li&gt;Or the associated File is invalid (e.g. corrupted) and can&#39;t be processed to create a response.&lt;/li&gt; &lt;/ul&gt; 
     * <p><b>200</b> - File contents, either in binary or text format.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - No File associated to the provided id.
     * <p><b>422</b> - Invalid File associated to the provided id.
     * <p><b>500</b> - Server error.
     * @param id id for the File. (required)
     * @param format desired format for the returned File contents, **must** match the actual format. (required)
     * @return Resource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Resource getFileById(String id, FileFormat format) throws RestClientException {
        return getFileByIdWithHttpInfo(id, format).getBody();
    }

    /**
     * Returns the contents of a File.
     * Returns the contents of a File entity associated to the provided id, unless: &lt;ul&gt; &lt;li&gt;The id is not associated to any File.&lt;/li&gt; &lt;li&gt;Or the associated File is invalid (e.g. corrupted) and can&#39;t be processed to create a response.&lt;/li&gt; &lt;/ul&gt; 
     * <p><b>200</b> - File contents, either in binary or text format.
     * <p><b>401</b> - Invalid client token on the request.
     * <p><b>403</b> - Insufficient permissions for the client to access the resource.
     * <p><b>404</b> - No File associated to the provided id.
     * <p><b>422</b> - Invalid File associated to the provided id.
     * <p><b>500</b> - Server error.
     * @param id id for the File. (required)
     * @param format desired format for the returned File contents, **must** match the actual format. (required)
     * @return ResponseEntity&lt;Resource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Resource> getFileByIdWithHttpInfo(String id, FileFormat format) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFileById");
        }
        
        // verify the required parameter 'format' is set
        if (format == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'format' when calling getFileById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "format", format));
        

        final String[] localVarAccepts = { 
            "application/octet-stream", "text/*", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Resource> localReturnType = new ParameterizedTypeReference<Resource>() {};
        return apiClient.invokeAPI("/files/{id}/contents", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "application/octet-stream", "text/*", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
