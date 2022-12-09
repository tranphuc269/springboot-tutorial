package com.learn.springbootswagger.infrastructure.repo.service.impl;

import com.learn.springbootswagger.application.request.dto.CreateUserRequest;
import com.learn.springbootswagger.application.response.dto.UserResponse;
import com.learn.springbootswagger.infrastructure.repo.JpaUserRepository;
import com.learn.springbootswagger.infrastructure.repo.model.User;
import com.learn.springbootswagger.infrastructure.repo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User userCreate = userRepository.save(User
                .builder()
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .build());
        return UserResponse
                .builder()
                .userId(userCreate.getId())
                .email(userCreate.getEmail())
                .firstName(userCreate.getFirstName())
                .lastName(userCreate.getLastName())
                .build();
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user -> UserResponse
                .builder()
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build())).toList();
    }
}
