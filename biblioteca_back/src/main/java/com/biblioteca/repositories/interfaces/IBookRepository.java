package com.biblioteca.repositories.interfaces;
import java.util.List;
import com.biblioteca.models.Book;

public interface IBookRepository {

    List<Book> getAllBooks();
    Book getBookByBookId(Integer bookId);
    List<Book> getNewReleases();
    List<Book> search(String pattern);
    List<Book> getFavorites(Integer userId);
    List<Book> getCollectionsBooks(Integer collectionId);
    List<Book> searchInCollection(Integer collectionId, String pattern);
    List<Book> sortBy(Integer collectionId, String sortBy, String order);

}
