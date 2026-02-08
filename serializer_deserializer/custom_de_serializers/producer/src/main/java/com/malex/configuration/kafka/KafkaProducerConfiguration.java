package com.malex.configuration.kafka;

import com.malex.Event;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableConfigurationProperties({KafkaProducerProperties.class})
public class KafkaProducerConfiguration {

  @Bean
  KafkaTemplate<String, Event> kafkaTemplate(ProducerFactory<String, Event> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
}
