FROM openjdk:11-jdk-alpine
ARG JAR_FILE
COPY primeness_server/target/primeness_server-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]