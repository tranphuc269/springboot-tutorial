package com.bookstore.authservice.domain.service;

import com.bookstore.authservice.application.dto.request.CreateRoleRequest;
import com.bookstore.authservice.infrastructure.entity.RoleEntity;

import java.util.List;

public interface RoleService {

  String createRole(CreateRoleRequest createRoleRequest);

  List<RoleEntity> getAllRoles();
}