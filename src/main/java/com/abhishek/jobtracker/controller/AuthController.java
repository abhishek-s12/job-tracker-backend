package com.abhishek.jobtracker.controller;

import com.abhishek.jobtracker.dto.LoginRequest;
import com.abhishek.jobtracker.dto.LoginResponse;
import com.abhishek.jobtracker.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        String token = authService.login(request);

        return new LoginResponse(token);
    }
}