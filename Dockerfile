FROM adoptopenjdk/openjdk8:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stock-quote-manager.jar
ENTRYPOINT ["java","-jar","stock-quote-manager.jar"]