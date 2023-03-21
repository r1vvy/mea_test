#FROM eclipse-temurin:17-jdk
#
#WORKDIR /app
#
#COPY build/libs/*.jar app.jar
#
#COPY weatherapp-app weatherapp-app
#COPY weatherapp-core weatherapp-core
#COPY weatherapp-domain weatherapp-domain
#COPY weatherapp-in weatherapp-in
#COPY weatherapp-out weatherapp-out
#COPY weatherapp-repository weatherapp-repository
#
#CMD ["java", "-jar", "app.jar"]