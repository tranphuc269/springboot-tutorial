package com.bookstore.authservice.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.bookstore.authservice.common.response.CommonResult;
import com.bookstore.authservice.service.UserService;
import com.bookstore.authservice.vo.request.CreateUserRequest;
import com.bookstore.authservice.vo.request.UpdateUserRequest;
import com.bookstore.authservice.vo.request.UpdateUserRequestFromAdmin;
import com.bookstore.authservice.vo.response.GetUserInfoResponse;
import com.bookstore.authservice.vo.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import de.alpharogroup.service.rs.api.RestfulResource;


@RestController
@CrossOrigin
public class UserController {


  private UserService userService;

  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  @PostMapping("/user")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

    String userId = userService.createUser(createUserRequest);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{userId}")
        .buildAndExpand(userId).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/user")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<GetUserResponse> getUser(
      @RequestParam("userName") Optional<String> userName
      , @RequestParam("userId") Optional<String> userId) {

    GetUserResponse user = null;
    if (userName.isPresent()) {
      user = userService.getUserByUserName(userName.get());
    } else if (userId.isPresent()) {
      user = userService.getUserByUserId(userId.get());
    }
    return ResponseEntity.ok(user);
  }

  @PutMapping("/user/{userId}")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> updateUser(@PathVariable("userId") String userId,
                                      @RequestBody @Valid UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

    userService.updateUser(userId,updateUserRequestFromAdmin);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public CommonResult<List<GetUserResponse>> getAllUsers() {
    List<GetUserResponse> allUsers = userService.getAllUsers();
    return CommonResult.success(allUsers);
  }

  @GetMapping("/userInfo")
  public CommonResult<GetUserInfoResponse> getUserInfo() {
    GetUserInfoResponse userInfo = userService.getUserInfo();
    return CommonResult.success(userInfo);
  }

  @PutMapping("/userInfo")
  public ResponseEntity<?> updateUserInfo(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
    userService.updateUserInfo(updateUserRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/user/{userId}")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> deleteUserById(@PathVariable("userId") String userId){
    userService.deleteUserById(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}