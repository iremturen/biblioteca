package com.biblioteca.services.interfaces;

import com.biblioteca.models.UserBooks;
import com.biblioteca.services.UserBooksService;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IUserBooksService {

    List<UserBooks> getNowReading(int userId);
    Integer getCountNowReading(int userId);
    List<UserBooks> getWillRead(int userId);
    Integer getCountWillRead(int userId);
    List<UserBooks> getFinished(int userId);
    Integer getCountFinished(int userId);
}
