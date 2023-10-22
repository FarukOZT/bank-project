package com.banka.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/auth-service/**")
                        .uri("http://localhost:8081"))
                .route("invoice", r -> r
                        .path("/bill-payment-service/**")
                        .uri("http://localhost:8082"))
                .route("mail-sender", r -> r
                        .path("/mail-sender/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
