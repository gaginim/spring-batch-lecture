package com.msa.rental.rental.domain.model;

import com.msa.rental.rental.domain.model.vo.IDName;
import com.msa.rental.rental.domain.model.vo.Item;
import com.msa.rental.rental.domain.model.vo.LateFee;
import com.msa.rental.rental.domain.model.vo.RentStatus;
import com.msa.rental.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.rental.domain.model.vo.RentalItem;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class RentalCard {

  @EmbeddedId private RentalCardNo rentalCardNo;

  @Embedded private IDName member;

  private RentStatus status;

  @Embedded private LateFee lateFee;

  @ElementCollection private List<RentalItem> rentalItems;
  @ElementCollection private List<ReturnItem> returnItems;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  public RentalCard(
      RentalCardNo rentalCardNo,
      IDName member,
      RentStatus status,
      LateFee lateFee,
      List<RentalItem> rentalItems,
      List<ReturnItem> returnItems) {
    this.rentalCardNo = rentalCardNo;
    this.member = member;
    this.status = status;
    this.lateFee = lateFee;
    this.rentalItems = rentalItems;
    this.returnItems = returnItems;
  }

  public static RentalCard of(IDName member) {
    return RentalCard.builder()
        .rentalCardNo(RentalCardNo.of())
        .member(member)
        .status(RentStatus.AVAILABLE)
        .lateFee(LateFee.of(0L))
        .rentalItems(new ArrayList<>())
        .returnItems(new ArrayList<>())
        .build();
  }

  public RentalCard rentalItem(Item item) {

    if (RentStatus.UNAVAILABLE.equals(status)) throw new IllegalArgumentException("대여 불가능한 상태입니다.");
    if (rentalItems.size() >= 5) throw new IllegalArgumentException("5권 이상 대여할 수 없습니다.");

    this.addRentalItem(RentalItem.of(item));
    return this;
  }

  public RentalCard returnItem(Item item, LocalDateTime returnDate) {

    Optional<RentalItem> rentalItem =
        this.rentalItems.stream().filter(it -> item.equals(it.getItem())).findFirst();
    if (rentalItem.isEmpty()) throw new IllegalArgumentException("대여도서 목록에 없으므로 반납할 수 없습니다.");

    if (returnDate.isAfter(rentalItem.get().getOverdueDate())) {
      this.lateFee.add(
          Period.between(rentalItem.get().getOverdueDate().toLocalDate(), returnDate.toLocalDate())
                  .getDays()
              * 10L);
    }

    this.removeRentalItem(RentalItem.of(item));
    this.addReturnItem(ReturnItem.of(RentalItem.of(item)));
    return this;
  }

  public RentalCard overdueItem(Item item) {
    Optional<RentalItem> rentalItem =
        this.rentalItems.stream().filter(it -> item.equals(it.getItem())).findFirst();
    if (rentalItem.isEmpty()) throw new IllegalArgumentException("대여도서 목록에 없으므로 반납할 수 없습니다.");

    if (RentStatus.AVAILABLE.equals(this.status)) {
      rentalItem.get().overDued();
      this.status = RentStatus.UNAVAILABLE;
    }
    return this;
  }

  public Long changeRentalAvailable(long point) {

    if (!this.rentalItems.isEmpty()) throw new IllegalArgumentException("모든 도서가 반납되어야 정지 해제가 됩니다.");
    if (!this.lateFee.getPoint().equals(point))
      throw new IllegalArgumentException("해당 포인트로 연체를 해제할 수 없습니다.");

    this.lateFee = this.lateFee.remove(point);
    if (this.lateFee.getPoint() == 0L) {
      this.status = RentStatus.AVAILABLE;
    }
    return this.lateFee.getPoint();
  }

  public void addRentalItem(RentalItem item) {
    this.rentalItems.add(item);
  }

  public void removeRentalItem(RentalItem item) {
    this.rentalItems.remove(
        this.rentalItems.stream()
            .filter(it -> it.getItem().getNo().equals(item.getItem().getNo()))
            .findFirst()
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "cannot find rental " + item.getItem().getTitle() + "item")));
  }

  public void addReturnItem(ReturnItem item) {
    this.returnItems.add(item);
  }

  public void removeReturnItem(ReturnItem item) {
    this.returnItems.remove(
        this.returnItems.stream()
            .filter(
                it ->
                    it.getRentalItem()
                        .getItem()
                        .getNo()
                        .equals(item.getRentalItem().getItem().getNo()))
            .findFirst()
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "cannot find rental "
                            + item.getRentalItem().getItem().getTitle()
                            + "item")));
  }
}
