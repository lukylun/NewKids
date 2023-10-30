FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/analysis-service-1.0.jar AnalysisService.jar

ENTRYPOINT ["java", "-jar", "AnalysisService.jar"]
