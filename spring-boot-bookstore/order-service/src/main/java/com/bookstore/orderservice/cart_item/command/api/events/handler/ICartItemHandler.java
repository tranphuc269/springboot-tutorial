package com.bookstore.orderservice.cart_item.command.api.events.handler;

import com.bookstore.orderservice.cart_item.command.api.events.AddCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveAllCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveCartItemEvent;

public interface ICartItemHandler {
    public void on(AddCartItemEvent event);

    public void on(RemoveCartItemEvent event);

    public void on(RemoveAllCartItemEvent event);
}
