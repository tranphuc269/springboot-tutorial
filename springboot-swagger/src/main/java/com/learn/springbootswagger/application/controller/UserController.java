package com.learn.springbootswagger.application.controller;


import com.learn.springbootswagger.application.request.dto.CreateUserRequest;
import com.learn.springbootswagger.application.response.dto.UserResponse;
import com.learn.springbootswagger.application.response.dto.base.BaseResponse;
import com.learn.springbootswagger.infrastructure.repo.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@Api(value = "User APIs")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("")
    public BaseResponse<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return BaseResponse.ofSucceeded(userService.createUser(createUserRequest));
    }

    @GetMapping("")
    public BaseResponse<List<UserResponse>> getAllUsers() {
        return BaseResponse.ofSucceeded(userService.getUsers());
    }
}
