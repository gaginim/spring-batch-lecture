package com.msa.rental.rental.test;

import com.msa.rental.rental.annotation.FindSlowTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.RegisterExtension;

// @TestInstance(Lifecycle.PER_CLASS) // 클래스 단위의 생성주기. 상태 공유.
// https://awayday.github.io/2017-11-12/junit5-05/
@TestMethodOrder(OrderAnnotation.class)
// @ExtendWith(FindSlowTestExtension.class)
public class StudyTest3 {
  private int value = 0;

  @BeforeAll
  void init() {
    value = 1;
  }

  @RegisterExtension
  static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

  @Test
  @Order(1)
  //  @SlowTest
  void create_study1() throws InterruptedException {
    Thread.sleep(1500L);
    Study study = new Study(value++);
    System.out.println("value => " + study.getLimit());
  }

  @Test
  @Order(2)
  void create_study2() {
    Study study = new Study(value++);
    System.out.println("value => " + study.getLimit());
  }
}
