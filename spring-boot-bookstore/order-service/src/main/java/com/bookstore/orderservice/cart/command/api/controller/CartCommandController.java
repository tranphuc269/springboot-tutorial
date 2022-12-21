package com.bookstore.orderservice.cart.command.api.controller;


import com.bookstore.common.application.response.dto.BaseResponse;
import com.bookstore.orderservice.cart.command.api.commands.CreateCartCommand;
import com.bookstore.orderservice.cart.command.api.commands.RemoveAllCartCommand;
import com.bookstore.orderservice.cart.command.model.dto.CreateCartRequest;
import com.bookstore.orderservice.cart.command.model.dto.RemoveAllCartRequest;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@NoArgsConstructor
@RequestMapping("/cart")
public class CartCommandController {

    @Autowired
    private CommandGateway gateway;


    @PostMapping
    public BaseResponse<Void> createCart(@RequestBody CreateCartRequest request) {
        CreateCartCommand command = new CreateCartCommand(UUID.randomUUID().toString(),
                request.getUserId());
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

    @DeleteMapping
    public BaseResponse<Void> removeAllCartItem(@RequestBody RemoveAllCartRequest request){
        RemoveAllCartCommand command = new RemoveAllCartCommand(UUID.randomUUID().toString(),
                request.getCartId());
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }
}
