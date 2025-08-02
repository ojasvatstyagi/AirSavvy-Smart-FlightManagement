# --- Build stage ---
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.war app.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.war"]
