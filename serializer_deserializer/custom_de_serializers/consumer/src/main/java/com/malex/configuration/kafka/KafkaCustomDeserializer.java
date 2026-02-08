package com.malex.configuration.kafka;

import com.malex.Event;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

/*
 * Add to application.yaml:
 *  producer:
 *     value-deserializer: com.malex.configuration.kafka.KafkaCustomDeserializer
 */
public class KafkaCustomDeserializer implements Deserializer<Event> {

  private final ObjectMapper objectMapper;

  public KafkaCustomDeserializer() {
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public Event deserialize(String topic, byte[] data) {
    if (data == null) {
      return null;
    }
    return objectMapper.readValue(data, Event.class);
  }
}
