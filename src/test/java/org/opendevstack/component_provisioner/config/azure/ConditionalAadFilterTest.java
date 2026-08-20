package org.opendevstack.component_provisioner.config.azure;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.AadAppRoleStatelessAuthenticationFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConditionalAadFilterTest {

    private AadAppRoleStatelessAuthenticationFilter delegate;
    private RequestMatcher protectedEndpoints;
    private RequestMatcher whitelistedEndpoints;

    private ConditionalAadFilter filter;

    @BeforeEach
    void setup() {
        delegate = mock(AadAppRoleStatelessAuthenticationFilter.class);
        protectedEndpoints = mock(RequestMatcher.class);
        whitelistedEndpoints = mock(RequestMatcher.class);

        filter = new ConditionalAadFilter(delegate, protectedEndpoints, whitelistedEndpoints);
    }

    @Test
    void givenWhitelistedPath_whenShouldNotFilter_thenTrue() {
        // given
        var request = mock(HttpServletRequest.class);
        when(whitelistedEndpoints.matches(request)).thenReturn(true);

        // when
        var result = filter.shouldNotFilter(request);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenProtectedAndNotWhitelisted_whenShouldNotFilter_thenFalse() {
        // given
        var request = mock(HttpServletRequest.class);
        when(whitelistedEndpoints.matches(request)).thenReturn(false);
        when(protectedEndpoints.matches(request)).thenReturn(true);

        // when
        var result = filter.shouldNotFilter(request);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenNotProtectedAndNotWhitelisted_whenShouldNotFilter_thenTrue() {
        // given
        var request = mock(HttpServletRequest.class);
        when(whitelistedEndpoints.matches(request)).thenReturn(false);
        when(protectedEndpoints.matches(request)).thenReturn(false);

        // when
        var result = filter.shouldNotFilter(request);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenAnyRequest_whenDoFilterInternal_thenDelegateIsCalled() throws Exception {
        // given
        var request = mock(HttpServletRequest.class);
        var response = mock(HttpServletResponse.class);
        var filterChain = mock(FilterChain.class);

        // when
        filter.doFilterInternal(request, response, filterChain);

        // then
        verify(delegate).doFilter(request, response, filterChain);
    }
}
