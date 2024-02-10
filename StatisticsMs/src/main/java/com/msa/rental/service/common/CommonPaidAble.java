package com.msa.rental.service.common;

import com.msa.rental.enums.PaidKind;
import com.msa.rental.enums.PaidType;

public interface CommonPaidAble {

  String paid();

  PaidType type();

  PaidKind getKind();
}
