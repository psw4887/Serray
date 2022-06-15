package com.nhnacademy.serrayrouter.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRouteConfig {

    @Bean
    public RouteLocator accountRoutes(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
            .route(p->p.path("/users/**")
                .uri("http://localhost:5050"))
            .route(p->p.path("/projects/**")
                .uri("http://localhost:9090"))
            .build();
    }
}
