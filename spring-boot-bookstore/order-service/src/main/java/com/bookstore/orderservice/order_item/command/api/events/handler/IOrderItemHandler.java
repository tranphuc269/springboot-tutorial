package com.bookstore.orderservice.order_item.command.api.events.handler;

import com.bookstore.orderservice.order_item.command.api.events.CreateOrderItemEvent;

public interface IOrderItemHandler {
    public void on(CreateOrderItemEvent event);
}
