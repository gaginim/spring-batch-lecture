package com.msa.rental.rental.domain.model;

import com.msa.rental.rental.domain.model.vo.RentalItem;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class ReturnItem {

  @Embedded private RentalItem rentalItem;
  private LocalDateTime returnDate;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  private ReturnItem(RentalItem rentalItem, LocalDateTime returnDate) {
    this.rentalItem = rentalItem;
    this.returnDate = returnDate;
  }

  public static ReturnItem of(RentalItem item) {
    return ReturnItem.builder().rentalItem(item).returnDate(LocalDateTime.now()).build();
  }
}
