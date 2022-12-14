package com.bookstore.catalogservice.author.command.api.commands;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnableAuthorCommand {
    private String authorId;
}
