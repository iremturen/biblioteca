package com.biblioteca.services.interfaces;

import com.biblioteca.models.User;
import org.springframework.validation.annotation.Validated;

@Validated
public interface IUserService {

    User getUserByUserId(int userId);

    User update(int userId, User user);
}
