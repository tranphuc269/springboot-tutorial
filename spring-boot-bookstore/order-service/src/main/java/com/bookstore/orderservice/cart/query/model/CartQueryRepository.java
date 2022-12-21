package com.bookstore.orderservice.cart.query.model;

import com.bookstore.orderservice.cart.query.model.entity.CartQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartQueryRepository extends JpaRepository<CartQueryEntity, String> {
}
