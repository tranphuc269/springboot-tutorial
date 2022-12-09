package com.learn.springbootswagger.infrastructure.repo;

import com.learn.springbootswagger.infrastructure.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public  interface JpaUserRepository extends JpaRepository<User, Long> {
}
