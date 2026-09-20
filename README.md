# Initial Project

A Spring Boot-based quiz management backend with user authentication, role-based access control, quiz/category management, and monitoring support. The project is designed as a learning/demo application for building a REST API for an exam or quiz platform.

## Overview

This repository contains a backend service for managing:

- Users and authentication
- Roles and authorization
- Categories
- Quizzes
- Questions
- Monitoring and health endpoints

The application uses Spring Boot, Spring Security, JWT, JPA, and MySQL.

## Tech Stack

- Java 11
- Spring Boot 2.7.6
- Spring Data JPA
- Spring Web
- Spring Security
- JWT (jjwt 0.9.0)
- MySQL 8
- Lombok
- Springdoc OpenAPI UI
- Micrometer + Prometheus
- Docker
- Gradle

## Project Structure

```text
.
├── Dockerfile
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
├── gradle/
├── monitoring/
│   ├── docker-compose.yml
│   ├── grafana/
│   ├── loki/
│   ├── prometheus/
│   └── promtail/
├── projectlogs/
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
│   │   │   └── InitialApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/docker/initial/
│       └── InitialApplicationTests.java
└── .gitignore
```

## Main Features

### 1. Authentication and Authorization

The application exposes JWT-based authentication:

- User login via `/generate-token`
- Current logged-in user details via `/current-user`
- Spring Security configuration for protected endpoints
- BCrypt password hashing for user creation

### 2. User Management

Endpoints under `/user`:

- `POST /user/` - Create a new user
- `GET /user/{username}` - Fetch a user by username
- `DELETE /user/{userId}` - Delete a user

### 3. Quiz Management

Endpoints under `/quiz`:

- `POST /quiz/` - Add a quiz
- `PUT /quiz/` - Update a quiz
- `GET /quiz/{qid}` - Get a quiz by ID
- `GET /quiz/` - Get all quizzes
- `DELETE /quiz/{qid}` - Delete a quiz
- `GET /quiz/category/{cid}` - Get quizzes by category
- `GET /quiz/active` - Get active quizzes
- `GET /quiz/category/active/{cid}` - Get active quizzes by category

### 4. Category and Question APIs

The project includes dedicated controller/service layers for categories and questions, allowing:

- Category creation and retrieval
- Question creation and management
- Linking questions to quizzes

### 5. Monitoring and Observability

The repository includes monitoring configuration for:

- Prometheus
- Grafana
- Loki
- Promtail

This is configured through the `monitoring` directory and is intended for application metrics and log collection.

## Database Configuration

The project expects a MySQL database named `exam` running on localhost.

Current configuration in `src/main/resources/application.properties`:

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

## Prerequisites

Before running this project, ensure you have:

- Java 11 or later
- Gradle
- MySQL server running
- Docker (optional, for monitoring and containerized execution)

## Run the Application

### Option 1: Run with Gradle

```bash
git clone https://github.com/Abhijeet-Chakraborty/initial.git
cd initial
./gradlew build
./gradlew bootRun
```

The application will start on port `8081` as configured in `application.properties`.

### Option 2: Run with Docker

A Dockerfile is included in the repository.

```bash
docker build -t initial .
docker run -p 8082:8082 initial
```

Note: The Dockerfile exposes port `8082`, while the application configuration currently listens on port `8081`.

## Swagger / API Documentation

The project includes `springdoc-openapi-ui`, which provides Swagger UI for API exploration.

Once the application is running, access:

```text
http://localhost:8081/swagger-ui/index.html
```

## Monitoring Setup

The monitoring directory contains a Docker Compose setup for observability.

To start monitoring services:

```bash
cd monitoring
docker-compose up -d
```

This may include:

- Prometheus: app metrics collection
- Grafana: dashboard visualization
- Loki: log collection
- Promtail: log forwarding

## Authentication Flow

A typical login flow is:

1. POST request to `/generate-token` with username and password
2. Server validates credentials using Spring Security
3. JWT token is generated and returned
4. The token is used in requests to protected APIs

Example request body:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

## Sample API Endpoints

### Generate token

```http
POST /generate-token
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}
```

### Create user

```http
POST /user/
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123",
  "firstname": "Test",
  "lastname": "User",
  "email": "test@example.com",
  "phone": "1234567890"
}
```

### Get all quizzes

```http
GET /quiz/
```

## Notes

- This project appears to be a backend API for an online quiz/exam system.
- Many of the entity models follow a conventional JPA design with `Category`, `Quiz`, and `Question` relationships.
- The codebase is structured around clean separation of layers: controller, service, repository, and model.
- The project is suitable for learning JWT authentication, Spring Security, and REST API design with Spring Boot.

## Main Dependencies

From `build.gradle`:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'mysql:mysql-connector-java:8.0.33'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springdoc:springdoc-openapi-ui:1.6.15'
implementation 'org.springframework.boot:spring-boot-starter-security:2.5.7'
implementation 'io.jsonwebtoken:jjwt:0.9.0'
implementation 'javax.xml.bind:jaxb-api:2.3.1'
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'io.micrometer:micrometer-registry-prometheus:1.12.9'
```

## Future Improvements

Possible enhancements for this project include:

- Role-based access restrictions for admin-only endpoints
- Unit and integration tests for controllers/services
- Validation annotations on request DTOs
- Better exception handling and standardized API responses
- Frontend integration for quiz-taking experience
- CI/CD pipeline and deployment configuration

## License

No explicit license file was found in the repository. If you plan to distribute or reuse this project commercially, it is recommended to add an appropriate OSS license such as MIT or Apache 2.0.

## Repository Summary

This repository is a Spring Boot quiz management application designed around user authentication, quiz operations, and observability. It is well-suited for backend learning, demo projects, and practical API development with Java and Spring Boot.

---

If you want, I can also create a more polished version of this README specifically tailored for GitHub with badges, screenshots, table of contents, and API examples for your exact project.
