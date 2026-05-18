package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.model.AzureTokenResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class AzureAdTokenService {

    private final RestTemplate restTemplate;
    private final ApplicationPropertiesConfiguration.AzureAdTokenServiceProps azureAdTokenServiceProps;

    public String getAccessToken(String clientId, String clientSecret, String scope) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("scope", scope);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        var tokenRestUrl = azureAdTokenServiceProps.getUrl();

        log.debug("Requesting Azure AD access token from URL: {} and body: {}", tokenRestUrl, body);

        ResponseEntity<AzureTokenResponse> response =
                restTemplate.postForEntity(
                        tokenRestUrl,
                        request,
                        AzureTokenResponse.class
                );

        if (!response.getStatusCode().is2xxSuccessful() ||
                response.getBody() == null) {
            throw new IllegalStateException("Failed to obtain Azure AD access token");
        }

        return response.getBody().getAccessToken();
    }
}
