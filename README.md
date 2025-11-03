## 📘 Visão Geral
A API-SQL Igesta é um backend desenvolvido em Java com Spring Boot, integrado a um banco de dados PostgreSQL.
O projeto tem como objetivo fornecer uma base sólida para aprendizado de APIs RESTful, autenticação com JWT e boas práticas de desenvolvimento backend.

## 🌐 Swagger da API:
👉 https://api-sql-igesta-2ano.onrender.com/swagger-ui/index.html#/

## 🚀 Funcionalidades Principais
- Autenticação com JWT
- Controle de acesso por tipo de usuário (Admin, Comum etc.)
- CRUD completo para diferentes entidades
- Integração com PostgreSQL
- Configuração via .env
- Deploy em nuvem via Render


## ⚙️ Configuração do ambiente (.env)
As informações sensíveis (como URL do banco, usuário e senha) estão no arquivo .env, que não deve ser versionado no repositório.
Exemplo de .env:
```
DB_URL=jdbc:postgresql://seu-servidor:5432/igesta
DB_USER=seu_usuario
DB_PASSWORD=sua_senha

API_ADMIN_USERNAME=igestaAdmin
API_ADMIN_PASSWORD=igestaSenha123
JWT_SECRET=suaChaveJWTSecreta
```
No application.properties, essas variáveis são usadas assim:
```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
````

Durante o deploy (Render, Koyeb, etc), defina essas variáveis no painel da plataforma.
Durante o desenvolvimento local, mantenha o .env na raiz do projeto.
💡 O projeto usa a biblioteca spring-dotenv para que o Spring Boot leia automaticamente o arquivo .env durante a execução local.


## 🛠️ Como executar localmente

1. Clone o repositório
```
git clone https://github.com/A-UTech/api-sql-igesta-2ano.git
cd api-sql-igesta-2ano
```

2. Crie o arquivo .env com as suas credenciais (ver exemplo acima).

3. Compile e rode o projeto:
`./mvnw spring-boot:run`

ou, com Docker:
`docker-compose up --build`

Acesse a API:
Swagger (deploy): https://api-sql-igesta-2ano.onrender.com/swagger-ui/index.html#/
Local: http://localhost:8080/swagger-ui/index.html#/


## 🧱 Tecnologias Utilizadas
- Java 17+
- Spring Boot 3.x
- Spring Security + JWT
- PostgreSQL
- Maven
- Docker / Docker Compose
- spring-dotenv
- Swagger UI

## 📄 Licença
Este projeto está licenciado sob a MIT License — consulte o arquivo LICENSE para mais detalhes.

