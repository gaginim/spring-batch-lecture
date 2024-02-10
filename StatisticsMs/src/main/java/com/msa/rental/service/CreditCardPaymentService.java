package com.msa.rental.service;

import com.msa.rental.enums.PaidKind;
import com.msa.rental.service.common.AbstractDefaultService;
import com.msa.rental.service.common.PrePaidAble;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class CreditCardPaymentService extends AbstractDefaultService implements PrePaidAble {
  private final PaidKind kind = PaidKind.CREDIT_CARD;

  @Override
  public BigDecimal calculate() {
    System.out.println("current paid ten");
    return BigDecimal.TEN;
  }
}
