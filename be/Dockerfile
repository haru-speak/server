FROM adoptopenjdk/openjdk11

COPY ./build/libs/*.jar haru-speak.jar

ENTRYPOINT ["java",  "-Dspring.profiles.active=prod", "-jar", "/haru-speak.jar"]
