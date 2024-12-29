package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.User;
import com.biblioteca.requests.UserRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository {

    User getUserByUserId(Integer userId);
    User update(Integer userId, User user);
    List<String> register(UserRegisterRequest request);
}
