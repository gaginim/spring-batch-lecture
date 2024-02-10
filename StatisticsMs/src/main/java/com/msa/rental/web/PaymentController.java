package com.msa.rental.web;

import com.msa.rental.enums.PaidKind;
import com.msa.rental.enums.PaidType;
import com.msa.rental.service.common.AbstractBasicService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

  private final List<AbstractBasicService> paidServices;

  @PostMapping("/pre-payments")
  public ResponseEntity<String> findByPrePayments() {
    paidServices.stream()
        .filter(it -> it.type().equals(PaidType.PRE))
        .forEachOrdered(it -> System.out.println(it.calculate()));
    return new ResponseEntity<>("OK", HttpStatus.CREATED);
  }

  @PostMapping("/credit-card/info")
  public ResponseEntity<String> findByCreditCardDetail() {
    paidServices.stream()
        .filter(
            it ->
                it.type().equals(PaidType.PRE)
                    && it.getKind().equals(PaidKind.CREDIT_CARD)
                    && "CreditCardPaymentService".equals(it.getClass().getSimpleName()))
        .forEachOrdered(it -> System.out.println(it.calculate()));
    return new ResponseEntity<>("OK", HttpStatus.CREATED);
  }
}
