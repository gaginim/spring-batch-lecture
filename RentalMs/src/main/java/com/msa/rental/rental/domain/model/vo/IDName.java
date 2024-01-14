package com.msa.rental.rental.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class IDName {
  private String id;
  private String name;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  private IDName(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public static IDName of(String id, String name) {
    return IDName.builder().id(id).name(name).build();
  }
}
