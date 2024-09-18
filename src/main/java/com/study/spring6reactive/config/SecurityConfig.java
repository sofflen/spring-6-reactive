package com.study.spring6reactive.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    @Order(2)
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(security ->
                        security.anyExchange().authenticated())
                .oauth2ResourceServer(server ->
                        server.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    @Order(1)
    SecurityWebFilterChain actuatorSecurityWebFilterChain(ServerHttpSecurity http) {
        http
                .securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeExchange(authorize -> authorize.anyExchange().permitAll());

        return http.build();
    }
}
