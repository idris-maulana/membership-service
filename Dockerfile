FROM openjdk:21
EXPOSE 8080
COPY target/membership-service-0.0.1-SNAPSHOT.jar /usr/src/app/membership-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
CMD [ "java","-jar","membership-service-0.0.1-SNAPSHOT.jar" ]