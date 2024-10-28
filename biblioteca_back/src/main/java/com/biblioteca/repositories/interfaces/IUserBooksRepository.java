package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.UserBooks;

import java.util.List;

public interface IUserBooksRepository {

    List<UserBooks> getNowReading(Integer userId);
    Integer getCountNowReading(Integer userId);
    List<UserBooks> getWillRead(Integer userId);
    Integer getCountWillRead(Integer userId);
    List<UserBooks> getFinished(Integer userId);
    Integer getCountFinished(Integer userId);
    Integer updateProgress(Integer userId, Integer bookId, Integer pageNum);
    List<UserBooks> search(Integer userId,Integer type, String pattern);

}
