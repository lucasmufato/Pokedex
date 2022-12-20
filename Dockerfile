# BUILDER
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app

COPY build.gradle settings.gradle  ./
RUN mkdir src
COPY src/ src/

RUN gradle clean build

# RUNNER
FROM eclipse-temurin:17-jdk

EXPOSE 8080

WORKDIR /home/api
COPY --from=builder app/build/libs/pokedex-*[0-9].jar /home/api/pokedex.jar
ENTRYPOINT ["java","-jar","/home/api/pokedex.jar"]