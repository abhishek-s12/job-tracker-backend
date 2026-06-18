package com.abhishek.jobtracker.service;

import com.abhishek.jobtracker.dto.CreateUserRequest;
import com.abhishek.jobtracker.dto.UserResponse;
import com.abhishek.jobtracker.entity.User;
import com.abhishek.jobtracker.exception.DuplicateEmailException;
import com.abhishek.jobtracker.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
}

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse createUser(CreateUserRequest request) {


            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new DuplicateEmailException (

                "Email already exists"

            );
}

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}