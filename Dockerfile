FROM arm32v7/adoptopenjdk:12-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} my-bank-back.jar
ENTRYPOINT ["java","-jar","/my-bank-back.jar"]
