package org.opendevstack.component_provisioner.server.services;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationProvider {
    public String getAccessToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.debug("Authenticated user '{}'", auth.getName());

        var principal = (UserPrincipal) auth.getPrincipal();

        var accessToken = principal.getAadIssuedBearerToken();

        log.debug("Extracted accessToken: {} from request.", accessToken);

        return accessToken;
    }

    public String getUserPrincipalName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal authUserPrincipal = (UserPrincipal) auth.getPrincipal();
        var userPrincipalName = authUserPrincipal.getUserPrincipalName();

        log.debug("Authenticated user '{}'", userPrincipalName);

        return userPrincipalName;
    }
}
