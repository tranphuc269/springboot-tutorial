package com.bookstore.authservice.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserDetailsService extends UserDetailsService {

  @Override
  UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException;

}