FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY backend /workspace/backend
COPY messagebroker /workspace/messagebroker
COPY scanner /workspace/scanner
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
COPY --from=build /workspace/backend/target/backend-1.0-SNAPSHOT-spring-boot.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/backend.jar"]

