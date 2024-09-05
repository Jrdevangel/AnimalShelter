
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/animalshelter.jar /app/animalshelter.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/animalshelter.jar"]
