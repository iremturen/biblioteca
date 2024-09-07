package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.UserBooks;

import java.util.List;

public interface IUserBooksRepository {

    List<UserBooks> getNowReading(int userId);
    Integer getCountNowReading(int userId);
    List<UserBooks> getWillRead(int userId);
    Integer getCountWillRead(int userId);
    List<UserBooks> getFinished(int userId);
    Integer getCountFinished(int userId);
}
