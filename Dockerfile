# Use the official OpenJDK image to run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/etudiant-service.jar /app/etudiant-service.jar

# Expose the port Etudiant will run on
EXPOSE 8092

# Run the application
ENTRYPOINT ["java", "-jar", "/app/etudiant-service.jar"]
