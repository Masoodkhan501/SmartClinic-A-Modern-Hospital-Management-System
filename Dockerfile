# Stage 1: Build the application
# Use Eclipse Temurin's OpenJDK 21 image as the base for building
# jammy is the Ubuntu 22.04 base, a common stable choice
FROM eclipse-temurin:21-jdk-jammy AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first to leverage Docker caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Build dependencies (this will download them into the Maven cache)
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy the rest of your application code
COPY src src

# Package your Spring Boot application into an executable JAR
RUN ./mvnw clean install -DskipTests

# Stage 2: Create the final lightweight image for running the application
# Use Eclipse Temurin's OpenJDK 21 JRE image (smaller for runtime)
# jammy for consistency and stability
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app listens on (default 8080)
EXPOSE 8080

# Command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]