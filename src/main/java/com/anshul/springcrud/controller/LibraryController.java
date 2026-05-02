package com.anshul.springcrud.controller;

import com.anshul.springcrud.entity.Author;
import com.anshul.springcrud.entity.Book;
import com.anshul.springcrud.service.AuthorService;
import com.anshul.springcrud.service.BookService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LibraryController {
    private final AuthorService authorService;
    private final BookService bookService;

    public LibraryController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping({"/", "/books"})
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("bookAuthorDetails", bookService.findBookAuthorDetails());
        return "books";
    }

    @GetMapping("/books/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books")
    public String createBook(@Valid @ModelAttribute Book book,
                             BindingResult bindingResult,
                             @RequestParam Long authorId,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "book-form";
        }
        try {
            bookService.save(book, authorId);
            redirectAttributes.addFlashAttribute("success", "Book added successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "Could not save book. ISBN must be unique and author must be valid.");
            model.addAttribute("authors", authorService.findAll());
            return "book-form";
        }
    }

    @GetMapping("/books/{id}/edit")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-edit";
    }

    @PostMapping("/books/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute Book book,
                             BindingResult bindingResult,
                             @RequestParam Long authorId,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "book-edit";
        }
        try {
            bookService.update(id, book, authorId);
            redirectAttributes.addFlashAttribute("success", "Book updated successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "Could not update book. ISBN must remain unique.");
            model.addAttribute("authors", authorService.findAll());
            return "book-edit";
        }
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @GetMapping("/authors/new")
    public String newAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/authors")
    public String createAuthor(@Valid @ModelAttribute Author author,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "author-form";
        }
        try {
            authorService.save(author);
            redirectAttributes.addFlashAttribute("success", "Author added successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "Could not save author. Email must be unique.");
            return "author-form";
        }
    }

    @GetMapping("/authors/{id}/edit")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author-edit";
    }

    @PostMapping("/authors/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute Author author,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "author-edit";
        }
        try {
            Author existing = authorService.findById(id);
            existing.setName(author.getName());
            existing.setSpecialization(author.getSpecialization());
            existing.setEmail(author.getEmail());
            authorService.save(existing);
            redirectAttributes.addFlashAttribute("success", "Author updated successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "Could not update author. Email must remain unique.");
            return "author-edit";
        }
    }
}
