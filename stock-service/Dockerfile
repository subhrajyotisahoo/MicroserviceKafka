FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy JAR file
COPY target/stock-service-0.0.1-SNAPSHOT.jar stock-service.jar

# ✅ Copy application.properties
COPY src/main/resources/application.properties ./application.properties

EXPOSE 8081

# ✅ Tell Spring Boot to use the copied config
ENTRYPOINT ["java", "-jar", "stock-service.jar", "--spring.config.location=file:./application.properties"]
