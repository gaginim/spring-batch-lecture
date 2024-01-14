package com.msa.rental.rental.framework.web.dto;

import com.msa.rental.rental.domain.model.ReturnItem;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class ReturnItemOutputDto {

  private Integer itemId;
  private String itemTitle;
  private LocalDateTime returnDate;

  @Builder(access = AccessLevel.PRIVATE)
  private ReturnItemOutputDto(Integer itemId, String itemTitle, LocalDateTime returnDate) {
    this.itemId = itemId;
    this.itemTitle = itemTitle;
    this.returnDate = returnDate;
  }

  public static ReturnItemOutputDto mapToDto(ReturnItem rentalItem) {
    return ReturnItemOutputDto.builder()
        .itemId(rentalItem.getRentalItem().getItem().getNo())
        .itemTitle(rentalItem.getRentalItem().getItem().getTitle())
        .returnDate(rentalItem.getRentalItem().getRentDate())
        .build();
  }
}
