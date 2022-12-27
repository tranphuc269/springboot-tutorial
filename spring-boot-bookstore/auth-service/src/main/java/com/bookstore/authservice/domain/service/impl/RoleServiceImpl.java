package com.bookstore.authservice.domain.service.impl;

import com.bookstore.authservice.application.dto.request.CreateRoleRequest;
import com.bookstore.authservice.domain.service.RoleService;
import com.bookstore.authservice.infrastructure.data.entity.RoleEntity;
import com.bookstore.authservice.infrastructure.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {


  private final RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository){
    this.roleRepository = roleRepository;
  }

  @Override
  public String createRole(CreateRoleRequest createRoleRequest) {

    RoleEntity role = RoleEntity.builder()
        .roleName(createRoleRequest.getRoleName())
        .roleDescription(createRoleRequest.getRoleDescription())
        .build();

    RoleEntity savedRole = roleRepository.save(role);
    return savedRole.getId();
  }

  @Override
  public List<RoleEntity> getAllRoles() {
    List<RoleEntity> allRoles = roleRepository.findAll();
    return allRoles;
  }
}