FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/article-service-1.0.jar ArticleService.jar

ENTRYPOINT ["java", "-jar", "ArticleService.jar"]
