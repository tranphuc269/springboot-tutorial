package com.bookstore.orderservice.cart.command.api.events.handler;

import com.bookstore.orderservice.cart.command.api.events.CreateCartEvent;
import com.bookstore.orderservice.cart.command.api.events.RemoveAllCartItemEvent;
import com.bookstore.orderservice.cart.command.model.CartCommandRepository;
import com.bookstore.orderservice.cart.command.model.entity.CartCommandEntity;
import com.bookstore.orderservice.cart_item.command.api.commands.RemoveAllCartItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public class CartEventHandler implements ICartEventHandler {
    @Autowired
    private CartCommandRepository cartRepository;

    @Autowired
    private CommandGateway commandGateway;


    @EventHandler
    @Override
    public void on(CreateCartEvent event) {
        CartCommandEntity entity = CartCommandEntity
                .builder()
                .userId(event.getUserId())
                .build();
        cartRepository.save(entity);
    }

    @EventHandler
    @Override
    public void on(RemoveAllCartItemEvent event) {
        commandGateway.send(new RemoveAllCartItemCommand(UUID.randomUUID().toString(),
                event.getCartId()));
    }
}
