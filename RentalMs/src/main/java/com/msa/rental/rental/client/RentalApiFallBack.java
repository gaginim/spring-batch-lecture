package com.msa.rental.rental.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RentalApiFallBack implements FallbackFactory<RentalApi> {

  @Override
  public RentalApi create(Throwable cause) {
    System.out.println("error cause => " + cause);
    return name -> "no data";
  }
}
