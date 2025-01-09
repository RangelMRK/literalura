# 📚 Literarura - Catálogo de Livros

**Literarura** é uma aplicação de catálogo de livros que permite interação via console para buscar, listar e explorar informações de livros. Os dados dos livros são obtidos através da API **GutenDex**, e os usuários têm diversas opções de interação, como busca por título, listagem de livros e autores, e filtragem por idioma ou ano.

---

## 🔍 Objetivo do Projeto

O objetivo do projeto é desenvolver um catálogo de livros interativo que:
- Busque livros por título a partir de uma API externa.
- Permita o registro de livros e autores em um banco de dados local.
- Ofereça opções para listar livros, autores e aplicar filtros.
- Disponibilize interação textual amigável via console.

---

## 🚀 Funcionalidades

1. **Buscar Livro pelo Título**
   - O usuário insere o título do livro e a aplicação busca informações na API **GutenDex**.
   - Caso o livro seja encontrado, ele é salvo no banco de dados.

2. **Listar Livros Registrados**
   - Exibe todos os livros já registrados no banco de dados.

3. **Listar Autores Registrados**
   - Mostra a lista de autores e os livros associados a cada autor.

4. **Listar Autores Vivos em um Determinado Ano**
   - Filtra autores que estavam vivos em um ano específico fornecido pelo usuário.

5. **Listar Livros por Idioma**
   - Filtra os livros registrados com base no idioma fornecido pelo usuário.

6. **Sair**
   - Encerra a aplicação.

---

## 🛠️ Ferramentas e Tecnologias Utilizadas

### Frameworks e Bibliotecas

- **Java 17**: Linguagem principal utilizada no projeto.
- **Spring Boot**: Framework para simplificar a configuração e desenvolvimento da aplicação.
  - **Spring Data JPA**: Para persistência de dados no banco de dados.
  - **Spring Web**: Para interação com APIs externas.
- **Jackson**: Biblioteca para serialização e desserialização de JSON.

### Banco de Dados

- **PostgreSQL**: Banco de dados relacional utilizado para persistir as informações de livros e autores.

### Ferramentas de Build e Gerenciamento de Dependências
- **Maven**: Para gerenciamento de dependências e build do projeto.

---

## 🗂️ Estrutura do Projeto

A estrutura principal do projeto é organizada da seguinte forma:

```bash
src/
├── main/
│   ├── java/
│   │   └── com.rangelmrk.literalura/
│   │       ├── main/                  # Classe principal e Menu interativo
│   │       │   └── Main.java
│   │       ├── model/                 # Modelos do domínio (Livro, Autor, etc.)
│   │       │   ├── Autor.java
│   │       │   ├── DadosAutor.java
│   │       │   ├── DadosLivro.java
│   │       │   ├── DadosResposta.java
│   │       │   └── Livro.java
│   │       ├── repository/            # Repositórios (Spring Data JPA)
│   │       │   ├── AutorRepository.java
│   │       │   └── LivroRepository.java
│   │       ├── service/               # Serviços para API e conversão de dados
│   │       │   ├── ConsumoAPI.java
│   │       │   ├── ConverteDados.java
│   │       │   └── IConverteDados.java
│   │       └── LiteraruraApplication  # Classe principal do Spring Boot
│   └── resources/
│       ├── application.properties     # Configurações do Spring Boot
├── pom.xml                            # Configurações do Maven
```

---

## 🌐 Integração com a API GutenDex

Os dados dos livros são obtidos através da API pública **GutenDex**. Para realizar buscas, a aplicação consome o endpoint:
https://gutendex.com/books/?search={titulo_do_livro}


A resposta da API contém informações como título, autores, idiomas e formatos do livro.

---

## ⚙️ Configuração do Projeto

### Pré-requisitos

- **JDK 17** ou superior
- **Maven**
- **PostgreSQL**
- **IDE** (IntelliJ IDEA, Eclipse, VSCode, etc.)

### Configuração do Banco de Dados

1. Certifique-se de que o PostgreSQL está instalado e rodando em sua máquina.
2. Crie o banco de dados chamado `literarura`:
   ```sql
   CREATE DATABASE literarura;
3. Configure o arquivo application.properties com as credenciais do banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost/literarura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
## Passos para Executar
1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/literarura.git
cd literarura
```
2. Compile e execute o projeto:
```
mvn clean install
mvn spring-boot:run
```
3. Interaja com o menu exibido no console.

## ⚡ Exemplos de Uso
### Menu Interativo
Ao iniciar a aplicação, o seguinte menu será exibido:

```
1 - Buscar Livro pelo título
2 - Listar Livros Registrados
3 - Listar Autores Registrados
4 - Listar Autores vivos em um determinado ano
5 - Listar Livros em um determinado Idioma
0 - Sair
```
### Buscar Livro pelo Título
Exemplo de busca:

```
Digite o título do livro que deseja buscar:
Dom Casmurro
```

Saída:

```
Título: Dom Casmurro
Autor: Machado de Assis
Idioma: pt
Downloads: 1500
```
## 🔗 Links Úteis
GutenDex API: https://gutendex.com

Spring Boot Documentation: https://spring.io/projects/spring-boot
