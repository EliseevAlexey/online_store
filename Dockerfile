FROM ubuntu:16.04 as builder
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean package


FROM alpine:3.7
RUN apk --update add openjdk8-jre && java -version
WORKDIR /root/
COPY --from=builder /app/target/simpledocker-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD ["java", "-jar", "simpledocker-0.0.1-SNAPSHOT.jar"]

