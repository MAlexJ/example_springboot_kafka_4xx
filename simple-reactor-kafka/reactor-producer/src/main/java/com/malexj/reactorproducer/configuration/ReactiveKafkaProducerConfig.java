package com.malexj.reactorproducer.configuration;

import com.malexj.reactorproducer.properties.KafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

// import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

@Configuration
@RequiredArgsConstructor
public class ReactiveKafkaProducerConfig {

  private final KafkaConfigProperties kafkaConfigProperties;

  //  @Bean
  //  public ReactiveKafkaProducerTemplate<String, Message> reactiveKafkaProducer() {
  //    Map<String, Object> props = new HashMap<>();
  //    props.put(
  //        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getBootstrapServerUrl());
  //    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);
  //
  //    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
  //    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
  //    props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
  //
  //    /*
  //     * Configuration SASL_SSL connection: <a
  //     *
  // href="https://stackoverflow.com/questions/60825373/spring-kafka-application-properties-configuration-for-jaas-sasl-not-working">JAAS/SASL</a>
  //     */
  //    props.put(
  //        CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
  //        kafkaConfigProperties.getSecuritySaslProtocol());
  //    props.put(SaslConfigs.SASL_MECHANISM, kafkaConfigProperties.getSecuritySaslMechanism());
  //    props.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaConfigProperties.getSecuritySaslJaasConfig());
  //
  //    return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(props));
  //  }
}
