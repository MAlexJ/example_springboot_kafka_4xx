package com.malexj.reactorconsumer.configuration;

import com.malexj.reactorconsumer.model.Message;
import com.malexj.reactorconsumer.properties.KafkaConsumerConfigurationProperties;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
@RequiredArgsConstructor
public class ReactiveKafkaConsumerConfig {

  private final KafkaConsumerConfigurationProperties properties;

  private static final String MESSAGE_CLASS = "com.malexj.reactorconsumer.model.Message";

  @Bean
  public ReceiverOptions<String, Message> kafkaReceiverOptions() {

    Map<String, Object> config = new HashMap<>();

    //    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServerUrl());
    //    config.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getGroupId());
    //    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //
    //    // ===== Deserializers =====
    //    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    //
    //    config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
    //    config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    //    config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MESSAGE_CLASS);

    // ===== SASL / SSL =====
    config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, properties.getSecuritySaslProtocol());
    config.put(SaslConfigs.SASL_MECHANISM, properties.getSecuritySaslMechanism());
    config.put(SaslConfigs.SASL_JAAS_CONFIG, properties.getSecuritySaslJaasConfig());

    return ReceiverOptions.<String, Message>create(config)
            .subscription(Collections.singleton(properties.getTopic()));
  }

//  @Bean
//  public ReactiveKafkaConsumerTemplate<String, Message> reactiveKafkaConsumerTemplate(
//          ReceiverOptions<String, Message> kafkaReceiverOptions) {
//
//    return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
//  }
}
