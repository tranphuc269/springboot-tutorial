package com.bookstore.authservice.application.controllers;

import com.bookstore.authservice.application.dto.request.CreateOAuthClientRequest;
import com.bookstore.authservice.application.dto.request.SignUpRequest;
import com.bookstore.authservice.application.dto.response.CreateOAuthClientResponse;
import com.bookstore.authservice.application.dto.response.CreateUserResponse;
import com.bookstore.authservice.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController {


  private AuthService authService;

  @Autowired
  public AuthController(AuthService authService){
    this.authService = authService;
  }


  @PostMapping("/client")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<CreateOAuthClientResponse> createOAuthClient(
      @Valid @RequestBody CreateOAuthClientRequest createOAuthClientRequest) {

    CreateOAuthClientResponse oAuthClient = authService.createOAuthClient(createOAuthClientRequest);
    return new ResponseEntity<>(oAuthClient, HttpStatus.CREATED);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

    CreateUserResponse createUserResponse = authService.registerUser(signUpRequest);

    return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
  }
}