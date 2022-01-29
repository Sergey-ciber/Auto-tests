FROM maven:3.8.4-openjdk-11

RUN mkdir test
WORKDIR /test
COPY . /test
RUN mvn clean install -DskipTests
CMD java -jar target/TestProject-1.0-SNAPSHOT-jar-with-dependencies.jar