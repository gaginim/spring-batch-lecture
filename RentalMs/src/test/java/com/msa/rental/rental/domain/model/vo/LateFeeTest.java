package com.msa.rental.rental.domain.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LateFeeTest {

  @Test
  void late_fee_create() {
    Long pivotFee = 10L;

    LateFee fee = LateFee.of(100L);
    LateFee plusFee = LateFee.of(fee.getPoint()).add(pivotFee);
    LateFee minusFee = LateFee.of(fee.getPoint()).remove(pivotFee);

    System.out.println(fee.getPoint() + pivotFee + ", " + plusFee.getPoint());

    Assertions.assertAll(
        () -> Assertions.assertNotNull(fee),
        () -> Assertions.assertNotNull(plusFee),
        () -> Assertions.assertNotNull(minusFee),
        () -> Assertions.assertEquals(fee.getPoint() + pivotFee, plusFee.getPoint()),
        () -> Assertions.assertEquals(fee.getPoint() - pivotFee, minusFee.getPoint()));
  }

  @Test
  void late_fee_error() {
    LateFee fee = LateFee.of(100L);
    Assertions.assertThrows(RuntimeException.class, () -> fee.remove(110L));
  }
}
