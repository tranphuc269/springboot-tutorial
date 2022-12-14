package com.bookstore.catalogservice.author.command.api.controller;

import com.bookstore.catalogservice.author.command.api.commands.CreateAuthorCommand;
import com.bookstore.catalogservice.author.command.api.commands.DeleteAuthorCommand;
import com.bookstore.catalogservice.author.command.api.commands.UpdateAuthorCommand;
import com.bookstore.catalogservice.author.command.write_data.request.CreateAuthorRequest;
import com.bookstore.catalogservice.author.command.write_data.request.DeleteAuthorRequest;
import com.bookstore.catalogservice.author.command.write_data.request.UpdateAuthorRequest;
import com.bookstore.common.application.response.dto.BaseResponse;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@NoArgsConstructor
@RequestMapping("/author")
public class AuthorCommandController {

    @Autowired
    private CommandGateway gateway;


    @PostMapping
    public BaseResponse<Void> createAuthor(@RequestBody CreateAuthorRequest request){
        CreateAuthorCommand command = CreateAuthorCommand
                .builder()
                .authorName(request.getAuthorName())
                .description(request.getDescription())
                .identify(UUID.randomUUID().toString())
                .images(request.getImages())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

    @PutMapping
    public BaseResponse<Void> update(@RequestBody UpdateAuthorRequest request){
        UpdateAuthorCommand command = UpdateAuthorCommand
                .builder()
                .authorName(request.getAuthorName())
                .description(request.getDescription())
                .authorId(request.getAuthorId())
                .images(request.getImages())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }


    @DeleteMapping
    public BaseResponse<Void> deleteAuthor(@RequestBody DeleteAuthorRequest request){
        DeleteAuthorCommand command = DeleteAuthorCommand
                .builder()
                .authorId(request.getAuthorId())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }
}
