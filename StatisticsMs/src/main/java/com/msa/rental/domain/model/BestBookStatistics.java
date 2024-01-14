package com.msa.rental.domain.model;

import java.util.UUID;
import lombok.*;

@Data
@NoArgsConstructor
public class BestBookStatistics {

  private String id;
  private Item item;
  private long rentCount;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  public BestBookStatistics(String id, Item item, long rentCount) {
    this.id = id;
    this.item = item;
    this.rentCount = rentCount;
  }

  public static BestBookStatistics register(Item item) {
    return BestBookStatistics.builder()
        .id(UUID.randomUUID().toString())
        .item(item)
        .rentCount(1L)
        .build();
  }

  public Long increase() {
    return this.rentCount++;
  }
}
