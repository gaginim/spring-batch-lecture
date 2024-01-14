package com.msa.rental.rental.utils;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

@Configuration
@EnableFeignClients(basePackages = "com.msa.rental.**.client")
public class OpenfeignConfig {

  @Bean
  Logger.Level loggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public FeignFormatterRegistrar feignFormatterRegistrar() {
    return registry -> {
      DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
      registrar.setUseIsoFormat(true);
      registrar.registerFormatters(registry);
    };
  }
}
