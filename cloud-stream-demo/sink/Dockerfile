FROM openjdk:11.0.1-jre-slim-stretch

WORKDIR /app
ARG JAR=cloud-stream-sink-0.0.1-SNAPSHOT.jar

COPY  /target/$JAR /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]