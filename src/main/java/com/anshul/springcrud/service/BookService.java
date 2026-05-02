package com.anshul.springcrud.service;

import com.anshul.springcrud.entity.Author;
import com.anshul.springcrud.entity.Book;
import com.anshul.springcrud.repository.BookAuthorView;
import com.anshul.springcrud.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
    }

    public List<BookAuthorView> findBookAuthorDetails() {
        return bookRepository.findBookAuthorDetails();
    }

    @Transactional
    public Book save(Book book, Long authorId) {
        Author author = authorService.findById(authorId);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book bookDetails, Long authorId) {
        Book book = findById(id);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublishedYear(bookDetails.getPublishedYear());
        book.setAuthor(authorService.findById(authorId));
        return bookRepository.save(book);
    }
}
