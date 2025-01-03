package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.User;
import com.biblioteca.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserLoginRepository extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findByUsername(String username);

}
