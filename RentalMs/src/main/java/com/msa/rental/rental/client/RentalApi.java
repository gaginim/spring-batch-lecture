package com.msa.rental.rental.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @FeignClient(name = "rental-api", fallbackFactory = RentalApiFallBack.class)
@FeignClient(name = "rental-api")
public interface RentalApi {

  @GetMapping("/test")
  @CircuitBreaker(name = "getTestCircuitBreaker", fallbackMethod = "getTestFallBack")
  String getTest(@RequestParam("name") String name);

  default String getTestFallBack(Exception exception) {
    System.out.println("circuit breaker default method");
    return "no more";
  }
}
