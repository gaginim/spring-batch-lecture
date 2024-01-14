package com.msa.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BestBookStatisticsTest {

  @Test
  void createAndCount() {
    BestBookStatistics bestBookStatistics =
        BestBookStatistics.register(Item.create(1, "test book"));

    Assertions.assertNotNull(bestBookStatistics);
    Assertions.assertEquals(bestBookStatistics.getItem().getNo(), 1);
    Assertions.assertEquals(bestBookStatistics.getItem().getTitle(), "test book");
  }
}
