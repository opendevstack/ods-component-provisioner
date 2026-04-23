package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import org.opendevstack.component_provisioner.server.services.model.AzureTokenResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AzureAdTokenService {

    private final RestTemplate restTemplate;

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

        ResponseEntity<AzureTokenResponse> response =
                restTemplate.postForEntity(
                        "https://login.microsoftonline.com/e1f8af86-ee95-4718-bd0d-375b37366c83/oauth2/v2.0/token",
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
