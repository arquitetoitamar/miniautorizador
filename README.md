
🗂️ Estrutura de Diretórios

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
