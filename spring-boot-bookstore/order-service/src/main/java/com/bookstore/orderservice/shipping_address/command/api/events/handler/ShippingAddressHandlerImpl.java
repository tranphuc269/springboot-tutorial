package com.bookstore.orderservice.shipping_address.command.api.events.handler;

import com.bookstore.orderservice.shipping_address.command.api.events.CreateShippingAddressEvent;
import com.bookstore.orderservice.shipping_address.command.model.ShippingAddressCommandRepository;
import com.bookstore.orderservice.shipping_address.command.model.entity.ShippingAddressCommandEntity;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShippingAddressHandlerImpl implements IShippingAddressHandler{

    @Autowired
    private ShippingAddressCommandRepository repository;

    @EventHandler
    @Override
    public void on(CreateShippingAddressEvent event) {
        ShippingAddressCommandEntity entity = ShippingAddressCommandEntity
                .builder()
                .city(event.getCity())
                .detail(event.getDetail())
                .district(event.getDistrict())
                .orderId(event.getOrderId())
                .phoneNumber(event.getPhoneNumber())
                .province(event.getProvince())
                .userId(event.getUserId())
                .userName(event.getUserName())
                .build();
        repository.save(entity);
    }
}
