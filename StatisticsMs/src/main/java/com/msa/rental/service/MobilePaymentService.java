package com.msa.rental.service;

import com.msa.rental.enums.PaidKind;
import com.msa.rental.service.common.AbstractPrePaymentService;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class MobilePaymentService extends AbstractPrePaymentService {
  private final PaidKind kind = PaidKind.MOBILE;

  @Override
  public BigDecimal calculate() {
    System.out.println("current paid two");
    return BigDecimal.TWO;
  }
}
