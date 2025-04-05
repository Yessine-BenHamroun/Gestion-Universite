# Utilisation de l'image OpenJDK 17
FROM openjdk:17

# Expose le port utilisé par l'application
EXPOSE 8761

# Ajoute le fichier JAR de l'application dans l'image
ADD target/cours-0.0.1-SNAPSHOT.jar cours.jar

# Définit le point d'entrée pour démarrer l'application
ENTRYPOINT ["java", "-jar", "cours.jar"]
