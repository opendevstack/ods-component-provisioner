package org.opendevstack.component_provisioner.server.services;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationProviderTest {

    private AuthenticationProvider authenticationProvider;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserPrincipal userPrincipal;

    @BeforeEach
    void setUp() {
        authenticationProvider = new AuthenticationProvider();
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void givenAuthenticated_whenGetAccessToken_thenReturnsBearerToken() {
        // given
        var expectedBearerToken = "test-bearer-token";
        var userName = "test-user";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(userName);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getAadIssuedBearerToken()).thenReturn(expectedBearerToken);

        // when
        var actualBearerToken = authenticationProvider.getAccessToken();

        // then
        assertThat(actualBearerToken).isEqualTo(expectedBearerToken);
    }

    @Test
    void givenAuthenticated_whenGetUserPrincipalName_thenReturnsPrincipalName() {
        // given
        var expectedUserPrincipalName = "user@example.com";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        when(userPrincipal.getUserPrincipalName()).thenReturn(expectedUserPrincipalName);

        // when
        var actualUserPrincipalName = authenticationProvider.getUserPrincipalName();

        // then
        assertThat(actualUserPrincipalName).isEqualTo(expectedUserPrincipalName);
    }
}
