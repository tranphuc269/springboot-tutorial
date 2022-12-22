package com.bookstore.orderservice.order_item.query.model;

import com.bookstore.orderservice.order_item.query.model.entity.OrderItemQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemQueryRepository extends JpaRepository<OrderItemQueryEntity,
        String> {
    List<OrderItemQueryEntity> findAllByOrderId(String orderId);
}
