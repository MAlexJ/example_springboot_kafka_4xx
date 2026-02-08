package com.malex.configuration.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.producer")
public record KafkaProducerProperties(String topic) {}
