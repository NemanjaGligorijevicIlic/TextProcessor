FROM openjdk:22-jdk

WORKDIR /app

COPY target/HiQTextProcessor-0.0.1-SNAPSHOT.jar /app/HiQTextProcessor-0.0.1-SNAPSHOT.jar

ENV JAVA_HOME=/root/.sdkman/candidates/java/22.0.0-zulu
ENV PATH=$JAVA_HOME/bin:$PATH

CMD ["java", "-jar", "HiQTextProcessor-0.0.1-SNAPSHOT.jar"]

EXPOSE 6001
