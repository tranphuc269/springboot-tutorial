package com.bookstore.authservice.domain.service;

import com.bookstore.authservice.application.dto.request.MapRoleToUsersRequest;
import com.bookstore.authservice.application.dto.request.MapUserToRolesRequest;

public interface UserRoleService {

  void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

  void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

  void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest);
}