package com.bookstore.catalogservice.author.command.api.events;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorEvent {
    private String identify;
    private String authorName;
    private String description;
    private String images;
}
