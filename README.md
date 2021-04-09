![GitHub Workflow Status](https://img.shields.io/github/workflow/status/jeff5m/gym-manager-api/all-tests)
### 🏗️ <ins>*Projeto em Desenvolvimento*</ins>
## Gym Manager API

### Software para gestão de academias

### 💬️ O que é? 
API desenvolvida para fazer o gerenciamento de uma academia

### 🗃 Gerenciando Instrutores e Alunos
O fluxo começa com o cadastro de um instrutor:

> - _**Nome**_
> - _**Foto de Perfil (URL)**_
> - _**E-mail**_
> - _**CPF**_
> - _**Especialidade (Musculação, Natação, Crossfit ou Pilates )**_
> - _**Data de nascimento**_ 

Depois que um Instrutor é criado, é possível editar as seguintes informações:

> - _**Foto de Perfil (URL)**_
> - _**E-mail**_
> - _**Especialidade (Musculação, Natação, Crossfit ou Pilates )**_
> 
Lembrando que o e-mail e o cpf são únicos para cada Instrutor.

Para fazer o cadastro de um Aluno, as informações necessárias são:

> - _**Nome**_
> - _**Foto de Perfil (URL)**_
> - _**E-mail**_
> - _**CPF**_
> - _**Peso**_
> - _**Altura**_
> - _**Idade**_
> - _**Gênero**_
> - _**Instrutor**_

Para selecionar um instrutor, basta adicionar seu id na chave "instructor". 
```json
"instructor": {
    "id": 2
}
```
Depois que um Aluno é criado, é possível editar as seguintes informações:

> - _**Foto de Perfil (URL)**_
> - _**E-mail**_

Assim como no caso do Instrutor, o e-mail e o cpf são únicos para cada Aluno.

### 🔧 Desenvolvimento
Tecnologias em uso no projeto

* ☕️ Java 11
* 🌱 Spring (Boot, Data JPA)
* 🐳 Docker