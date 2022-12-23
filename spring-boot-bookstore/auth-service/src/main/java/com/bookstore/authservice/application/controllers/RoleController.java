package com.bookstore.authservice.application.controllers;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import com.bookstore.authservice.application.dto.request.CreateRoleRequest;
import com.bookstore.authservice.domain.service.RoleService;
import com.bookstore.authservice.infrastructure.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class RoleController {


  private RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService){
    this.roleService = roleService;
  }

  @PostMapping("/role")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> createRole(@RequestBody @Valid CreateRoleRequest createRoleRequest) {

    String userId = roleService.createRole(createRoleRequest);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{roleId}")
        .buildAndExpand(userId).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/roles")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> getAllRoles() {
    List<RoleEntity> allRoles = roleService.getAllRoles();
    return ResponseEntity.ok(allRoles);

  }
}