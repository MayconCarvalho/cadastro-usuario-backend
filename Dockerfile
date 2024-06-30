# Start with a base image containing Java runtime (stage 1)
FROM maven:3.8.1-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Start with a base image containing Java runtime (stage 2)
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar backend-api.jar
ENTRYPOINT ["java","-jar","backend-api.jar"]
EXPOSE 8080
