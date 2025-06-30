# Blog Platform Backend

A backend REST API for a blogging platform built with **Spring Boot**, supporting post management with categories and tags, role-based content access using **JWT authentication**, and a clean architecture using **DTOs**, **MapStruct**, and **Lombok**.

---

## Tech Stack

- **Java 21**
- **Spring Boot**
- **Spring Security (JWT)**
- **MapStruct**
- **Lombok**
- **PostgreSQL**
- **Maven**
- **JPA/Hibernate**

---

## Features

- JWT-based Authentication
- CRUD operations for:
  - Posts (with reading time, draft/published status)
  - Tags (Many-to-Many with posts)
  - Categories (One-to-Many with posts)
- DTO-layer abstraction with MapStruct
- Secure access to drafts (only visible to authenticated users)
- Role-based access control planned
- Proper exception handling and validation
- Future plans:
  - User registration & role system
  - Swagger/OpenAPI documentation
  - Frontend integration

---

## Authentication

- JWT is used to protect private content like draft posts and secured endpoints.
- Token expiry is set to **24 hours**.
- Login API returns the JWT on successful authentication.

---

## API Endpoints

| Resource     | Method | Endpoint                         | Description                             | Auth Required |
|--------------|--------|----------------------------------|-----------------------------------------|----------------|
| **Auth**     | POST   | `/api/v1/auth/login`            | Login and receive JWT token             | ❌             |
| **Categories** | GET  | `/api/v1/categories`            | Get all categories                      | ❌             |
|              | POST   | `/api/v1/categories`            | Create a new category                   | ✅             |
|              | DELETE | `/api/v1/categories/{id}`       | Delete a category by ID                 | ✅             |
| **Tags**     | GET    | `/api/v1/tags`                  | Get all tags                            | ❌             |
|              | POST   | `/api/v1/tags`                  | Create a new tag                        | ✅             |
|              | DELETE | `/api/v1/tags/{id}`             | Delete a tag by ID                      | ✅             |
| **Posts**    | GET    | `/api/v1/posts`                 | Get all published posts                 | ❌             |
|              | GET    | `/api/v1/posts/{id}`            | Get post by ID                          | ❌             |
|              | GET    | `/api/v1/posts/drafts`          | Get all draft posts of logged-in user   | ✅             |
|              | POST   | `/api/v1/posts`                 | Create a new post                       | ✅             |
|              | PUT    | `/api/v1/posts/{id}`            | Update a post by ID                     | ✅             |
|              | DELETE | `/api/v1/posts/{id}`            | Delete a post by ID                     | ✅             |

---

## Setup & Run Locally

### Prerequisites

- Java 21
- PostgreSQL installed and running
- Maven installed

### Steps

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/ZRishu/blog-platform.git
   cd blog-platform
   ```

2. **Configure Environment Variables**  
   Create a `.env` or use `application.properties` to set:
   ```properties
   DB_URL=jdbc:postgresql://your_database_url
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   JWT_SECRET=your_jwt_secret_key
   ```

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## Frontend Integration Guide

- Use the `/api/v1/auth/login` endpoint to authenticate users and store the JWT in local storage or memory.
- Include the `Authorization: Bearer <token>` header in requests to protected endpoints like drafts, create, or delete.
- Structure frontend routes based on post status (`/posts` for public, `/drafts` for user dashboard).

---

## To-Do (Planned Features)

- [ ] User registration system
- [ ] Role-based access (ADMIN, AUTHOR, READER)
- [ ] Swagger/OpenAPI documentation
- [ ] Deployment on cloud (Render, Railway, etc.)
- [ ] Frontend integration (React/Next.js)

---
