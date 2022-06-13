## Debug 專用images
# FROM  quay.io/eduk8s/jdk8-environment:latest
FROM adoptopenjdk/openjdk8:alpine

COPY ./target/CGA101G4-bootable.jar /app/CGA101G4-bootable.jar
WORKDIR /app


# https://github.com/open-telemetry/opentelemetry-java-instrumentation
RUN  set -eux; \
     addgroup demo && adduser -DH -G demo demo; \
     wget   https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.28.4/elastic-apm-agent-1.28.4.jar    -P  /app/  ; \
     wget   https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.14.0/opentelemetry-javaagent.jar -P  /app/  ; \
     chown -R demo:demo /app ; \
     ln -snf /usr/share/zoneinfo/Asia/Taipei /etc/localtime && echo Asia/Taipei > /etc/timezone

USER demo



EXPOSE 8080/tcp
ENV TZ=Asia/Taipei
ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS     -jar  /app/CGA101G4-bootable.jar"]