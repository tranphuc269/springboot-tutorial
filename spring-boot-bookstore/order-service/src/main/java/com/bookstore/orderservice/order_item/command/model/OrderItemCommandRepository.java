package com.bookstore.orderservice.order_item.command.model;

import com.bookstore.orderservice.order_item.command.model.entity.OrderItemCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemCommandRepository extends JpaRepository<OrderItemCommandEntity,
        String> {
}
