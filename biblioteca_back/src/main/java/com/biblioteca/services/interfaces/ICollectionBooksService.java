package com.biblioteca.services.interfaces;

import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ICollectionBooksService {

    Integer getBookCount(Integer collectionId);
    List<Book> getCollectionsBooks(Integer collectionId, String sortBy, String pattern);
    void addBooksToCollection(Integer collectionId, List<Long> addedBooks);
    void removeBook(CollectionBooks collectionsBooks);

}
