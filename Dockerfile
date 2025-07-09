# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file into the image
COPY target/*.jar app.jar

# Expose the port your Spring Boot app uses (usually 8080)
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
