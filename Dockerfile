FROM openjdk

WORKDIR /jumia

COPY target/api-exercise-0.0.1-SNAPSHOT.jar /jumia/api-exercise.jar

ENTRYPOINT [ "java", "-jar", "api-exercise.jar" ]