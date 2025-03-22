package com.biblioteca.repositories.interfaces;

public interface IFavoriteBooksRepository {
    void remove(Integer userId, Integer bookId);

    void addBook(Integer userId, Integer bookId);

    boolean isFavorite(Integer bookId, Integer userId);

}
