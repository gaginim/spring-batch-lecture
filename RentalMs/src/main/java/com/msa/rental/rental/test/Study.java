package com.msa.rental.rental.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Study {

  @Id @GeneratedValue private Long id;
  private StudyStatus status = StudyStatus.DRAFT;
  private int limitCount;
  private String name;
  private LocalDateTime openedDateTime;
  private Long ownerId;

  public Study(int limit, String name) {
    if (limit < 0) throw new IllegalArgumentException("cannot register minus value");

    this.limitCount = limit;
    this.name = name;
  }

  public Study(int limit) {
    if (limit < 0) throw new IllegalArgumentException("cannot register minus value");

    this.limitCount = limit;
  }

  public StudyStatus getStatus() {
    return StudyStatus.DRAFT;
  }

  public int getLimit() {
    return limitCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Study {status=" + status + ", limit=" + limitCount + ", name=" + name + "}";
  }
}
