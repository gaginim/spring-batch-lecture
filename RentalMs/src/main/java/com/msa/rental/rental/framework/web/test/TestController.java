package com.msa.rental.rental.framework.web.test;

import com.msa.rental.rental.client.RentalApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  private final RentalApi rentalApi;

  @GetMapping
  public String sendYourName(@RequestParam String name) {
    return "Thanks, " + name;
  }

  @GetMapping("/{name}/openfeign")
  public String validOpenFeign(@PathVariable String name) {
    return rentalApi.getTest(name);
  }

  @GetMapping("/thread-name")
  public String getThradName() {
    return Thread.currentThread().toString();
  }
}
