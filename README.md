# Visão Geral da Arquitetura

![Arquitetura](docs/archtecture.png)

# Bibliotecas usadas

## spring-boot-starter-amqp

ToDo

## spring-boot-starter-web

ToDo

## lombok

ToDo

## spring-boot-starter-test

ToDo

## springfox-swagger2

ToDo

## springfox-swagger-ui

ToDo

## spring-boot-starter-data-mongodb

ToDo

## javax.mail

ToDo

## spring-boot-starter-logging

ToDo

## de.flapdoodle.embed.mongo

ToDo

# Como rodar a aplicação

## Pré-Requisitos

- Docker
- Docker-Compose
- Java
- Maven

## Steps

- Dentro da pasta `devops` existem 3 scrips:
  - build.sh
    - Responsável por buildar a aplicação e gerar o `.jar`
  - start-app.sh
    - Responsável por executar o comando `docker-compose up` que irá utilizar o arquivo `docker-compose.yml` na raiz do projeto para subir todos os containers necessários para o correto funcionamento da aplicação, sendo eles:
      - MongoDB
      - RabbitMQ
      - Dockerfile da API Springboot em questão
  - stop-app.sh
    - Responsável por executar o comando `docker-compose down` que interrompe a execução da aplicação, removendo todos os containers mencionados acima.
- Uma vez que a aplicação está sendo executada, basta acessar o endereço `http://localhost:8080/swagger-ui.html#` para visualizar todos os endpoints disponívels pela API e testar suas funções.
- Caso queira, também é possível acessar o gerenciador do RabbitMQ no endereço `http://localhost:15672` com o usuário e senha sendo, respectivamente, `guest:guest`.