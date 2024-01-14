package com.msa.rental.rental.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class Item {
  private Integer no;
  private String title;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  private Item(Integer no, String title) {
    this.no = no;
    this.title = title;
  }

  public static Item of(Integer no, String title) {
    return Item.builder().no(no).title(title).build();
  }
}
