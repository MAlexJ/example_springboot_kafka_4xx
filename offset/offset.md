If you start a consumer for the first time, Kafka will decide whether it reads all past events or only new ones based on
one setting.
Let’s make it crystal clear.

When a consumer group has NO committed offsets yet (your “first run” case), Kafka uses:

`auto.offset.reset`

Defaults to:

`latest`

What that means in practice

Default behavior (latest)

`spring.kafka.consumer.auto-offset-reset: latest`

* Consumer SKIPS all existing events
* Starts reading only new messages produced after it starts

#### Read everything from the beginning (earliest)

```shell
spring:
  kafka:
    consumer:
      auto-offset-reset: earliest
```

- Consumer reads ALL events in the topic
- From offset 0 for each partition

This is what you want for:

- reprocessing
- backfills
- analytics
- debugging


#### How to force re-read everything

Option 1 — change group-id

```shell
spring.kafka.consumer.group-id: consumer-group-v2
```

Option 2 — reset offsets (production-safe way)

```shell
kafka-consumer-groups.sh \
  --bootstrap-server broker:9092 \
  --group consumer-group-id \
  --topic events-topic \
  --reset-offsets \
  --to-earliest \
  --execute
```