package com.bookstore.orderservice.application.es.cart.query.model;

import com.bookstore.orderservice.application.es.cart.query.model.entity.CartQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartQueryRepository extends JpaRepository<CartQueryEntity, String> {
}
