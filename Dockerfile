# Use the official OpenJDK image to run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/gateway-service.jar /app/gateway-service.jar

# Expose the port Gateway will run on
EXPOSE 8093

# Run the application
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]
