package com.msa.rental.rental.utils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(
    properties = {"spring.cloud.openfeign.client.config.rental-api.url=http://localhost:8888"})
public class WireMockConfig {
  private WireMockServer wireMockServer;

  @BeforeEach
  void init() {
    wireMockServer = new WireMockServer(8888);
    wireMockServer.start();

    initializeMockServer();
  }

  void initializeMockServer() {
    wireMockServer.stubFor(
        get(urlEqualTo("/test?name=mone"))
            .willReturn(
                aResponse().withHeader("Content-Type", "text/plain").withBody("Thanks, mone")));
  }

  @AfterEach
  void finish() {
    wireMockServer.stop();
  }
}
