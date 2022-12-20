package com.bookstore.orderservice.cart_item.command.api.events.handler;

import com.bookstore.orderservice.cart_item.command.api.events.CreateCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveAllCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.UpdateCartItemEvent;

public interface ICartItemHandler {
    public void on(CreateCartItemEvent event);

    public void on(RemoveCartItemEvent event);

    public void on(UpdateCartItemEvent event);

    public void on(RemoveAllCartItemEvent event);
}
