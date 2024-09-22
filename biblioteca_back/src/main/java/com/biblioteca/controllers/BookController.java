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
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookByBookId(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.getBookByBookId(bookId));
    }

    @GetMapping("/new_releases")
    public ResponseEntity<List<Book>> getNewReleases() {
        return ResponseEntity.ok(bookService.getNewReleases());
    }

    @GetMapping("/search/{pattern}")
    public ResponseEntity<List<Book>> search(@PathVariable String pattern) {
        return ResponseEntity.ok(bookService.search(pattern));
    }


}
