package com.boehringer.componentprovisioner.client.awx.v2.api;

import com.boehringer.componentprovisioner.client.awx.v2.ApiClient;
import com.boehringer.componentprovisioner.client.awx.v2.BaseApi;


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
@Component("com.boehringer.componentprovisioner.client.awx.v2.api.SystemConfigurationApi")
public class SystemConfigurationApi extends BaseApi {

    public SystemConfigurationApi() {
        super(new ApiClient());
    }

    @Autowired
    public SystemConfigurationApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * No Description for post on /api/{version}/config/attach/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiConfigAttachCreate(String version) throws RestClientException {
        apiConfigAttachCreateWithHttpInfo(version);
    }

    /**
     * No Description for post on /api/{version}/config/attach/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiConfigAttachCreateWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConfigAttachCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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
        return apiClient.invokeAPI("/api/v2/config/attach/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the configuration containing
     * the following fields (some fields may not be visible to all users):  * &#x60;project_base_dir&#x60;: Path on the server where projects and playbooks are \\   stored. * &#x60;project_local_paths&#x60;: List of directories beneath &#x60;project_base_dir&#x60; to   use when creating/editing a manual project. * &#x60;time_zone&#x60;: The configured time zone for the server. * &#x60;license_info&#x60;: Information about the current license. * &#x60;version&#x60;: Version of Ansible Tower package installed. * &#x60;custom_virtualenvs&#x60;: Deprecated venv locations from before migration to   execution environments. Export tooling is in &#x60;awx-manage&#x60; commands. * &#x60;eula&#x60;: The current End-User License Agreement    # Install or update an existing license  (_New in Ansible Tower 2.0.0_) Make a POST request to this resource as a super user to install or update the existing license.  The license data itself can be POSTed as a normal json data structure.  (_New in Ansible Tower 2.1.1_) The POST must include a &#x60;eula_accepted&#x60; boolean element indicating acceptance of the End-User License Agreement.    # Delete an existing license  (_New in Ansible Tower 2.0.0_) Make a DELETE request to this resource as a super user to delete the existing license
     * <p><b>201</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiConfigCreate(String version) throws RestClientException {
        apiConfigCreateWithHttpInfo(version);
    }

    /**
     * Make a GET request to this resource to retrieve the configuration containing
     * the following fields (some fields may not be visible to all users):  * &#x60;project_base_dir&#x60;: Path on the server where projects and playbooks are \\   stored. * &#x60;project_local_paths&#x60;: List of directories beneath &#x60;project_base_dir&#x60; to   use when creating/editing a manual project. * &#x60;time_zone&#x60;: The configured time zone for the server. * &#x60;license_info&#x60;: Information about the current license. * &#x60;version&#x60;: Version of Ansible Tower package installed. * &#x60;custom_virtualenvs&#x60;: Deprecated venv locations from before migration to   execution environments. Export tooling is in &#x60;awx-manage&#x60; commands. * &#x60;eula&#x60;: The current End-User License Agreement    # Install or update an existing license  (_New in Ansible Tower 2.0.0_) Make a POST request to this resource as a super user to install or update the existing license.  The license data itself can be POSTed as a normal json data structure.  (_New in Ansible Tower 2.1.1_) The POST must include a &#x60;eula_accepted&#x60; boolean element indicating acceptance of the End-User License Agreement.    # Delete an existing license  (_New in Ansible Tower 2.0.0_) Make a DELETE request to this resource as a super user to delete the existing license
     * <p><b>201</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiConfigCreateWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConfigCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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
        return apiClient.invokeAPI("/api/v2/config/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to retrieve the configuration containing
     * the following fields (some fields may not be visible to all users):  * &#x60;project_base_dir&#x60;: Path on the server where projects and playbooks are \\   stored. * &#x60;project_local_paths&#x60;: List of directories beneath &#x60;project_base_dir&#x60; to   use when creating/editing a manual project. * &#x60;time_zone&#x60;: The configured time zone for the server. * &#x60;license_info&#x60;: Information about the current license. * &#x60;version&#x60;: Version of Ansible Tower package installed. * &#x60;custom_virtualenvs&#x60;: Deprecated venv locations from before migration to   execution environments. Export tooling is in &#x60;awx-manage&#x60; commands. * &#x60;eula&#x60;: The current End-User License Agreement    # Install or update an existing license  (_New in Ansible Tower 2.0.0_) Make a POST request to this resource as a super user to install or update the existing license.  The license data itself can be POSTed as a normal json data structure.  (_New in Ansible Tower 2.1.1_) The POST must include a &#x60;eula_accepted&#x60; boolean element indicating acceptance of the End-User License Agreement.    # Delete an existing license  (_New in Ansible Tower 2.0.0_) Make a DELETE request to this resource as a super user to delete the existing license
     * <p><b>204</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiConfigDelete(String version) throws RestClientException {
        apiConfigDeleteWithHttpInfo(version);
    }

    /**
     * Make a GET request to this resource to retrieve the configuration containing
     * the following fields (some fields may not be visible to all users):  * &#x60;project_base_dir&#x60;: Path on the server where projects and playbooks are \\   stored. * &#x60;project_local_paths&#x60;: List of directories beneath &#x60;project_base_dir&#x60; to   use when creating/editing a manual project. * &#x60;time_zone&#x60;: The configured time zone for the server. * &#x60;license_info&#x60;: Information about the current license. * &#x60;version&#x60;: Version of Ansible Tower package installed. * &#x60;custom_virtualenvs&#x60;: Deprecated venv locations from before migration to   execution environments. Export tooling is in &#x60;awx-manage&#x60; commands. * &#x60;eula&#x60;: The current End-User License Agreement    # Install or update an existing license  (_New in Ansible Tower 2.0.0_) Make a POST request to this resource as a super user to install or update the existing license.  The license data itself can be POSTed as a normal json data structure.  (_New in Ansible Tower 2.1.1_) The POST must include a &#x60;eula_accepted&#x60; boolean element indicating acceptance of the End-User License Agreement.    # Delete an existing license  (_New in Ansible Tower 2.0.0_) Make a DELETE request to this resource as a super user to delete the existing license
     * <p><b>204</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiConfigDeleteWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConfigDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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
        return apiClient.invokeAPI("/api/v2/config/", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Return various sitewide configuration settings
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiConfigList(String version) throws RestClientException {
        apiConfigListWithHttpInfo(version);
    }

    /**
     * Return various sitewide configuration settings
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiConfigListWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConfigList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/config/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for post on /api/{version}/config/subscriptions/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiConfigSubscriptionsCreate(String version) throws RestClientException {
        apiConfigSubscriptionsCreateWithHttpInfo(version);
    }

    /**
     * No Description for post on /api/{version}/config/subscriptions/
     * 
     * <p><b>201</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiConfigSubscriptionsCreateWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiConfigSubscriptionsCreate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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
        return apiClient.invokeAPI("/api/v2/config/subscriptions/", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Make a GET request to this resource to obtain a list all Receptor Nodes and their links.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiMeshVisualizerList(String version) throws RestClientException {
        apiMeshVisualizerListWithHttpInfo(version);
    }

    /**
     * Make a GET request to this resource to obtain a list all Receptor Nodes and their links.
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiMeshVisualizerListWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiMeshVisualizerList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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
        return apiClient.invokeAPI("/api/v2/mesh_visualizer/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Everything returned here should be considered public / insecure, as
     * this requires no auth and is intended for use by the installer process.
     * <p><b>200</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiPingList(String version) throws RestClientException {
        apiPingListWithHttpInfo(version);
    }

    /**
     * Everything returned here should be considered public / insecure, as
     * this requires no auth and is intended for use by the installer process.
     * <p><b>200</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiPingListWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiPingList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/ping/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * No Description for get on /api/{version}/schedules/zoneinfo/
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void apiSchedulesZoneinfoList(String version) throws RestClientException {
        apiSchedulesZoneinfoListWithHttpInfo(version);
    }

    /**
     * No Description for get on /api/{version}/schedules/zoneinfo/
     * 
     * <p><b>200</b> - 
     * @param version  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> apiSchedulesZoneinfoListWithHttpInfo(String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling apiSchedulesZoneinfoList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("version", version);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v2/schedules/zoneinfo/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
