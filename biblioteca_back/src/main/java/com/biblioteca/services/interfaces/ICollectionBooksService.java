package com.biblioteca.services.interfaces;

import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ICollectionBooksService {

    Integer getBookCount(Integer collectionId);
    List<Book> getCollectionsBooks(Integer collectionId);
    List<Book> search(Integer collectionId, String pattern);
    List<Book> sortBy(Integer collectionId, String sortBy, String order);
    void addBooksToCollection(Integer collectionId, List<Long> addedBooks);

}
