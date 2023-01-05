FROM ghcr.io/graalvm/graalvm-ce:ol9-java17-22.3.0

WORKDIR /app
EXPOSE 8085

RUN gu install python
RUN gu install js

#COPY target/*.war app.war

COPY . .

RUN ./mvnw package

CMD [ "java", "-jar", "target/prorec-0.0.1-SNAPSHOT.war"]
