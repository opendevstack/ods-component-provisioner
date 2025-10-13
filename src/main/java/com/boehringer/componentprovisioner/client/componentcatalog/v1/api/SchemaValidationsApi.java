package com.boehringer.componentprovisioner.client.componentcatalog.v1.api;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.ApiClient;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.BaseApi;

import java.io.File;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.ValidationResult;

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
@Component("com.boehringer.componentprovisioner.client.componentcatalog.v1.api.SchemaValidationsApi")
public class SchemaValidationsApi extends BaseApi {

    public SchemaValidationsApi() {
        super(new ApiClient());
    }

    @Autowired
    public SchemaValidationsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Validate a yaml against a proper schema.
     * Validates the provided Catalog schema against the expected format and structure.&lt;br/&gt; Returns a 200 OK response if the schema is valid, otherwise returns a 400 Bad Request with details about the validation errors. 
     * <p><b>200</b> - Validation resul.
     * <p><b>400</b> - Invalid input or validation failed
     * @param className ClassName for the uploaded file, so we can get proper schema for validation. (required)
     * @param _file  (optional)
     * @return ValidationResult
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ValidationResult validateCatalogSchema(String className, File _file) throws RestClientException {
        return validateCatalogSchemaWithHttpInfo(className, _file).getBody();
    }

    /**
     * Validate a yaml against a proper schema.
     * Validates the provided Catalog schema against the expected format and structure.&lt;br/&gt; Returns a 200 OK response if the schema is valid, otherwise returns a 400 Bad Request with details about the validation errors. 
     * <p><b>200</b> - Validation resul.
     * <p><b>400</b> - Invalid input or validation failed
     * @param className ClassName for the uploaded file, so we can get proper schema for validation. (required)
     * @param _file  (optional)
     * @return ResponseEntity&lt;ValidationResult&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ValidationResult> validateCatalogSchemaWithHttpInfo(String className, File _file) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'className' is set
        if (className == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'className' when calling validateCatalogSchema");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("className", className);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (_file != null)
            localVarFormParams.add("file", new FileSystemResource(_file));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<ValidationResult> localReturnType = new ParameterizedTypeReference<ValidationResult>() {};
        return apiClient.invokeAPI("/schema-validation/{className}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
