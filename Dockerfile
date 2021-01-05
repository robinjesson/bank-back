FROM balenalib/raspberry-pi-debian-openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} my-bank-back.jar
ENTRYPOINT ["java","-jar","/my-bank-back.jar"]
