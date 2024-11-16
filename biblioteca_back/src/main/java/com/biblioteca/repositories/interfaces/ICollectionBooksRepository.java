package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;

import java.util.List;

public interface ICollectionBooksRepository {
    Integer getBookCount(Integer collectionId);
    void addBooksToCollection(Integer collectionId, List<Long> addedBooks);
    void removeBook(CollectionBooks collectionsBooks);


}
