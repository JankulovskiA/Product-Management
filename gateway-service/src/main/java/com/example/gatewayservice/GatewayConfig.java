package com.example.gatewayservice;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/api/order/**")
                        .uri("lb://ORDER-MANAGEMENT"))
                .route(r->r.path("/api/product/**")
                        .uri("lb://PRODUCT-INVENTORY"))
                .build();
    }
}
