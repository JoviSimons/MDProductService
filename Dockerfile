FROM adoptopenjdk/openjdk11
EXPOSE 8081
ADD target/product-service-docker.jar product-service-docker.jar
ENTRYPOINT ["java", "-jar", "/product-service-docker.jar"]