package com.bookstore.catalogservice.application.es.author.command.api.events;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorEvent {
    private String identity;
    private String authorName;
    private String description;
    private String images;
}
