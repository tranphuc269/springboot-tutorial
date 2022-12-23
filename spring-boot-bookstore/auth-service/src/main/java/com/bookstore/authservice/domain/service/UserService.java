package com.bookstore.authservice.domain.service;


import com.bookstore.authservice.application.dto.request.CreateUserRequest;
import com.bookstore.authservice.application.dto.request.UpdateUserRequest;
import com.bookstore.authservice.application.dto.request.UpdateUserRequestFromAdmin;
import com.bookstore.authservice.application.dto.response.GetUserInfoResponse;
import com.bookstore.authservice.application.dto.response.GetUserResponse;

import java.util.List;


public interface UserService {

  String createUser(CreateUserRequest createUserRequest);

  GetUserResponse getUserByUserName(String userName);

  GetUserResponse getUserByUserId(String userId);

  GetUserInfoResponse getUserInfo();

  void updateUserInfo(UpdateUserRequest updateUserRequest);

  void deleteUserById(String userId);

  List<GetUserResponse> getAllUsers();

  void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin);
}