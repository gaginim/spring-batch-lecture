package com.msa.rental.rental.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

  @Id @GeneratedValue private Long id;
  private String name;

  public Member(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
