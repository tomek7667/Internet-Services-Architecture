package com.tomaszdabrowski.lab3;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class Lab3Application {

  public static void main(String[] args) {
    SpringApplication.run(Lab3Application.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    String backendElementUrl = "http://localhost:8082";
    String backendCategoryUrl = "http://localhost:8083";

    return builder
      .routes()
      .route(
        "categories",
        r ->
          r
            .host("localhost:8081")
            .and()
            .path("/api/v1/categories/*/tasks")
            .uri(backendCategoryUrl)
      )
      .route(
        "categories",
        r ->
          r
            .host("localhost:8081")
            .and()
            .path("/api/v1/categories/**")
            .uri(backendElementUrl)
      )
      .route(
        "tasks",
        r ->
          r
            .host("localhost:8081")
            .and()
            .path("/api/v1/tasks/**")
            .uri(backendCategoryUrl)
      )
      .build();
  }

  @Bean
  public CorsWebFilter corsWebFilter() {
    final CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(Collections.singletonList("*"));
    corsConfig.setMaxAge(3600L);
    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
    corsConfig.addAllowedHeader("*");

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

    return new CorsWebFilter(source);
  }
}
