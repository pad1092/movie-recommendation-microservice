FROM openjdk:18
ARG FILE=target/*.jar
COPY ${FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]