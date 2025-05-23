FROM openjdk:17-jdk-slim
WORKDIR /app

# Install netcat (nc) so wait-for-it.sh can use it
RUN apt-get update && \
    apt-get install -y netcat && \
    rm -rf /var/lib/apt/lists/*

# Copy and make the wait script executable
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh

# Copy your Spring Boot jar
COPY target/spring-boot-docker.jar app.jar

# Expose the port
EXPOSE 8080

# Wait for Postgres on service 'postgres', port 5432, then start the app
ENTRYPOINT ["./wait-for-it.sh", "postgres", "5432", "--", "java", "-jar", "app.jar"]




