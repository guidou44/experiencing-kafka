# Experiencing-kafka
![CI Status](https://github.com/guidou44/experiencing-kafka/workflows/experiencing-kafka%20CI/badge.svg?branch=main)

Small single page application to test interractions and integration with the famous message broker
[Kafka](https://kafka.apache.org/).

The backend of the application runs a background thread that produce messages to the broker. The message are simply a randomized IP address in the address range of the local host with the ping time and wether it is reachable or not.

Typical message:
```json
NetworkScan {
    "ipAddress" : "0.0.0.0",
    "pingTimeMilli" : 3,
    "isReachable" : true,
    "brokerPartitionId" : 0
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
This app uses 3 kafka brokers with one zookeeper instance. There is not a lot of topic so there is no point in using more than one broker. However, the objective of this small project is to test setup and interraction with a kafka cluster.
The docker-compose side of things for kafka was accomplished thanks to this [conlfuentinc repo](https://github.com/confluentinc/examples/blob/5.3.1-post/cp-all-in-one/docker-compose.yml).

## Deployment
Everything runs in a docker container. Just run the following command from project root:
``docker-compose up -d``
