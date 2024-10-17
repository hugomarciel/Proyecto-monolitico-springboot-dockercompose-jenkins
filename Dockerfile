##FROM ubuntu:latest
##LABEL authors="Hugo"

##ENTRYPOINT ["top", "-b"]

FROM openjdk:17
ARG JAR_FILE=target/Proyecto-monolitico-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} Proyecto-monolitico-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Proyecto-monolitico-0.0.1-SNAPSHOT.jar"]