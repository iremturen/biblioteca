package com.biblioteca.services.interfaces;

import com.biblioteca.enums.FavoriteStatus;
import com.biblioteca.models.FavoriteBooks;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface IFavoriteBooksService {

    List<FavoriteBooks> getFavorites(Integer userId);
    void remove(Integer userId, Integer bookId);
    void addBook(Integer userId, Integer bookId);

}
