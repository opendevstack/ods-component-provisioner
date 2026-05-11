package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.model.AzureTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AzureAdTokenServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApplicationPropertiesConfiguration.AzureAdTokenServiceProps azureAdTokenServiceProps;

    @InjectMocks
    private AzureAdTokenService azureAdTokenService;

    @Test
    void getAccessToken_shouldReturnToken_whenResponseIsSuccessful() {
        // Arrange
        String clientId = "test-client-id";
        String clientSecret = "test-client-secret";
        String scope = "test-scope";
        String expectedToken = "test-access-token";

        AzureTokenResponse mockResponse = new AzureTokenResponse();
        ReflectionTestUtils.setField(mockResponse, "accessToken", expectedToken);

        ResponseEntity<AzureTokenResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(azureAdTokenServiceProps.getTokenRestUrl()).thenReturn("https://login.microsoftonline.com/example-tenant/oauth2/v2.0/token");

        when(restTemplate.postForEntity(anyString(), any(), eq(AzureTokenResponse.class)))
                .thenReturn(responseEntity);

        // Act
        String actualToken = azureAdTokenService.getAccessToken(clientId, clientSecret, scope);

        // Assert
        assertEquals(expectedToken, actualToken);
    }
}
