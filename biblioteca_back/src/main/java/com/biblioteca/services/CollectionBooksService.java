package com.biblioteca.services;

import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;
import com.biblioteca.repositories.interfaces.IBookRepository;
import com.biblioteca.repositories.interfaces.ICollectionBooksRepository;
import com.biblioteca.services.interfaces.ICollectionBooksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class CollectionBooksService implements ICollectionBooksService {

    private ICollectionBooksRepository collectionBooksRepository;
    private IBookRepository bookRepository;

    @Override
    public Integer getBookCount(Integer collectionId) {
        return collectionBooksRepository.getBookCount(collectionId);
    }

    @Override
    public List<Book> getCollectionsBooks(Integer collectionId, String sortBy, String pattern) {
        return bookRepository.getCollectionsBooks(collectionId, sortBy, pattern);
    }

    @Override
    public void addBooksToCollection(Integer collectionId, List<Long> addedBooks) {
        collectionBooksRepository.addBooksToCollection(collectionId, addedBooks);
    }

    @Override
    public void removeBook(CollectionBooks collectionsBooks) {
        collectionBooksRepository.removeBook(collectionsBooks);
    }
}
