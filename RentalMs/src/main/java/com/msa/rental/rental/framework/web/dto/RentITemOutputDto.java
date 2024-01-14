package com.msa.rental.rental.framework.web.dto;

import com.msa.rental.rental.domain.model.vo.RentalItem;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class RentITemOutputDto {
  private Integer itemId;
  private String itemTitle;
  private LocalDateTime rentDate;
  private boolean overdued;
  private LocalDateTime overdueDate;

  @Builder(access = AccessLevel.PRIVATE)
  private RentITemOutputDto(
      Integer itemId,
      String itemTitle,
      LocalDateTime rentDate,
      boolean overdued,
      LocalDateTime overdueDate) {
    this.itemId = itemId;
    this.itemTitle = itemTitle;
    this.rentDate = rentDate;
    this.overdued = overdued;
    this.overdueDate = overdueDate;
  }

  public static RentITemOutputDto mapToDto(RentalItem rentalItem) {
    return RentITemOutputDto.builder()
        .itemId(rentalItem.getItem().getNo())
        .itemTitle(rentalItem.getItem().getTitle())
        .rentDate(rentalItem.getRentDate())
        .overdued(rentalItem.isOverdue())
        .overdueDate(rentalItem.getOverdueDate())
        .build();
  }
}
