package com.bookstore.orderservice.order_item.command.api.events.handler;

import com.bookstore.orderservice.order_item.command.api.events.CreateOrderItemEvent;
import com.bookstore.orderservice.order_item.command.model.OrderItemCommandRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderItemHandler implements IOrderItemHandler {

    @Autowired
    private OrderItemCommandRepository repository;


    @EventHandler
    @Override
    public void on(CreateOrderItemEvent event) {
        repository.saveAll(event.getEntities());
    }
}
