FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y curl

COPY target/*.jar app.jar

EXPOSE 8761

CMD ["java", "-jar", "/app/app.jar"]
