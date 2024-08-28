FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .

RUN mvn clean package
EXPOSE 8080
ENTRYPOINT ["mvn","spring-boot:run"]
