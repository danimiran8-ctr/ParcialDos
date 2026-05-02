FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/PARCIALDOS-1.jar app.jar
EXPOSE 8213
ENTRYPOINT ["java", "-jar", "app.jar"]
