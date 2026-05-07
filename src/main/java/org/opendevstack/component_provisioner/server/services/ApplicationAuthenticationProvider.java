package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationAuthenticationProvider {

    public String getAccessToken() {
        log.debug("Generating Auth token for application");

        var accessToken = "To be properly calculated";

        log.debug("Extracted accessToken: {} from request.", accessToken);

        return accessToken;
    }

}
