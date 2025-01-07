# Étape 1 : Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
COPY target/service_utilisateur-0.0.1-SNAPSHOT.jar /app/service_utilisateur.jar

# Étape 4 : Spécifier la commande d'exécution
ENTRYPOINT ["java", "-jar", "/app/service_utilisateur.jar"]
