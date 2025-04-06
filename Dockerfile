# Utiliser une image de base avec Java 17
FROM openjdk:17-jdk-alpine

# Exposer le port sur lequel l'application Spring Boot écoute
EXPOSE 8082

# Copier le fichier JAR de l'application dans le conteneur
ADD target/ServiceDoums.jar app.jar

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]