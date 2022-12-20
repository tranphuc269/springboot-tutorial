package com.bookstore.orderservice.cart.command.api.events.handler;


import com.bookstore.orderservice.cart.command.api.events.*;

public interface ICartEventHandler {

    public void on(CreateCartEvent event);

    public void on(RemoveAllCartItemEvent event);

}
