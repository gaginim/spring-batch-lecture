package com.msa.rental.rental.framework.web.dto;

import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.domain.model.vo.RentStatus;
import com.msa.rental.rental.domain.model.vo.RentalItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RentalCardOutputDto {

  private String rentalCardId;
  private String memberId;
  private String memberName;
  private String rentStatus;
  private Long totalLateFee;
  private Long totalRentalCount;
  private Long totalReturnCount;
  private Long totalOverDuedCount;

  @Builder(access = AccessLevel.PRIVATE)
  public RentalCardOutputDto(
      String rentalCardId,
      String memberId,
      String memberName,
      String rentStatus,
      Long totalLateFee,
      Long totalRentalCount,
      Long totalReturnCount,
      Long totalOverDuedCount) {
    this.rentalCardId = rentalCardId;
    this.memberId = memberId;
    this.memberName = memberName;
    this.rentStatus = rentStatus;
    this.totalLateFee = totalLateFee;
    this.totalRentalCount = totalRentalCount;
    this.totalReturnCount = totalReturnCount;
    this.totalOverDuedCount = totalOverDuedCount;
  }

  // 매퍼 클래스
  public static RentalCardOutputDto mapToDto(RentalCard rentalCard) {
    return RentalCardOutputDto.builder()
        .rentalCardId(rentalCard.getRentalCardNo().getNo())
        .memberId(rentalCard.getMember().getId())
        .memberName(rentalCard.getMember().getName())
        .rentStatus(RentStatus.valueOf(rentalCard.getStatus()))
        .totalLateFee(rentalCard.getLateFee().getPoint())
        .totalRentalCount(rentalCard.getRentalItems().stream().count())
        .totalReturnCount(rentalCard.getReturnItems().stream().count())
        .totalOverDuedCount(
            rentalCard.getRentalItems().stream().filter(RentalItem::isOverdue).count())
        .build();
  }
}
