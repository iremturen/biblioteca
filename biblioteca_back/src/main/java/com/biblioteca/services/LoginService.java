package com.biblioteca.services;

import com.biblioteca.models.User;
import com.biblioteca.models.UserLogin;
import com.biblioteca.repositories.interfaces.IUserLoginRepository;
import com.biblioteca.repositories.interfaces.IUserRepository;
import com.biblioteca.requests.UserLoginRequest;
import com.biblioteca.models.response.AuthResponse;
import com.biblioteca.services.interfaces.ILoginService;
import com.biblioteca.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Service
@Validated
@AllArgsConstructor
public class LoginService implements ILoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private IUserRepository userRepository;

    @Override
    public AuthResponse login(UserLoginRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Integer userId = userRepository.findUserIdByUsername(request.getUsername());
        Objects.requireNonNull(userId, "User ID not found");

        String token = jwtUtil.generateToken(request.getUsername(), userId);
        return new AuthResponse(token, userId);

    }
}
