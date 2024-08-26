package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.User;

public interface IUserRepository {

    User getUserByUserId(int userId);
    User update(int userId, User user);

}
