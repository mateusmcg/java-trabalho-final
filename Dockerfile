FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/trabalho-final-0.0.1-SNAPSHOT.jar trabalho-final-0.0.1-SNAPSHOT.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/trabalho-final-0.0.1-SNAPSHOT.jar"]