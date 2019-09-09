FROM adoptopenjdk/openjdk11:alpine-jre

ARG jar
WORKDIR /app

COPY  /target/$jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]