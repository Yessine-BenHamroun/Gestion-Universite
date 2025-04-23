FROM openjdk:17
EXPOSE 8092
ADD target/Etudiant-0.0.1-SNAPSHOT.jar etudiant.jar
ENTRYPOINT ["java", "-jar", "etudiant.jar"]
