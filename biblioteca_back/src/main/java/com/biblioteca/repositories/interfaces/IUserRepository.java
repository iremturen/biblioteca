package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.User;

public interface IUserRepository {

    User getUserByUserId(Integer userId);
    User update(Integer userId, User user);

}
