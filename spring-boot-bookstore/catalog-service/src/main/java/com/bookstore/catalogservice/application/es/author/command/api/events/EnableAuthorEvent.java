package com.bookstore.catalogservice.application.es.author.command.api.events;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class EnableAuthorEvent {
    private String authorId;
}
