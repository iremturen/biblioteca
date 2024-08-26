package com.biblioteca.services;

import com.biblioteca.models.Book;
import com.biblioteca.repositories.interfaces.IBookRepository;
import com.biblioteca.services.interfaces.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class BookService implements IBookService {

    private IBookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
