package org.opendevstack.component_provisioner.config;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.AadAppRoleStatelessAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.opendevstack.component_provisioner.config.azure.ConditionalAadFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.PathPatternRequestMatcherBuilderFactoryBean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {
    public static final String V_1_PROVISION = "/v1/provision/**";
    private final AadAppRoleStatelessAuthenticationFilter aadAuthFilter;

    /**
     * Builder that makes PathPatternRequestMatcher use MVC's PathPatternParser and servlet path.
     * Spring Security will discover this bean and apply it to request matching.
     */
    @Bean
    @Primary
    PathPatternRequestMatcherBuilderFactoryBean pathPatternRequestMatcherBuilderFactoryBean() {
        return new PathPatternRequestMatcherBuilderFactoryBean();
    }

    /**
     * Chain #1: ONLY for DELETE /v1/provision/* -> HTTP Basic with role PROVISIONER
     */
    @Bean
    @Order(1)
    SecurityFilterChain basicForProvisionDelete(HttpSecurity http) throws Exception {
        PathPatternRequestMatcher provisionDelete =
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.DELETE, V_1_PROVISION);
        PathPatternRequestMatcher provisionPut =
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.PUT, V_1_PROVISION);

        var basicEntryPoint = new BasicAuthenticationEntryPoint();
        basicEntryPoint.setRealmName("provision");

        http
                .securityMatchers( requestMatcherConfigurer ->
                        requestMatcherConfigurer.requestMatchers(provisionDelete, provisionPut)
                )
                .authorizeHttpRequests(auth -> auth.anyRequest().hasRole("PROVISIONER"))
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                // Important: don't add the AAD filter here
                .exceptionHandling(e -> e.authenticationEntryPoint(basicEntryPoint));

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain aadForEverythingElse(HttpSecurity http) throws Exception {
        RequestMatcher protectedEndpoints = new OrRequestMatcher(
                PathPatternRequestMatcher.withDefaults().matcher("/v1/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/actuator/**")
        );

        RequestMatcher whitelistedEndpoints = new OrRequestMatcher(
                PathPatternRequestMatcher.withDefaults().matcher("/api-docs/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/v3/api-docs/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/actuator/health"),
                PathPatternRequestMatcher.withDefaults().matcher("/actuator/mappings"),
                PathPatternRequestMatcher.withDefaults().matcher("/v1/message-definitions/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/v1/catalog-items/*/message-definitions/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/v1/catalog-items/*/user-actions/*/message-definitions/**")
        );


        http
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                whitelistedEndpoints
                        ).permitAll()
                        .anyRequest().hasAuthority("ROLE_USER")
                )
                .csrf(CsrfConfigurer::disable) //NOSONAR required for /actuator endpoints, STATELESS prevents CSRF
                .cors(c -> c.configurationSource(request ->
                        new CorsConfiguration().applyPermitDefaultValues())) //NOSONAR required for CORS support for browser requests
                .sessionManagement(configurer ->
                        // Avoid session caching and validation e.g. via JSESSIONID cookie, as we are stateless
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(
                        new ConditionalAadFilter(aadAuthFilter, protectedEndpoints, whitelistedEndpoints),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}