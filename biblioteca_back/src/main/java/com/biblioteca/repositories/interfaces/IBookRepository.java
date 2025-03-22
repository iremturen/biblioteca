package com.biblioteca.repositories.interfaces;

import java.util.List;
import com.biblioteca.models.Book;

public interface IBookRepository {

    List<Book> getAllBooks(String pattern);

    Book getBookByBookId(Integer bookId);

    List<Book> getNewReleases();

    List<Book> getFavorites(Integer userId);

    List<Book> getCollectionsBooks(Integer collectionId, String sortBy, String pattern);

    List<Book> sortBy(Integer collectionId, String sortBy, String order);
}
