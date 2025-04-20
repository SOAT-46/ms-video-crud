FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

FROM amazoncorretto:21-alpine3.21
WORKDIR /app

RUN addgroup -S springgroup && adduser -S springuser -G springgroup && \
    chown springuser:springgroup /app
USER springuser:springgroup

COPY --from=build --chown=springuser:springgroup /app/target/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]