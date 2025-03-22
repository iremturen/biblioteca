package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.UserBooks;

import java.util.List;

public interface IUserBooksRepository {

    List<UserBooks> getBooksByStatus(Integer userId, Integer status, String pattern);

    Integer getCountByStatus(Integer userId, String status);

    Integer updateProgress(Integer userId, Integer bookId, Integer pageNum);

    void removeBook(Integer bookId, Integer userId, Integer type);

    void addBookByStatus(Integer bookId, Integer userId, Integer status);

}
