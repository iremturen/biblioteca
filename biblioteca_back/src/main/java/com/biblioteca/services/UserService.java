package com.biblioteca.services;

import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IUserRepository;
import com.biblioteca.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository usersRepository;

    @Override
    public User getUserByUserId(int userId) {
        return usersRepository.getUserByUserId(userId);
    }

    @Override
    public User update(int userId, User user) {
        return usersRepository.update(userId,user);
    }
}
