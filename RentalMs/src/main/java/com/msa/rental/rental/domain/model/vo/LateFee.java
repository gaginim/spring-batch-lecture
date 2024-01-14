package com.msa.rental.rental.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class LateFee {

  private Long point;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  private LateFee(Long point) {
    this.point = point;
  }

  public static LateFee of(Long point) {
    return LateFee.builder().point(point).build();
  }

  public LateFee add(Long point) {
    this.point += point;
    return this;
  }

  public LateFee remove(Long point) {
    if (this.point < point) throw new RuntimeException("보유한 포인터가 커서 삭제할 수 없습니다.");

    this.point -= point;
    return this;
  }
}
