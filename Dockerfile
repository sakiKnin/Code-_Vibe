FROM openjdk:latest

ARG JAR_FILE=build/libs/demo5-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","app.jar"]
