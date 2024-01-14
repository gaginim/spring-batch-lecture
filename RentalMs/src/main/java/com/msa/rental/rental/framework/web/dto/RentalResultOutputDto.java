package com.msa.rental.rental.framework.web.dto;

import com.msa.rental.rental.domain.model.RentalCard;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// tommy test
@Getter
@RequiredArgsConstructor
public class RentalResultOutputDto {

  private String userId;
  private String userName;
  private Integer rentedCount;
  private long totalLateFee;

  @Builder(access = AccessLevel.PRIVATE)
  private RentalResultOutputDto(
      String userId, String userName, Integer rentedCount, long totalLateFee) {
    this.userId = userId;
    this.userName = userName;
    this.rentedCount = rentedCount;
    this.totalLateFee = totalLateFee;
  }

  public static RentalResultOutputDto mapToDto(RentalCard card) {
    return RentalResultOutputDto.builder()
        .userId(card.getMember().getId())
        .userName(card.getMember().getName())
        .rentedCount(card.getRentalItems().size())
        .totalLateFee(card.getLateFee().getPoint())
        .build();
  }
}
