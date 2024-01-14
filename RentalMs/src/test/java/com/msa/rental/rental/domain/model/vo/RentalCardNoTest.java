package com.msa.rental.rental.domain.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RentalCardNoTest {

  @Test
  void rental_card_no_create() {
    RentalCardNo cardNo = RentalCardNo.of();

    System.out.println("cardNo => " + cardNo.getNo());

    Assertions.assertAll(
        () -> Assertions.assertNotNull(cardNo),
        () -> Assertions.assertNotNull(cardNo.getNo()),
        () -> Assertions.assertTrue(cardNo.getNo().indexOf("^") > 0));
  }
}
