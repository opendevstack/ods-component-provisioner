package org.opendevstack.component_provisioner.server.controllers.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            byte @NonNull [] body,
            ClientHttpRequestExecution execution
    ) throws IOException {

        logRequest(request, body);

        return execution.execute(request, body);
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.debug("===== REST TEMPLATE REQUEST =====");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());

        log.debug("Headers     :");
        request.getHeaders().forEach((key, value) ->
                log.debug("  {}: {}", key, value)
        );

        if (body.length > 0) {
            log.debug("Request Body: {}", new String(body, StandardCharsets.UTF_8));
        } else {
            log.debug("Request Body: <empty>");
        }
    }
}
