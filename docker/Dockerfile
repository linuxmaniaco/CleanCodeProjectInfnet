FROM amazoncorretto:21
LABEL authors="linuxmaniaco"
WORKDIR app
COPY cars-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]