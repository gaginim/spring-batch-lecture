package com.msa.rental.rental.framework.web.test;

import com.msa.rental.rental.client.RentalApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

  private final RentalApi rentalApi;

  public String valivdOpenFeign(String contents) {
    return rentalApi.getTest(contents);
  }
}
