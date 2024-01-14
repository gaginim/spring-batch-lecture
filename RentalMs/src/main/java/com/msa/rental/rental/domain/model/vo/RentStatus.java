package com.msa.rental.rental.domain.model.vo;

import java.util.Arrays;

public enum RentStatus {
  AVAILABLE("대여가능"),
  UNAVAILABLE("대여불가능");

  private String description;

  RentStatus(String description) {
    this.description = description;
  }

  public static String valueOf(RentStatus rentStatus) {
    return Arrays.stream(RentStatus.values())
        .filter(it -> it.name().equals(rentStatus.name()))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("no data"))
        .description;
  }
}
