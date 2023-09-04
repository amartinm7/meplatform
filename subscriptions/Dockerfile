FROM openjdk:20

MAINTAINER amartinm7@gmail.com

## reminder fever.jar is a fat jar with all dependencies
COPY build/libs/subscriptions*SNAPSHOT.jar /usr/local/subscriptions/subscriptions.jar

WORKDIR /usr/local/subscriptions

ENTRYPOINT ["java","-jar","subscriptions.jar", "--spring.config.name=application", "--spring.profiles.active=dev"]


