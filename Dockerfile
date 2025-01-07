FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/service_utilisateur-0.0.1-SNAPSHOT.jar /app/service_utilisateur-0.0.1-SNAPSHOT.jar

EXPOSE 10005

CMD ["java", "-jar", "/app/user-service-0.0.1-SNAPSHOT.jar"]