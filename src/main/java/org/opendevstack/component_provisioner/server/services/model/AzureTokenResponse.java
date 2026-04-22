package org.opendevstack.component_provisioner.server.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AzureTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }
}

