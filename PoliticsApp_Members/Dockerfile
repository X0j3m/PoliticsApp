# Etap 1: Budowanie (Maven)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etap 2: Obraz uruchomieniowy
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Kopiujemy tylko plik JAR ze stadium budowania
COPY --from=build /app/target/*.jar app.jar

# Spring Boot automatycznie mapuje zmienne środowiskowe na właściwości w application.properties
# Np. SPRING_DATASOURCE_URL zastąpi spring.datasource.url
ENTRYPOINT ["java", "-jar", "app.jar"]