# Initial

A Spring Boot backend application for managing quizzes, categories, questions, and users with JWT authentication and monitoring support.

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Not%20specified-red.svg)]()

## Overview

This repository contains a backend service designed for an online quiz/exam platform. It includes user registration, JWT-based authentication, role-based access, quiz/category management, and monitoring integrations.

The application is built using Java and Spring Boot, with persistence handled through Spring Data JPA and MySQL.

## Features

- User registration and login
- JWT-based authentication
- Password hashing with BCrypt
- Role-based authorization support
- Quiz management APIs
- Category and question management
- Health and metrics exposure via Spring Actuator
- Prometheus/Grafana/Loki monitoring setup
- Docker support for containerized deployment

## Tech Stack

- Java 11
- Spring Boot 2.7.6
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (`io.jsonwebtoken:jjwt:0.9.0`)
- MySQL Connector
- Lombok
- Springdoc OpenAPI UI
- Micrometer + Prometheus
- Docker
- Gradle

## Project Structure

```text
.
├── Dockerfile
├── README.md
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
├── .gitignore
├── gradle/
│   └── wrapper/
├── monitoring/
│   ├── docker-compose.yml
│   ├── grafana/
│   │   └── datasources.yml
│   ├── loki/
│   │   └── loki-config.yml
│   ├── prometheus/
│   │   └── prometheus.yml
│   ├── promtail/
│   │   └── promtail-config.yml
│   └── logs/
│       └── spring.log
├── projectlogs/
│   └── spring.log
├── projectlogsquiz.log
├── src/
│   ├── main/
│   │   ├── java/com/docker/initial/
│   │   │   ├── configuration/
│   │   │   ├── controller/
│   │   │   ├── exception/
│   │   │   ├── modal/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── InitialApplication.java
│   │   │   └── ...
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/docker/initial/
│           └── InitialApplicationTests.java
└── .gitignore
```

## Prerequisites

Before running the project, make sure you have:

- Java 11+
- Gradle
- MySQL installed and running
- Docker (optional for monitoring/container setup)

## Database Configuration

The application is configured to connect to a MySQL database named `exam` on localhost.

File: `src/main/resources/application.properties`

```properties
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/exam
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

management.metrics.tags.application=quiz-service
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
logging.file.path=E:/project/logs
labels.logging=promtail
```

## Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/Abhijeet-Chakraborty/initial.git
cd initial
```

### 2. Build the project

```bash
./gradlew build
```

### 3. Start the application

```bash
./gradlew bootRun
```

The app will start on port `8081` by default.

## Docker

A Dockerfile is included in the repository.

```bash
docker build -t initial .
docker run -p 8082:8082 initial
```

Note: The project configuration listens on `8081`, while the Dockerfile exposes `8082`. You may need to align these values depending on your environment.

## Authentication Flow

The project uses Spring Security with JWT tokens.

### Login endpoint

```http
POST /generate-token
Content-Type: application/json
```

Request body:

```json
{
  "username": "demouser",
  "password": "demo123"
}
```

Response:

```json
{
  "token": "<jwt-token>"
}
```

### Current user endpoint

```http
GET /current-user
```

This endpoint returns the authenticated user details using the JWT principal.

## API Modules

### User APIs

Base path: `/user`

- `POST /user/` - Create a user
- `GET /user/{username}` - Fetch a user
- `DELETE /user/{userId}` - Delete a user

### Quiz APIs

Base path: `/quiz`

- `POST /quiz/` - Add a quiz
- `PUT /quiz/` - Update a quiz
- `GET /quiz/{qid}` - Get quiz by ID
- `GET /quiz/` - Get all quizzes
- `DELETE /quiz/{qid}` - Delete a quiz
- `GET /quiz/category/{cid}` - Get quizzes by category
- `GET /quiz/active` - Get active quizzes
- `GET /quiz/category/active/{cid}` - Get active quizzes by category

### Category and Question Management

The repository includes additional service and controller layers for:

- categories
- questions
- quiz-to-question relationships

## Swagger / API Documentation

The project includes OpenAPI support via `springdoc-openapi-ui`.

Once the application is running, open:

```text
http://localhost:8081/swagger-ui/index.html
```

## Monitoring

This repo contains a monitoring setup for observability.

### Start monitoring stack

```bash
cd monitoring
docker-compose up -d
```

Included services:

- Prometheus
- Grafana
- Loki
- Promtail

These files are located under the `monitoring/` directory.

## Main Components

### Controllers

- `AuthenticateController` — JWT login and current user retrieval
- `UserController` — user operations
- `QuizController` — quiz CRUD and active/filtered queries
- `CategoryController` — category functionality
- `QuestionController` — question functionality

### Models / Entities

- `User`
- `Role`
- `UserRole`
- `Quiz`
- `Category`
- `Question`
- `JwtRequest`
- `JwtResponse`

### Repositories

- `UserRepository`
- `RoleRepository`
- `QuizRepository`
- `CategoryRepository`
- `QuestionRepository`

### Security

The security layer is configured in the `configuration` package and manages:

- JWT authentication filter
- Authentication entry point
- Security configuration and bean setup

## Notes

- This project is structured as a Spring Boot REST API for a quiz/exam system.
- It is suitable for learning purposes and backend API development.
- Some configuration values are environment-specific, such as MySQL credentials and log paths.

## License

No explicit license file was found in the repository. If you intend to distribute or reuse this project publicly, it is recommended to add an appropriate open-source license such as MIT or Apache 2.0.

## Summary

`initial` is a Spring Boot-based quiz platform backend with user authentication, database persistence, security, and monitoring support. It is a strong starting point for building a full-stack assessment application.

---

If you want, I can also turn this into a more advanced version with badges, a table of contents, example request/response payloads, and a cleaner architecture diagram.
