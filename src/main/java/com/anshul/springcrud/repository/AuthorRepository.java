package com.anshul.springcrud.repository;

import com.anshul.springcrud.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
