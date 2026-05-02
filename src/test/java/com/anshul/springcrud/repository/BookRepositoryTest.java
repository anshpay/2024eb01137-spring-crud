package com.anshul.springcrud.repository;

import com.anshul.springcrud.config.DataSeeder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DataSeeder.class)
class BookRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void seedDataCreatesTenRowsInEachTable() {
        assertThat(authorRepository.count()).isEqualTo(10);
        assertThat(bookRepository.count()).isEqualTo(10);
    }

    @Test
    void innerJoinQueryReturnsBookAuthorDetails() {
        assertThat(bookRepository.findBookAuthorDetails())
                .hasSize(10)
                .allSatisfy(row -> {
                    assertThat(row.getTitle()).isNotBlank();
                    assertThat(row.getAuthorName()).isNotBlank();
                    assertThat(row.getSpecialization()).isNotBlank();
                });
    }
}
