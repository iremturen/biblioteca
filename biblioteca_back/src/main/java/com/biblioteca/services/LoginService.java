package com.biblioteca.services;

import com.biblioteca.services.interfaces.ILoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class LoginService implements ILoginService {
}
