package com.malex.configuration.kafka;

import com.malex.Event;
import org.apache.kafka.common.serialization.Serializer;
import tools.jackson.databind.ObjectMapper;

/*
 * Add to application.yaml:
 *  producer:
 *     value-serializer: com.malex.configuration.kafka.KafkaCustomSerializer
 */
public class KafkaCustomSerializer implements Serializer<Event> {

  private final ObjectMapper objectMapper;

  public KafkaCustomSerializer() {
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public byte[] serialize(String topic, Event event) {
    if (event == null) {
      return new byte[] {};
    }
    return objectMapper.writeValueAsBytes(event);
  }
}
