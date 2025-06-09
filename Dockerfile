# Stage 1: Build the application
# Use an official OpenJDK 21 image as the base for building
FROM openjdk:21-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first to leverage Docker caching
# This helps speed up subsequent builds if dependencies don't change
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Build dependencies (this will download them into the Maven cache)
# We use -DskipTests for a faster build unless you need to run tests in the build stage
# You might want to run tests here: RUN chmod +x mvnw && ./mvnw dependency:go-offline -B test
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy the rest of your application code
COPY src src

# Package your Spring Boot application into an executable JAR
# -DskipTests is used here to prevent tests from running during the final packaging
RUN ./mvnw clean install -DskipTests

# Stage 2: Create the final lightweight image for running the application
# Use an official OpenJDK 21 JRE image (smaller for runtime)
FROM openjdk:21-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app listens on (default 8080)
EXPOSE 8080

# Command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]