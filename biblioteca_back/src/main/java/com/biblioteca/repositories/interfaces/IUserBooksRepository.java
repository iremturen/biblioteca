package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.UserBooks;

import java.util.List;

public interface IUserBooksRepository {

    List<UserBooks> getBooksByStatus(Integer userId, String status);
    Integer getCountByStatus(Integer userId, String status);
    Integer updateProgress(Integer userId, Integer bookId, Integer pageNum);
    List<UserBooks> search(Integer userId,Integer type, String pattern);

}
