package com.bookstore.authservice.infrastructure.repository;

import com.bookstore.authservice.infrastructure.entity.OAuthClient;
import org.springframework.data.repository.CrudRepository;

public interface OAuthClientRepository extends CrudRepository<OAuthClient, Long> {

}