FROM java:8
EXPOSE 8080
VOLUME /slm
ADD b2c-docker-1.0.0-SNAPSHOT.jar b2c-docker.jar
RUN sh -c 'touch /b2c-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /b2c-docker.jar" ]
