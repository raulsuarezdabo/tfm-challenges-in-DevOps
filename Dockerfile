FROM openjdk:8-jdk-alpine
RUN addgroup -S uoc && adduser -S devsecop -G uoc
USER devsecop:uoc
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]