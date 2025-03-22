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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class LoginService implements ILoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private IUserRepository userRepository;
    private IUserLoginRepository userLoginRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(UserLoginRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            Integer userId = userRepository.findUserIdByUsername(request.getUsername());
            if (userId == null) {
                throw new BadCredentialsException("User not found");
            }

            User user = userRepository.getUserByUserId(userId);
            if (user == null) {
                throw new BadCredentialsException("User details not found");
            }

            String token = jwtUtil.generateToken(request.getUsername(), userId, user.getEmail());
            return new AuthResponse(token, userId, user.getEmail());

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public void changePassword(String newPassword, String currentPassword, String token) {
        String username = jwtUtil.getUserFromToken(token);
        UserLogin userLogin = userLoginRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(currentPassword, userLogin.getPassword())) {
            throw new BadCredentialsException("Old password is incorrect.");
        }

        userLogin.setPassword(passwordEncoder.encode(newPassword));
        userLoginRepository.save(userLogin);
    }
}
