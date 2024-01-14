package com.msa.rental.domain.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
public class Item implements Serializable {

  private Integer no;
  private String title;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  public Item(Integer no, String title) {
    this.no = no;
    this.title = title;
  }

  public static Item create(Integer no, String title) {
    return Item.builder().no(no).title(title).build();
  }
}
