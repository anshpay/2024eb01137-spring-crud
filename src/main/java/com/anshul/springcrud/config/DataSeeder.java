package com.anshul.springcrud.config;

import com.anshul.springcrud.entity.Author;
import com.anshul.springcrud.entity.Book;
import com.anshul.springcrud.repository.AuthorRepository;
import com.anshul.springcrud.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataSeeder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0 || bookRepository.count() > 0) {
            return;
        }

        List<Author> authors = authorRepository.saveAll(List.of(
                new Author("Aarav Mehta", "Distributed Systems", "aarav.mehta@example.com"),
                new Author("Isha Sharma", "Database Design", "isha.sharma@example.com"),
                new Author("Rohan Kapoor", "Cloud Computing", "rohan.kapoor@example.com"),
                new Author("Neha Verma", "Software Testing", "neha.verma@example.com"),
                new Author("Karan Singh", "Cyber Security", "karan.singh@example.com"),
                new Author("Priya Nair", "Machine Learning", "priya.nair@example.com"),
                new Author("Vikram Rao", "Web Engineering", "vikram.rao@example.com"),
                new Author("Meera Iyer", "Data Structures", "meera.iyer@example.com"),
                new Author("Kabir Khan", "Microservices", "kabir.khan@example.com"),
                new Author("Ananya Bose", "DevOps", "ananya.bose@example.com")
        ));

        bookRepository.saveAll(List.of(
                new Book("Spring Boot in Practice", "ISBN-2024-001", 2021, authors.get(0)),
                new Book("Relational Models", "ISBN-2024-002", 2020, authors.get(1)),
                new Book("Cloud Native Java", "ISBN-2024-003", 2022, authors.get(2)),
                new Book("JUnit Essentials", "ISBN-2024-004", 2019, authors.get(3)),
                new Book("Secure Web Apps", "ISBN-2024-005", 2023, authors.get(4)),
                new Book("Applied ML Systems", "ISBN-2024-006", 2021, authors.get(5)),
                new Book("Modern JSP MVC", "ISBN-2024-007", 2018, authors.get(6)),
                new Book("Algorithms Refresher", "ISBN-2024-008", 2020, authors.get(7)),
                new Book("Microservice Patterns", "ISBN-2024-009", 2022, authors.get(8)),
                new Book("DevOps Playbook", "ISBN-2024-010", 2023, authors.get(9))
        ));
    }
}
