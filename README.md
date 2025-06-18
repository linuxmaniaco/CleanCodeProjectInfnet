# 🚗 **Projeto Carro & Fabricante API** 🚗

Este é um projeto de uma API RESTful construída com **Spring Boot** para gerenciar informações sobre **Carros** e **Fabricantes**. A API permite realizar operações de CRUD (Criar, Ler, Atualizar, Deletar) tanto para os carros quanto para os fabricantes. Além disso, também inclui a autenticação de usuários com senhas.

## 📦 **Tecnologias Utilizadas**

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [H2 Database](https://www.h2database.com/html/main.html) (para fins de teste)
- [Docker](https://www.docker.com/)

## 🚀 **Como Rodar o Projeto**

### 1. **Pré-requisitos**

Certifique-se de ter as seguintes ferramentas instaladas:

- [Java 17 ou superior](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/get-started)

### 2. **Rodando Localmente**

#### Clonando o Repositório

```bash
git clone https://github.com/leoinfnet/trabalho_final_react_noite.git
cd projeto-carro-fabricante
```

#### Contruindo com Maven
```bash
mvn clean install -Dmaven.test.skip
```
#### Rodando
```bash
mvn spring-boot:run
```

### 3. **Rodando com Docker**
```bash
docker run -p 8080:8080 leogloriainfnet/cars:tagname
```

### Fim



