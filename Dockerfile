# syntax=docker/dockerfile:1
FROM gradle:7.4.2-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17.0.2

EXPOSE 8443

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]