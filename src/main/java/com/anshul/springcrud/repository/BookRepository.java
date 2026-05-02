package com.anshul.springcrud.repository;

import com.anshul.springcrud.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
            select b.id as bookId,
                   b.title as title,
                   b.isbn as isbn,
                   b.publishedYear as publishedYear,
                   a.name as authorName,
                   a.specialization as specialization
            from Book b
            inner join b.author a
            order by b.id
            """)
    List<BookAuthorView> findBookAuthorDetails();
}
