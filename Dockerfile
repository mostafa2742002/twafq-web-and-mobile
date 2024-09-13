# Use a multi-architecture Maven image for the build stage
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Use a multi-architecture OpenJDK image for the final image
FROM openjdk:17-jdk-slim
COPY --from=build /target/twafq-0.0.1-SNAPSHOT.jar twafq.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "twafq.jar"]
