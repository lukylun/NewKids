FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/keyword-batch-1.0.jar KeywordBatch.jar

ENTRYPOINT ["java", "-jar", "KeywordBatch.jar"]
