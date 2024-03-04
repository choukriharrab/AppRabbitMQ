package com.example.Gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/api/v1/users/**")
                        .uri("http://localhost:8081"))
                .route(r -> r
                        .path("/api/v1/tasks/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}