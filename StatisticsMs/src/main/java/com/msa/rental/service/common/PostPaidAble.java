package com.msa.rental.service.common;

import com.msa.rental.enums.PaidType;

public interface PostPaidAble extends CommonPaidAble {

  default String paid() {
    return "PostPaidAble";
  }

  default PaidType type() {
    return PaidType.POST;
  }
}
