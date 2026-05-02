FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/PARCIALDOS-1.jar app.jar
EXPOSE 8213
ENTRYPOINT ["java", "-jar", "app.jar"]
