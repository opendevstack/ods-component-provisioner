package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationAuthenticationProviderTest {

    @Mock
    private AzureAdTokenService azureAdTokenService;

    private ApplicationAuthenticationProvider applicationAuthenticationProvider;

    @BeforeEach
    void setUp() {
        applicationAuthenticationProvider = new ApplicationAuthenticationProvider(
                azureAdTokenService,
                "testClientId",
                "testClientSecret",
                "testScope"
        );
    }

    @Test
    void givenValidCredentials_whenGetAccessToken_thenReturnToken() {
        // given
        var expectedToken = "expectedAccessToken";
        when(azureAdTokenService.getAccessToken("testClientId", "testClientSecret", "testScope"))
                .thenReturn(expectedToken);

        // when
        var actualToken = applicationAuthenticationProvider.getAccessToken();

        // then
        assertThat(actualToken).isEqualTo(expectedToken);
    }
}
