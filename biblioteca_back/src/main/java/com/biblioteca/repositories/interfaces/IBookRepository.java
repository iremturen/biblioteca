package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.Book;

import java.util.List;

public interface IBookRepository {

    List<Book> getAllBooks();
}
