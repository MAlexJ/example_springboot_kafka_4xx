# Spring boot kafka examples

## Description

* Kafka
* Spring Boot 4.0.2
* Java version 25
* Gradle 9.3.1

## Docker

docker-compose.yml

### Add ENV properties to project/IDE or .env file

```
KAFKA_USERNAME={Default user}
KAFKA_PASSWORD={Password}
KAFKA_BROKER_URL=test.com:9094
```

##### Bash command (manual clean)

If you prefer cleaning via command line instead of Gradle:
On macOS/Linux:

```
find . -type d -name "build" -exec rm -rf {} + \
  && find . -type d -name ".gradle" -exec rm -rf {} + \
  && find . -path "*/.idea/gradle.xml" -type f -delete \
  && find . -path "*/.idea/modules.xml" -type f -delete \
  && find . -type d -path "*/.idea/modules" -exec rm -rf {} +
```

### Gradle

### Gradle Versions Plugin

Displays a report of the project dependencies that are up-to-date, exceed the latest version found, have upgrades, or
failed to be resolved, info: https://github.com/ben-manes/gradle-versions-plugin

command:

```
gradle dependencyUpdates
```

#### Gradle wrapper

The recommended way to execute any Gradle build is with the help of the Gradle Wrapper (referred to as "Wrapper")

```
./gradlew wrapper --gradle-version latest
```

#### Gradle ignore test

To skip any task from the Gradle build, we can use the -x or –exclude-task option. In this case, we’ll use “-x test” to
skip tests from the build.

To see it in action, let’s run the build command with -x option:

```
gradle build -x test
```
