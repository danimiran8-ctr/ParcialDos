
FROM openjdk:21

COPY PARCIALDOS/target/PARCIALDOS-1.jar app.jar

EXPOSE 8213

ENTRYPOINT ["java", "-jar", "app.jar"]

