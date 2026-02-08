### Root cause (the real blocker)

`com.malex.Event is not in the trusted packages`

From the error:

``
The class 'com.malex.Event' is not in the trusted packages:
[java.util, java.lang, com.malexj, com.malexj.*]
``
What this means

Your producer serialized an Event with this class name:

``
com.malex.Event
``

But your consumer trusts only:

``
spring.json.trusted.packages: com.malexj
spring.json.value.default.type: com.malexj.Event
``

Package mismatch → deserialization blocked (by design, for security).
Fix #1 — make producer & consumer agree on the package
You have 3 valid choices. Pick ONE.

#### Option A — fix trusted packages (most common)

````
spring:
    kafka:
    consumer:
    properties:
    spring.json.trusted.packages: com.malex
    spring.json.value.default.type: com.malex.Event
````

* Safe
* Explicit
* Recommended for prod

#### Option B — trust everything (dev only)

````
spring:
    kafka:
    consumer:
    properties:
    spring.json.trusted.packages: "*"
````

* Not recommended for production
* Useful while debugging

#### Option C — remove type headers entirely (clean microservices setup)

If producer and consumer share the same DTO, force the type:

```
spring:
    kafka:
    consumer:
        value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
    properties:
        spring.json.use.type.headers: false
        spring.json.value.default.type: com.malex.Event
```

* No class name leaks into Kafka
* Best for cross-service compatibility
* Very Kafka-native