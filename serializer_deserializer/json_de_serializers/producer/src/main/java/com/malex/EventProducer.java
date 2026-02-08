package com.malex;

import com.malex.configuration.kafka.KafkaProducerProperties;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, Event> kafkaTemplate;
    private final KafkaProducerProperties properties;
    private final Faker faker;
    private final Clock clock;

    @Scheduled(cron = "${kafka.producer.cron:*/15 * * * * *}")
    public void send() {
        Event event = createEvent();

        log.info("Sending event: {}", event);

        kafkaTemplate
                .send(properties.topic(), event.uuid(), event)
                .whenComplete(this::handleResult);
    }

    private Event createEvent() {
        return new Event(
                UUID.randomUUID().toString(),
                faker.book().title(),
                LocalDateTime.now(clock)
        );
    }

    private void handleResult(SendResult<String, Event> result, Throwable ex) {
        if (ex != null) {
            log.error("Failed to send message", ex);
            return;
        }

        var metadata = result.getRecordMetadata();
        log.info(
                "Message sent successfully [topic={}, partition={}, offset={}]",
                metadata.topic(),
                metadata.partition(),
                metadata.offset()
        );
    }
}
