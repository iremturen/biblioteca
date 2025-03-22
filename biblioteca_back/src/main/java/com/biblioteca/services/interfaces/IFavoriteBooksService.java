package com.biblioteca.services.interfaces;

import com.biblioteca.models.Book;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface IFavoriteBooksService {

    List<Book> getFavorites(Integer userId);
    void remove(Integer userId, Integer bookId);
    void addBook(Integer userId, Integer bookId);
    boolean isFavorite(Integer bookId, Integer userId);

}
