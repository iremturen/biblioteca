package com.biblioteca.services;

import com.biblioteca.models.UserBooks;
import com.biblioteca.repositories.interfaces.IUserBooksRepository;
import com.biblioteca.services.interfaces.IUserBooksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class UserBooksService implements IUserBooksService {

    private IUserBooksRepository userBooksRepository;


    @Override
    public List<UserBooks> getBooksByStatus(Integer userId, String status) {
        return userBooksRepository.getBooksByStatus(userId, status);
    }

    @Override
    public Integer getCountByStatus(Integer userId, String status) {
        return userBooksRepository.getCountByStatus(userId, status);
    }

    @Override
    public Integer updateProgress(Integer userId, Integer bookId, Integer pageNum) {
        return userBooksRepository.updateProgress(userId,bookId, pageNum);
    }

    @Override
    public List<UserBooks> search(Integer userId, Integer type, String pattern) {
        return userBooksRepository.search(userId,type,pattern);
    }

    @Override
    public UserBooks removeBook(Integer bookId, Integer userId, String type) {
        return userBooksRepository.removeBook(bookId, userId, type);
    }
}
