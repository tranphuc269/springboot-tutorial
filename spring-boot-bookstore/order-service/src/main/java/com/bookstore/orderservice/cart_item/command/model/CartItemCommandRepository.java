package com.bookstore.orderservice.cart_item.command.model;

import com.bookstore.orderservice.cart_item.command.model.entity.CartItemCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemCommandRepository extends JpaRepository<CartItemCommandEntity, String> {
    void deleteAllByCartId(String cartId);
}
