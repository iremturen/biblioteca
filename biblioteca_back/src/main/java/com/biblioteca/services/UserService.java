package com.biblioteca.services;

import com.biblioteca.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class UserService implements IUserService {
}
