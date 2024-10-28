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
    public List<UserBooks> getNowReading(Integer userId) {
        return userBooksRepository.getNowReading(userId);
    }

    @Override
    public Integer getCountNowReading(Integer userId) {
        return userBooksRepository.getCountNowReading(userId);
    }

    @Override
    public List<UserBooks> getWillRead(Integer userId) {
        return userBooksRepository.getWillRead(userId);
    }

    @Override
    public Integer getCountWillRead(Integer userId) {
        return userBooksRepository.getCountWillRead(userId);
    }

    @Override
    public List<UserBooks> getFinished(Integer userId) {
        return userBooksRepository.getFinished(userId);

    }

    @Override
    public Integer getCountFinished(Integer userId) {
        return userBooksRepository.getCountFinished(userId);
    }

    @Override
    public Integer updateProgress(Integer userId, Integer bookId, Integer pageNum) {
        return userBooksRepository.updateProgress(userId,bookId, pageNum);
    }

    @Override
    public List<UserBooks> search(Integer userId, Integer type, String pattern) {
        return userBooksRepository.search(userId,type,pattern);
    }
}
