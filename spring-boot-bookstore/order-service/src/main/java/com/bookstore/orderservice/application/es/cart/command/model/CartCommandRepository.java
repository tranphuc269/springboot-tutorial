package com.bookstore.orderservice.application.es.cart.command.model;

import com.bookstore.orderservice.application.es.cart.command.model.entity.CartCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartCommandRepository extends JpaRepository<CartCommandEntity, String> {
}
