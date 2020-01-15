FROM gradle:5.6.2-jdk8

USER root

COPY . .

CMD ./gradlew bootRun

EXPOSE 8080
EXPOSE 8443