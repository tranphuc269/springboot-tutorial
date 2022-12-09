package com.learn.springbootswagger.infrastructure.repo.service;

import com.learn.springbootswagger.application.request.dto.CreateUserRequest;
import com.learn.springbootswagger.application.response.dto.UserResponse;
import com.learn.springbootswagger.infrastructure.repo.model.User;

import java.util.List;

public interface IUserService {
    public UserResponse createUser(CreateUserRequest createUserRequest);

    public List<UserResponse> getUsers();
}
