package com.biblioteca.services.interfaces;

import com.biblioteca.models.Book;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IBookService {

    List<Book> getAllBooks();
    Book getBookByBookId(Integer bookId);
    List<Book> getNewReleases();
    List<Book> search(String pattern);
}
