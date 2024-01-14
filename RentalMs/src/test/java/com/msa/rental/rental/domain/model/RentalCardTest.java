package com.msa.rental.rental.domain.model;

import com.msa.rental.rental.domain.model.vo.IDName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RentalCardTest {

  @Test
  void rental_card_create() {

    RentalCard rentalCard = RentalCard.of(IDName.of("wanchub429", "hanjiyoon"));

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals(0L, rentalCard.getLateFee().getPoint()),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()));
  }
}
