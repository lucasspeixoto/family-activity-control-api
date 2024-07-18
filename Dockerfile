FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine as prod

ARG JAR_FILE=target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
ENV SERVER_PORT=6060
WORKDIR /
EXPOSE 6060
ENTRYPOINT ["java", "-jar", "/app.jar"]