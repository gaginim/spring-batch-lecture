package com.msa.rental.rental.domain.model.vo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class RentalCardNo implements Serializable {

  private String no;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  private RentalCardNo(String no) {
    this.no = no;
  }

  public static RentalCardNo of() {
    return RentalCardNo.builder()
        .no(String.format("%s^%s", LocalDate.now().getYear(), UUID.randomUUID()))
        .build();
  }
}
