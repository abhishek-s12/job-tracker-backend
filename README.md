# Job Tracker Backend

A production-style Job Tracking System backend built with Spring Boot and PostgreSQL.

## Features

### Authentication
- User Registration
- User Login
- BCrypt Password Hashing
- JWT Token Generation

### Job Management
- Create Jobs
- View Jobs
- Get Job Details

### Applications
- Apply To Jobs
- View Applied Jobs
- View Applicants
- Prevent Duplicate Applications

### Validation & Error Handling
- Request Validation
- Global Exception Handling
- Custom Exceptions

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- JWT

## Project Structure

```text
controller/
service/
repository/
entity/
dto/
security/
exception/
config/
```

## API Endpoints

### Auth

POST /api/auth/login

### Users

GET /api/users
POST /api/users

### Jobs

GET /api/jobs
GET /api/jobs/{id}
POST /api/jobs

### Applications

POST /api/applications/{jobId}/apply/{userId}
GET /api/applications/user/{userId}
GET /api/applications/job/{jobId}
