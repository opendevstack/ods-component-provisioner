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

import static org.assertj.core.api.Assertions.assertThat;
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
    void givenValidCredentials_whenGetAccessTokenIsCalled_thenReturnsToken() {
        // given
        var clientId = "test-client-id";
        var clientSecret = "test-client-secret";
        var scope = "test-scope";
        var expectedToken = "test-access-token";

        var mockResponse = new AzureTokenResponse();
        ReflectionTestUtils.setField(mockResponse, "accessToken", expectedToken);

        var responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(azureAdTokenServiceProps.getUrl())
                .thenReturn("https://login.microsoftonline.com/example-tenant/oauth2/v2.0/token");
        when(restTemplate.postForEntity(anyString(), any(), eq(AzureTokenResponse.class)))
                .thenReturn(responseEntity);

        // when
        var actualToken = azureAdTokenService.getAccessToken(clientId, clientSecret, scope);

        // then
        assertThat(actualToken).isEqualTo(expectedToken);
    }
}
