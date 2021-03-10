package com.anass.microarchi.gatwayservice.configure;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {


    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route(r -> r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoute(ReactiveDiscoveryClient discoveryClient,
                                                       DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
    }
}
