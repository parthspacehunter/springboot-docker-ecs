FROM openjdk:8
ADD ./target/cow-collar-apis-0.0.1-SNAPSHOT.jar /usr/src/cow-collar-apis-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
EXPOSE 8000
ENTRYPOINT [ "java", "-jar", "cow-collar-apis-0.0.1-SNAPSHOT.jar"]
