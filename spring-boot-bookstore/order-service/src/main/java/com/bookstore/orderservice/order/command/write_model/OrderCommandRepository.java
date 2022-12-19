package com.bookstore.orderservice.order.command.write_model;

import com.bookstore.orderservice.order.command.write_model.entity.OrderCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderCommandRepository extends JpaRepository<OrderCommandEntity, String> {
}
