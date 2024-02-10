package com.msa.rental.service.common;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractBasicService implements CommonPaidAble {

  public abstract BigDecimal calculate();
}
