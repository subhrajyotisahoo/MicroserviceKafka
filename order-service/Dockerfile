FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar
EXPOSE 8080
# Add sleep to allow Kafka to become ready
ENTRYPOINT ["/bin/sh", "-c", "sleep 20 && java -jar order-service.jar"]
#ENTRYPOINT ["java", "-jar", "order-service.jar"]
