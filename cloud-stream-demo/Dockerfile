FROM adoptopenjdk/openjdk11:alpine-jre

ARG MAIN_CLASS
ENV MAIN_CLASS_ENV=$MAIN_CLASS

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-noverify","-cp","app:app/lib/*", "com.example.cloudstream.demo.ApplicationKt"]