package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.FavoriteBooks;

import java.util.List;

public interface IFavoriteBooksRepository {

    List<FavoriteBooks> getFavorites(Integer userId);
    void remove(Integer userId, Integer bookId);
    void addBook(Integer userId, Integer bookId);


}
