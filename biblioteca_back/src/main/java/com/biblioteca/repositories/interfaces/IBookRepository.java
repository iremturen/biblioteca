package com.biblioteca.repositories.interfaces;
import java.util.List;
import com.biblioteca.models.Book;

public interface IBookRepository {

    List<Book> getAllBooks();
    Book getBookByBookId(int bookId);
    List<Book> getNewReleases();


}
