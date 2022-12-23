package com.bookstore.authservice.domain.service;


import com.bookstore.authservice.application.dto.request.CreateOAuthClientRequest;
import com.bookstore.authservice.application.dto.request.SignInRequest;
import com.bookstore.authservice.application.dto.request.SignUpRequest;
import com.bookstore.authservice.application.dto.response.CreateOAuthClientResponse;
import com.bookstore.authservice.application.dto.response.CreateUserResponse;
import com.bookstore.authservice.application.dto.response.JwtAuthenticationResponse;

public interface AuthService {

  CreateOAuthClientResponse createOAuthClient(CreateOAuthClientRequest createOAuthClientRequest);

  CreateUserResponse registerUser(SignUpRequest signUpRequest);

  JwtAuthenticationResponse loginUser(SignInRequest signInRequest);
}