package com.bookstore.orderservice.cart.command.model;

import com.bookstore.orderservice.cart.command.model.entity.CartCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartCommandRepository extends JpaRepository<CartCommandEntity, String> {
}
