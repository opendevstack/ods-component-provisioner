package org.opendevstack.component_provisioner.server.controllers.interceptors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestTemplateLoggingInterceptorTest {

    @InjectMocks
    private RestTemplateLoggingInterceptor interceptor;

    @Mock
    private ClientHttpRequestExecution execution;

    @Test
    void givenRequestAndBody_whenIntercept_thenLogsAndExecutes() throws IOException {
        // given
        HttpRequest request = mock(HttpRequest.class);
        byte[] body = "test body".getBytes(StandardCharsets.UTF_8);
        ClientHttpResponse response = mock(ClientHttpResponse.class);

        when(request.getURI()).thenReturn(URI.create("http://localhost"));
        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(execution.execute(request, body)).thenReturn(response);

        // when
        ClientHttpResponse result = interceptor.intercept(request, body, execution);

        // then
        assertThat(result).isEqualTo(response);
    }

    @Test
    void givenRequestAndEmptyBody_whenIntercept_thenLogsAndExecutes() throws IOException {
        // given
        HttpRequest request = mock(HttpRequest.class);
        byte[] body = new byte[0];
        ClientHttpResponse response = mock(ClientHttpResponse.class);

        when(request.getURI()).thenReturn(URI.create("http://localhost"));
        when(request.getMethod()).thenReturn(HttpMethod.POST);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        when(request.getHeaders()).thenReturn(headers);
        when(execution.execute(request, body)).thenReturn(response);

        // when
        ClientHttpResponse result = interceptor.intercept(request, body, execution);

        // then
        assertThat(result).isEqualTo(response);
    }
}
