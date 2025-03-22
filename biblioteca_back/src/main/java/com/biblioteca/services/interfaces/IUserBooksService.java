package com.biblioteca.services.interfaces;

import com.biblioteca.models.UserBooks;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IUserBooksService {

    List<UserBooks> getBooksByStatus(Integer userId, Integer status, String pattern);

    Integer getCountByStatus(Integer userId, String status);

    Integer updateProgress(Integer userId, Integer bookId, Integer pageNum);

    void removeBook(Integer bookId, Integer userId, Integer type);

    void addBookByStatus(Integer bookId, Integer userId, Integer status);
}
