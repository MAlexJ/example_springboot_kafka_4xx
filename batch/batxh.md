```
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
```

### You are mixing single-record and batch listener APIs

Your method signature:

```
Event event,
List<Integer> partitions,
List<String> topics,
List<Long> offsets
```

This only works if:

`spring.kafka.listener.type: batch`

But you did not enable batch mode, so Spring will fail with:

`MethodArgumentResolutionException`