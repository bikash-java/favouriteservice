FROM openjdk:8-jdk-alpine
EXPOSE 9001
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ADD /target/*.jar favouriteservice-2.1.6.RELEASE.jar
ENTRYPOINT ["java","-jar","favouriteservice-2.1.6.RELEASE.jar"]