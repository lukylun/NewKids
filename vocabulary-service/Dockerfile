FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/vocabulary-service-1.0.jar VocabularyService.jar

ENTRYPOINT ["java", "-jar", "VocabularyService.jar"]
