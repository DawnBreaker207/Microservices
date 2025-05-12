## Start with a base image
FROM maven:latest AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ARG PROJECT_VERSION=0.0.1
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app


COPY --from=builder /app/target/notification-service-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8083
ENTRYPOINT [ "java","-jar","notification-service-0.0.1-SNAPSHOT.jar" ]
