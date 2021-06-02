FROM maven:3.6.3-jdk-11 as maven_builder
COPY . .
WORKDIR .
RUN mvn -Dmaven.test.skip=true install

FROM tomcat
LABEL maintainer="denisenkomm@gmail.com"
COPY --from=maven_builder target/ROOT.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]

