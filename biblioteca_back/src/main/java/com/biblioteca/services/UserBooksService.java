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
    public List<UserBooks> getNowReading(int userId) {
        return userBooksRepository.getNowReading(userId);
    }

    @Override
    public Integer getCountNowReading(int userId) {
        return userBooksRepository.getCountNowReading(userId);
    }

    @Override
    public List<UserBooks> getWillRead(int userId) {
        return userBooksRepository.getWillRead(userId);
    }

    @Override
    public Integer getCountWillRead(int userId) {
        return userBooksRepository.getCountWillRead(userId);
    }

    @Override
    public List<UserBooks> getFinished(int userId) {
        return userBooksRepository.getFinished(userId);

    }

    @Override
    public Integer getCountFinished(int userId) {
        return userBooksRepository.getCountFinished(userId);
    }
}
