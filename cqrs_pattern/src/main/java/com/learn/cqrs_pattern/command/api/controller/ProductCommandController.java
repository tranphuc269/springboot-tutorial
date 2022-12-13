package com.learn.cqrs_pattern.command.api.controller;


import com.learn.cqrs_pattern.command.api.commands.CreateProductCommand;
import com.learn.cqrs_pattern.command.api.model.ProductRestModel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@NoArgsConstructor
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;
//
//    public ProductCommandController(CommandGateway commandGateway) {
//        this.commandGateway = commandGateway;
//    }

    @PostMapping
    public String createProduct(@RequestBody ProductRestModel product) {
        CreateProductCommand command = CreateProductCommand
                .builder()
                .productId(UUID.randomUUID().toString())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        String result = commandGateway.sendAndWait(command);
        return result;
    }
}
