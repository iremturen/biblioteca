package com.biblioteca.services.interfaces;

import com.biblioteca.models.UserBooks;
import com.biblioteca.services.UserBooksService;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IUserBooksService {

    List<UserBooks> getBooksByStatus(Integer userId, String status);
    Integer getCountByStatus(Integer userId, String status);
    Integer updateProgress(Integer userId, Integer bookId, Integer pageNum);

    List<UserBooks> search(Integer userId,Integer type,String pattern);
}
