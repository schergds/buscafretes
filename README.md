# Vitrine de Fretes

Sistema web de vitrine de fretes onde usuários podem publicar e encontrar ofertas de transporte de cargas de forma simples e rápida.

O objetivo é conectar quem precisa enviar cargas com motoristas e transportadoras disponíveis, funcionando como um marketplace de fretes.

---

## Funcionalidades

### Usuários
- Cadastro e login com email e senha
- Autenticação com Spring Security
- Senha criptografada com BCrypt

### Fretes
- Publicação de fretes com:
  - origem e destino
  - valor do frete
  - descrição da carga
  - tipo de caminhão necessário
  - peso estimado
  - telefone e WhatsApp para contato
  - data de coleta
- Edição e remoção de fretes próprios
- Marcação de frete como finalizado
- Listagem pública de fretes disponíveis

### Busca e navegação
- Filtros por:
  - origem
  - destino
  - tipo de caminhão
- Paginação de resultados
- Página detalhada do frete

### Contato
- Botão direto para WhatsApp com link no formato:
  https://wa.me/55NUMERO

---

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- HTMX
- Bootstrap 5
- Maven

---

## Arquitetura

Padrão MVC com organização:

controller  
service  
repository  
entity  
dto  
config  
security  

Sistema monolítico focado em simplicidade e rapidez de desenvolvimento.

---

## Banco de dados

- PostgreSQL
- JPA com Hibernate
- Configuração com ddl auto update em desenvolvimento

---

## Segurança

- Login com email e senha
- Spring Security com sessão
- Senhas armazenadas com BCrypt
- Proteção de rotas privadas

---

## Como executar o projeto

### Pre requisitos
- Java 21 ou superior
- Maven
- PostgreSQL

### Passos

Clonar o repositório:

git clone https github.com seu usuario vitrine de fretes.git

Criar banco de dados:

CREATE DATABASE vitrine fretes

Configurar application properties:

spring datasource url jdbc postgresql localhost 5432 vitrine fretes  
spring datasource username postgres  
spring datasource password sua senha  
spring jpa hibernate ddl auto update  

Executar o projeto:

mvn spring boot run

Acessar:

http localhost 8080

---

## Status do projeto

Em desenvolvimento MVP

---

## Objetivo

Criar uma plataforma simples e eficiente para divulgação de fretes com foco em:

- rapidez de publicação  
- facilidade de contato  
- usabilidade
