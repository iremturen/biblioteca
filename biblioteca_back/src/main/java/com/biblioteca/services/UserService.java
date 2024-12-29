package com.biblioteca.services;

import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IUserRepository;
import com.biblioteca.requests.UserRegisterRequest;
import com.biblioteca.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository usersRepository;

    @Override
    public User getUserByUserId(Integer userId) {
        return usersRepository.getUserByUserId(userId);
    }

    @Override
    public User update(Integer userId, User user) {
        return usersRepository.update(userId,user);
    }

    @Override
    public List<String> register(UserRegisterRequest request) {
        return usersRepository.register(request);
    }
}
