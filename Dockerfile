FROM gradle:8-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:21
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/sogisurvey.jar
COPY src/docker/wait-for-it.sh /app/
RUN chmod +x /app/wait-for-it.sh
ENTRYPOINT ["java", "-jar", "/app/sogisurvey.jar"]