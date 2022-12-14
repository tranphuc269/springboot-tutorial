package com.bookstore.catalogservice.attribute.command.api.controller;


import com.bookstore.catalogservice.attribute.command.api.commands.CreateAttributeCommand;
import com.bookstore.catalogservice.attribute.data.dto.request.CreateAttributeRequest;
import com.bookstore.common.application.response.dto.BaseResponse;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@NoArgsConstructor
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private CommandGateway gateway;

    @PostMapping
    public BaseResponse<Void> createAttribute(@RequestBody CreateAttributeRequest request) {
        CreateAttributeCommand command = CreateAttributeCommand
                .builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

}
