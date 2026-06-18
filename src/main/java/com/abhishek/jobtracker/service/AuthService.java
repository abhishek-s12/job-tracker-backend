package com.abhishek.jobtracker.service;

import com.abhishek.jobtracker.dto.LoginRequest;
import com.abhishek.jobtracker.entity.User;
import com.abhishek.jobtracker.exception.InvalidCredentialsException;
import com.abhishek.jobtracker.repository.UserRepository;
import com.abhishek.jobtracker.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            JwtService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(() ->
                new InvalidCredentialsException(
                        "Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password");
        }

        return jwtService.generateToken(
                user.getEmail()
        );
    }
}