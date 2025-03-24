# Use the official OpenJDK image to run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/eureka-service.jar /app/eureka-service.jar

# Expose the port Eureka will run on
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "/app/eureka-service.jar"]
