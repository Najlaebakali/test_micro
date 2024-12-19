package com.example.gatewayservice;

import com.example.gatewayservice.configuration.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


  /*  @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/Enseignants")
                        .filters( f -> f.addRequestHeader("token","qlq"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/Chercheurs")
                        .uri("http://localhost:8082"))
                .route(r -> r.path("/Projets")
                        .uri("http://localhost:8083"))
                .build();

    }*/


    @Bean
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator
            (ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return  new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }


}
