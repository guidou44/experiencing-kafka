FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package -Dskiptests

FROM openjdk:8-jdk-alpine
COPY --from=build /workspace/target/experiencing-kafka-2.2.4.RELEASE-spring-boot.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/backend.jar"]

