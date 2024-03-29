package com.msa.rental.service;

import com.msa.rental.enums.PaidKind;
import com.msa.rental.service.common.AbstractPostPaymentService;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class VirtualAccountPaymentService extends AbstractPostPaymentService {
  private final PaidKind kind = PaidKind.VIRTUAL_ACCOUNT;

  @Override
  public BigDecimal calculate() {
    System.out.println("current paid zero");
    return BigDecimal.ZERO;
  }
}
