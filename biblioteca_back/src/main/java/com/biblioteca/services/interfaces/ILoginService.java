package com.biblioteca.services.interfaces;

import com.biblioteca.requests.UserLoginRequest;
import com.biblioteca.models.response.AuthResponse;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ILoginService {
    AuthResponse login(UserLoginRequest request) throws Exception;
    void changePassword(String newPassword, String currentPassword, String token);
}
