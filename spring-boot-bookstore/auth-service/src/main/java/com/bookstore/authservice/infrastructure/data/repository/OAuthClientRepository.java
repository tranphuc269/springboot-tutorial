package com.bookstore.authservice.infrastructure.data.repository;

import com.bookstore.authservice.infrastructure.data.entity.OAuthClient;
import org.springframework.data.repository.CrudRepository;

public interface OAuthClientRepository extends CrudRepository<OAuthClient, Long> {

}