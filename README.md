![GitHub Workflow Status](https://img.shields.io/github/workflow/status/jeff5m/gym-manager-api/all-tests)
![Docker Image Size (tag)](https://img.shields.io/docker/image-size/jeff5m/gym-manager-api/latest)
## Gym Manager API

### Software para gestão de academias

## 💬️ O que é? 
API desenvolvida para gerenciar academias.

## 🗃 Gerenciando Instrutores e Alunos
Você pode rodar a aplicação de duas maneiras:

### 1. 🐋 Utilizando docker
Navegue pelo terminal até a pasta raiz do projeto e execute o comando: 
```sh
docker-compose up
```
### 2. 🖥 Utilizando o maven
Navegue pelo terminal até a pasta raiz do projeto e execute o comando:
```sh
 ./mvnw spring-boot:run
```
## 🛎 Endpoints
Todos os endpoints são precedidos de `http://localhost:8080/`

#### Student
* /students/{id} `PUT`
* /students/{id} `DELETE`
* /students `GET`
* /students `POST`

#### Instructor
* /instructors/{id} `PUT`
* /instructors/{id} `DELETE`
* /instructors/{id} `GET`
* /instructors `GET`
* /instructors `POST`
* /instructors/{id}/students `GET`

## 📄 Documentação
A documentação da API, com a descrição de cada endpoint e seus possiveis retornos está disponível no link:

```sh
http://localhost:8080/swagger-ui.html
```

## 🧪 Testando a aplicação
### 1. Testes de unidade:
Navegue pelo terminal até a pasta raiz do projeto e execute o comando:
```sh
./mvnw test -P unit-tests
```
### 2. Testes de integração:
Navegue pelo terminal até a pasta raiz do projeto e execute o comando:
```sh
./mvnw test -P integration-tests
```
### 3. Todos os testes:
Navegue pelo terminal até a pasta raiz do projeto e execute o comando:
```sh
./mvnw test -P all-tests
```

## 🔧 Desenvolvimento
Tecnologias em uso no projeto:

* ☕️ **Java 11**
* 🌱 **Spring (Boot, Data JPA)**
* 🐋 **Docker**
* 📄 **Swagger**