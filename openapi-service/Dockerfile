FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/openapi-service-1.0.jar OpenapiService.jar

ENTRYPOINT ["java", "-jar", "OpenapiService.jar"]
