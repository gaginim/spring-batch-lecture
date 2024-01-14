package com.msa.rental.rental.domain.model.vo;

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
public class RentalItem {

  @Embedded private Item item;
  private LocalDateTime rentDate;
  private boolean overdue;
  private LocalDateTime overdueDate;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  private RentalItem(
      Item item, LocalDateTime rentDate, boolean overdue, LocalDateTime overdueDate) {
    this.item = item;
    this.rentDate = rentDate;
    this.overdue = overdue;
    this.overdueDate = overdueDate;
  }

  public static RentalItem of(Item item) {
    return RentalItem.builder()
        .item(item)
        .rentDate(LocalDateTime.now())
        .overdue(false)
        .overdueDate(LocalDateTime.now().plusDays(14))
        .build();
  }

  public void overDued() {
    this.overdue = true;
    this.overdueDate = LocalDateTime.now().minusDays(1L);
  }
}
