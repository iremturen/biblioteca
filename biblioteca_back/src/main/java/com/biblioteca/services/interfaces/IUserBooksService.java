package com.biblioteca.services.interfaces;

import com.biblioteca.models.UserBooks;
import com.biblioteca.services.UserBooksService;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IUserBooksService {

    List<UserBooks> getNowReading(Integer userId);
    Integer getCountNowReading(Integer userId);
    List<UserBooks> getWillRead(Integer userId);
    Integer getCountWillRead(Integer userId);
    List<UserBooks> getFinished(Integer userId);
    Integer getCountFinished(Integer userId);
}
