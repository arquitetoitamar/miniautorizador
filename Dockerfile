# Etapa 1: Construção da aplicação
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: Criação da imagem final
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY src/main/resources/application.yml /app/config/application.yml

# Exposição da porta
EXPOSE 8080

# Comando de entrada, considerando o perfil ativo e caminho do application.yml
ENTRYPOINT ["java", "-Dspring.config.location=/app/config/application.yml", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
