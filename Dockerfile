FROM amazoncorretto:17.0.7-alpine as tccapi

ARG JAR_FILE=target/contest-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]

EXPOSE 80