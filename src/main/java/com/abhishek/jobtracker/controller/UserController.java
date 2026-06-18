package com.abhishek.jobtracker.controller;

import com.abhishek.jobtracker.dto.CreateUserRequest;
import com.abhishek.jobtracker.dto.UserResponse;
import com.abhishek.jobtracker.entity.User;
import com.abhishek.jobtracker.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse createUser(
        @Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}