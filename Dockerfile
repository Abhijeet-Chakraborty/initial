FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/initial-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]


# FROM openjdk:17-jdk-slim
# # Set the working directory inside the container
# WORKDIR /app
#
# # Copy the packaged Spring Boot application JAR file into the container
# ADD ${JAR_FILE} app.jar
#
# # Expose the port your Spring Boot application runs on (default is 8080)
# EXPOSE 8082
#
# # Command to execute the application
# ENTRYPOINT ["java", "-jar", "app.jar"]