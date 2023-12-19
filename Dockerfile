FROM openjdk:21
WORKDIR /opt

#Copy .class files from the target directory into the image
COPY target/*.jar ./app/app.jar

#Set the command to run the Java application
CMD ["java", "-jar", "app/app.jar"]