package com.biblioteca.services.interfaces;

import com.biblioteca.models.User;
import com.biblioteca.requests.UserRegisterRequest;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IUserService {

    User getUserByUserId(Integer userId);
    User update(Integer userId, User user);
    List<String> register(UserRegisterRequest request);
}
