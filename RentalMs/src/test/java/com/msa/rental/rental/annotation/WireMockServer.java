package com.msa.rental.rental.annotation;

import java.lang.annotation.*;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigureWireMock(port = 0)
public @interface WireMockServer {}
