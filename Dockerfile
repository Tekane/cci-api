FROM openjdk:11
ADD target/cci-api.jar cci-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cci-api.jar"]
