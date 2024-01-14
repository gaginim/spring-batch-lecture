// package com.msa.rental.rental.config;
//
// import io.swagger.v3.oas.models.info.Info;
// import org.springdoc.core.models.GroupedOpenApi;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class SwaggerConfig {
//
//  @Bean
//  public GroupedOpenApi chatOpenApi() {
//
//    return GroupedOpenApi.builder()
//        .group("rentalcard")
//        .packagesToScan("com.msa.rental.rental.framework.web")
//        .pathsToMatch("/rentalcard/**")
//        .addOpenApiCustomizer((api) -> api.info(new Info().title("rental card example")))
//        .build();
//  }
// }
