# Experiencing-kafka
![CI Status](https://github.com/guidou44/experiencing-kafka/workflows/experiencing-kafka%20CI/badge.svg?branch=main)
Small single page application to test interractions and integration with the famous message broker
[Kafka](https://kafka.apache.org/).

The backend of the application runs a background thread that produce messages to the broker. The message are simply a randomized IP address in the address range of the local host with the ping time and wether it is reachable or not.

Typical message:
```json
NetworkScan {
    ipAddress: string,
    pingTimeMilli: number,
    isReachable: bool,
    brokerPartitionId: number
}
```
These messages go to the ``network-scan`` topic. There is also an ``error-scan`` topic which contains all IP addresses that caused an error. This topic also contains purposely caused exceptions, just to test the system. These exceptions are of type``VolunteerChaosException``.

Also, just to have at least some ip addresses that are reachable (in order to test the frontend properly), one out of two address is marked as 'reachable'.

## Backend
[Spring-boot](https://spring.io/projects/spring-boot) backend that uses the simplicity of
[spring-kafka](https://spring.io/projects/spring-kafka) to connect to the message broker.

## Frontend
Angular frontend. This is a single page applicaiton so nothing fancy and pretty self explanatory.

## Message broker
This app uses a single kafka broker with one zookeeper instance. This is not for any production environment so the idea was that a single broker is enough for the use case.
Kafka setup with docker was accomplished thanks to this [blog](https://medium.com/big-data-engineering/hello-kafka-world-the-complete-guide-to-kafka-with-docker-and-python-f788e2588cfc).

## Deployment
Everything runs in a docker container. Just run the following command from project root:
``docker-compose up -d``
