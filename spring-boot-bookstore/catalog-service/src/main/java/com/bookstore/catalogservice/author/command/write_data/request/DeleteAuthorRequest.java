package com.bookstore.catalogservice.author.command.write_data.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteAuthorRequest {
    private String authorId;
}
