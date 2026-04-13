package org.opendevstack.component_provisioner.server.services;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationProvider {
    public String getIdToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.debug("Authenticated user '{}'", auth.getName());

        var principal = (UserPrincipal) auth.getPrincipal();

        var idToken = principal.getAadIssuedBearerToken();

        log.debug("Extracted idToken: {} from request.", idToken);

        return idToken;
    }
}
