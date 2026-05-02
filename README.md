# Spring Boot CRUD Assignment

Student: Anshul Patyal  
Roll number: 2024eb01137

This project implements Question 1 using Spring Boot, Spring MVC with JSP, Spring Data JPA, and H2.

## Entities

- `Author`: id, name, specialization, email
- `Book`: id, title, isbn, publishedYear, author

Relationship: one author can have many books; each book belongs to one author.

## Run

```bash
mvn spring-boot:run
```

Open `http://localhost:8080/books`.

## Test

```bash
mvn test
```
