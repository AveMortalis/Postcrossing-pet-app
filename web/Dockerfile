FROM maven:3.6-openjdk-8 AS builder
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package

FROM tomcat:9.0.39-jdk8-openjdk-slim
WORKDIR /usr/local/tomcat/bin/
COPY --from=builder /app/target/Postcrossing.war /usr/local/tomcat/webapps/Postcrossing.war
CMD ./catalina.sh run
EXPOSE 8080
