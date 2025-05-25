FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Create logs directory
RUN mkdir -p logs

# Copy the Maven build file and source code
COPY target/spring-boot-docker.jar app.jar

# Expose the port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]




