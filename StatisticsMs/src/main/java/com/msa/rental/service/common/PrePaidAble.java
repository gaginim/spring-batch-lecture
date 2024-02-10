package com.msa.rental.service.common;

import com.msa.rental.enums.PaidType;

public interface PrePaidAble extends CommonPaidAble {

  default String paid() {
    return "PrePaidAble";
  }

  default PaidType type() {
    return PaidType.PRE;
  }
}
