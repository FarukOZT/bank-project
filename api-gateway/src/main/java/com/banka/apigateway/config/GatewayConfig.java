package com.banka.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/auth-service/**").filters(f -> f.filter(filter))
                        .uri("http://localhost:8081"))
                .route("invoice", r -> r
                        .path("/invoice/**").filters(f -> f.filter(filter))
                        .uri("http://localhost:8082"))
                .route("mail-sender", r -> r
                        .path("/mail-sender/**").filters(f -> f.filter(filter))
                        .uri("http://localhost:8083"))
                .build();
    }
}
