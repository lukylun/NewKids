FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/recommendation-service-1.0.jar RecommendationService.jar

ENTRYPOINT ["java", "-jar", "RecommendationService.jar"]
