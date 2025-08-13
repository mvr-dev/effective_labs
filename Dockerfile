# Стадия сборки
FROM gradle:8.4.0-jdk21-alpine as build
WORKDIR /opt/app
COPY . .
RUN ./gradlew clean build -x test

# Стадия запуска
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /opt/app/build/libs/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]