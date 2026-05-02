package com.anshul.springcrud.service;

import com.anshul.springcrud.entity.Author;
import com.anshul.springcrud.entity.Book;
import com.anshul.springcrud.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void saveAttachesAuthorBeforePersisting() {
        Author author = new Author("Test Author", "Testing", "test.author@example.com");
        Book book = new Book("Test Book", "ISBN-T-1", 2024, null);

        when(authorService.findById(1L)).thenReturn(author);
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = bookService.save(book, 1L);

        assertThat(saved.getAuthor()).isEqualTo(author);
        verify(bookRepository).save(book);
    }

    @Test
    void updateChangesExistingBookFields() {
        Author oldAuthor = new Author("Old Author", "Old", "old@example.com");
        Author newAuthor = new Author("New Author", "New", "new@example.com");
        Book existing = new Book("Old Title", "ISBN-OLD", 2020, oldAuthor);
        Book incoming = new Book("New Title", "ISBN-NEW", 2025, null);

        when(bookRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(authorService.findById(2L)).thenReturn(newAuthor);
        when(bookRepository.save(existing)).thenReturn(existing);

        Book updated = bookService.update(5L, incoming, 2L);

        assertThat(updated.getTitle()).isEqualTo("New Title");
        assertThat(updated.getIsbn()).isEqualTo("ISBN-NEW");
        assertThat(updated.getPublishedYear()).isEqualTo(2025);
        assertThat(updated.getAuthor()).isEqualTo(newAuthor);
    }
}
