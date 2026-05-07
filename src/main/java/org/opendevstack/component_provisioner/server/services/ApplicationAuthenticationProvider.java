package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationAuthenticationProvider {

    private final AzureAdTokenService azureAdTokenService;

    private final String clientId;
    private final String clientSecret;
    private final String scope;

    public ApplicationAuthenticationProvider(AzureAdTokenService azureAdTokenService,
                                             @Value("${component-provisioner.ods-api-service.params.client_id}") String clientId,
                                             @Value("${component-provisioner.ods-api-service.params.client_secret}") String clientSecret,
                                             @Value("${component-provisioner.ods-api-service.params.scope}") String scope) {
        this.azureAdTokenService = azureAdTokenService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    public String getAccessToken() {
        log.debug("Generating Auth token for application");

        var accessToken = azureAdTokenService.getAccessToken(clientId, clientSecret, scope);

        log.debug("Extracted accessToken: {} from request.", accessToken);

        return accessToken;
    }

}
