FROM openjdk:11

RUN mkdir test
WORKDIR /test
COPY . /test