# API Rest apara atender o projeto rating-front

Esta é uma API desenvolvida para gerenciar avaliações de clientes em uma aplicação web. Ela permite realizar operações como criar avaliações, listar todas as avaliações e validar se um e-mail já existe na base de dados.

## Endpoints Principais Disponíveis
 - POST /api/v1/ratings: Cria uma nova avaliação com os dados fornecidos.
 - GET /api/v1/ratings: Retorna uma lista de todas as avaliações cadastradas.
 - GET /api/v1/ratings/validate?email={email}: Valida se o e-mail informado já existe na base de dados.

## Tecnologias Utilizadas
 - Java
 - Spring Boot
 - Swagger para documentação
 - Postgres

## Como Usar
Certifique-se de ter o Java e o Maven instalados. Clone o repositório e execute o projeto usando o seguinte comando:

```bash
mvn spring-boot:run
```
## Base de dados
Foi utilizado no projeto o banco relacional PostgresSQL, os scripts estão na raiz do projeto:
 - rating-api/script.sql

## Swagger
Acesse a documentação da API(http://localhost:8080/swagger-ui.html) para detalhes sobre os endpoints disponíveis e como utilizá-los.

#### Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues para reportar problemas ou sugestões de melhorias.


