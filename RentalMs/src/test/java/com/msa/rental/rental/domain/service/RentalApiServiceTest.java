package com.msa.rental.rental.domain.service;

import com.msa.rental.rental.annotation.WireMockServer;
import com.msa.rental.rental.framework.web.test.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockServer
class RentalApiServiceTest {

  @Autowired private TestService testService;

  @Test
  void validOpenfeign() {
    String name = "mone";
    String result = testService.valivdOpenFeign(name);
    Assertions.assertEquals("Love, mone", result);
  }
}
