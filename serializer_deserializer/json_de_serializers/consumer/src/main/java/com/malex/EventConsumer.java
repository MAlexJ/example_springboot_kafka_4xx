package com.malex;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConsumer {

  @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
  public void consume(
      Event event, //
      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions, //
      @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, //
      @Header(KafkaHeaders.RECEIVED_KEY) String key, //
      @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

    log.info(
        "topic:{}, partition:{}, offset: {}, key: {}, message: {}",
        topics,
        partitions,
        offsets,
        key,
        event);
  }
}
