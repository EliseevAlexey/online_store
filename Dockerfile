FROM openjdk:8-jre-alpine
ARG artifactId
ARG version
ENV artifact ${artifactId}-${version}.jar
WORKDIR /app
COPY target/${artifact} /app
EXPOSE 8000
CMD java -jar /app/${artifact}
