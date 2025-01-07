FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/invoice-service-0.0.1-SNAPSHOT.jar /app/invoice-service-0.0.1-SNAPSHOT.jar

EXPOSE 10005

CMD ["java", "-jar", "/app/invoice-service-0.0.1-SNAPSHOT.jar"]