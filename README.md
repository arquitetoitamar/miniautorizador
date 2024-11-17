
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=arquitetoitamar_miniautorizador&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=arquitetoitamar_miniautorizador)
# Mini-Autorizador - Documentação da Arquitetura

![Diagrama da Arquitetura](docs/arquitetura.png)

## 🗂️ Estrutura de Diretórios

```plaintext
mini-autorizador/
├── src/
│   ├── main/
│   │   ├── java/com/vr/beneficios/autorizador/
│   │   │   ├── AutorizadorApplication.java
│   │   │   ├── config/
│   │   │   │   ├── JpaConfig.java
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── CartaoController.java
│   │   │   │   └── TransacaoController.java
│   │   │   ├── dto/
│   │   │   │   ├── CartaoRequest.java
│   │   │   │   ├── CartaoResponse.java
│   │   │   │   ├── TransacaoRequest.java
│   │   │   │   └── TransacaoResponse.java
│   │   │   ├── exception/
│   │   │   │   ├── CartaoNotFoundException.java
│   │   │   │   └── SaldoInsuficienteException.java
│   │   │   ├── mapper/
│   │   │   │   ├── CartaoMapper.java
│   │   │   │   └── TransacaoMapper.java
│   │   │   ├── model/
│   │   │   │   ├── Cartao.java
│   │   │   │   └── Transacao.java
│   │   │   ├── repository/
│   │   │   │   ├── CartaoRepository.java
│   │   │   │   └── TransacaoRepository.java
│   │   │   ├── service/
│   │   │   │   ├── CartaoService.java
│   │   │   │   └── AutorizacaoService.java
│   │   │   └── strategy/
│   │   │       ├── RegraAutorizacao.java
│   │   │       ├── CartaoExistenteRegra.java
│   │   │       ├── SenhaCorretaRegra.java
│   │   │       └── SaldoDisponivelRegra.java
│   └── test/
│       └── java/com/vr/beneficios/autorizador/
│           ├── controller/
│           │   ├── CartaoControllerTest.java
│           │   └── TransacaoControllerTest.java
│           ├── service/
│           │   ├── CartaoServiceTest.java
│           │   └── AutorizacaoServiceTest.java
│           └── mapper/
│               └── CartaoMapperTest.java
├── docker-compose.yml
├── Dockerfile
├── scripts/
│   └── init.js
├── pom.xml
└── README.md
```

## 📝 Descrição dos Pacotes

- **config/**: Contém configurações do Spring Boot, como JPA e Swagger.
- **controller/**: Controladores REST para gerenciar endpoints HTTP.
- **dto/**: Objetos de transferência de dados (Data Transfer Objects).
- **exception/**: Exceções personalizadas para tratar erros específicos.
- **mapper/**: Classes de mapeamento entre entidades e DTOs.
- **model/**: Entidades JPA que representam as tabelas do banco de dados.
- **repository/**: Repositórios JPA para persistência de dados.
- **service/**: Classes de serviço que contêm a lógica de negócios.
- **strategy/**: Implementação do padrão Strategy para regras de autorização.

## 🛠️ Passos para Execução

### 1. Construir a Imagem Docker

```bash
docker-compose build
```

### 2. Iniciar os Serviços

```bash
docker-compose up
```

### 3. Acessar a Aplicação

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- MongoDB: `mongodb://localhost:27017/miniautorizador`
- MySQL: `jdbc:mysql://localhost:3306/miniautorizador`

## 🛠️ Configurações no `application.properties`

```properties
# Configuração do MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/miniautorizador?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=12345678
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect

# Configuração do MongoDB
spring.data.mongodb.uri=mongodb://admin:12345678@localhost:27017/miniautorizador
```

## 🛠️ Estrutura do Docker

- **docker-compose.yml**: Define os serviços para MongoDB, MySQL e Spring Boot.
- **Dockerfile**: Define a construção da imagem da aplicação Spring Boot.
- **scripts/init.js**: Script de inicialização para configurar o MongoDB.

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Data MongoDB**
- **Springdoc OpenAPI (Swagger UI)**
- **MySQL 5.7**
- **MongoDB 4.2**
- **Docker e Docker Compose**

## 📝 Observações

- A aplicação utiliza o padrão Strategy para implementar regras de autorização de forma flexível.
- O uso do Docker Compose facilita o gerenciamento e a execução dos serviços da aplicação.
- A cobertura de testes automatizados está focada nos serviços e controladores principais.

## 📄 Licença

Este projeto é distribuído sob a licença Apache 2.0.

## ✨ Contribuições

Sinta-se à vontade para abrir issues e enviar pull requests.
