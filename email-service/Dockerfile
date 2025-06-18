FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/email-service-0.0.1-SNAPSHOT.jar email-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "email-service.jar"]
