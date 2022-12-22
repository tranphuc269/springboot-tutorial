package com.bookstore.orderservice.order.command.api.events.handler;

import com.bookstore.orderservice.order.command.api.events.*;
import org.axonframework.eventhandling.EventHandler;

public interface IOrderEventHandler {

    public void on(CreateOrderEvent event);
    public void on(ConfirmOrderEvent event);
    public void on(CancelOrderEvent event);
    public void on(ShipOrderEvent event);
    public void on(ReturnOrderEvent event);
    public void on(SuccessOrderEvent event);
}
