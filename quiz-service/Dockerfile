FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY build/libs/quiz-service-1.0.jar QuizService.jar

ENTRYPOINT ["java", "-jar", "QuizService.jar"]
