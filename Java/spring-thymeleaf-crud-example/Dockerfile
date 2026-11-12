# ---- Build Stage ----
# Use Maven base image from the Docker Hub
FROM maven:3.9-eclipse-temurin-11 AS build
ARG MAVEN_OPTS

# Set the current working directory inside the image
WORKDIR /app

# Copy the source code to the container
COPY pom.xml /app
RUN --mount=type=secret,id=maven_ca \
    if [ -f /run/secrets/maven_ca ]; then \
        keytool -importcert -noprompt -alias maven-build-ca \
            -file /run/secrets/maven_ca -cacerts -storepass changeit; \
    fi && mvn -B dependency:go-offline
COPY src /app/src

# Package the application
RUN mvn -B clean install -DskipTests

# ---- Deploy Stage ----
FROM eclipse-temurin:11-jre-jammy

# Copy the built JAR from the build stage
COPY --from=build /app/target/thymeleaf-0.0.1-SNAPSHOT.jar /app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
