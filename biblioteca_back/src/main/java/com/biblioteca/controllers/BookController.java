package com.biblioteca.controllers;

import com.biblioteca.models.Book;
import com.biblioteca.services.interfaces.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value="api/books")
public class BookController {

    private IBookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookByBookId(@PathVariable Integer bookId) {
        try {
            return ResponseEntity.ok(bookService.getBookByBookId(bookId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/new_releases")
    public ResponseEntity<List<Book>> getNewReleases() {
        try {
            return ResponseEntity.ok(bookService.getNewReleases());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search/{pattern}")
    public ResponseEntity<List<Book>> search(@PathVariable String pattern) {
        try {
            return ResponseEntity.ok(bookService.search(pattern));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
