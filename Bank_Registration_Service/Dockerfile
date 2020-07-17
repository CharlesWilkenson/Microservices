FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/Bank_registration_service*.jar  /app.jar
CMD ["java","-jar","/app.jar","--spring.profiles.active=prod"]
EXPOSE 8888
