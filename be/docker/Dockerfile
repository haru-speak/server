FROM adoptopenjdk/openjdk11

WORKDIR /var/www/

COPY ./build/libs/be-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "be-0.0.1-SNAPSHOT.jar"]
