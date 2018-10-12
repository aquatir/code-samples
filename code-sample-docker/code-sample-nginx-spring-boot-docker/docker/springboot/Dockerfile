FROM openjdk:8-jdk-slim
COPY ./target/code-sample-nginx-green-blue-2.0.5.RELEASE.jar /usr/src/app/
WORKDIR /usr/src/app
EXPOSE 8080
CMD ["java", "-jar", "code-sample-nginx-green-blue-2.0.5.RELEASE.jar"]