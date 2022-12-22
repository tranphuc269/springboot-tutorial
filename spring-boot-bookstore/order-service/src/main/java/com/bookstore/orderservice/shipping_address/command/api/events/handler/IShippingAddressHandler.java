package com.bookstore.orderservice.shipping_address.command.api.events.handler;

import com.bookstore.orderservice.shipping_address.command.api.events.CreateShippingAddressEvent;

public interface IShippingAddressHandler {

    public void on(CreateShippingAddressEvent event);

}
