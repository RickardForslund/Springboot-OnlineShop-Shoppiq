FROM openjdk:15
COPY ./target/Shoppiq-0.0.1-SNAPSHOT.jar /usr/src/Shopiq/
WORKDIR /usr/src/Shopiq
EXPOSE 8080
CMD ["java", "--enable-preview", "-jar","Shoppiq-0.0.1-SNAPSHOT.jar"]