package com.malex.configuration.support;

import java.time.Clock;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProducerSupportConfiguration {

  @Bean
  Faker faker() {
    return new Faker();
  }

  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}
