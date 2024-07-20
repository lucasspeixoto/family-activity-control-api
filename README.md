<h1 align="center">
   Family Activity Control
</h1>


## 💻 Repositório

[Family Activity Control](https://github.com/lucasspeixoto/family-activity-control-api) é o projeto de
backend da futura plataforma #FamilyActivityControl

## ✨ Tecnologias

O projeto foi desenvolvido com as seguintes tecnologias:

- [Sprint Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Flyway](https://flywaydb.org/)
- [JUnit](https://junit.org/junit5/docs/current/api/)
- [Mockito](https://site.mockito.org/)
- [Docker](https://www.docker.com/)

## 📑 Diagrama de Banco de dados
![ERD](erd.png 'ERD')

## 🚀 Instalação

```bash
# Clone o repositório
$ git clone https://github.com/lucasspeixoto/family-activity-control-api

# Entre na pasta do projeto
$ cd family-activity-control-api

# Execute a aplicação
$ mvn spring-boot:run
```

## 🚢 Iniciar em container Docker

```bash
# Clone o repositório
$ git clone https://github.com/lucasspeixoto/family-activity-control-api

# Entre na pasta do projeto
$ cd family-activity-control-api

# Execute a aplicação em uma máquina com docker
$ docker-compose up --build

# O servidor iniciará na porta 6060
```

## 🦾 Testing

```bash
# Rodar os testes
$ mvn clean test

# Rodar os testes e gerar cobertura do jacoco
$ mvn clean test jacoco:report

# Varredura com pitest
$ mvn test-compile org.pitest:pitest-maven:mutationCoverage

```


## 📝 Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](https://opensource.org/licenses/MIT) para obter mais detalhes.

---