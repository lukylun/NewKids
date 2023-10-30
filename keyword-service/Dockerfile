FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/keyword-service-1.0.jar KeywordService.jar

ENTRYPOINT ["java", "-jar", "KeywordService.jar"]
