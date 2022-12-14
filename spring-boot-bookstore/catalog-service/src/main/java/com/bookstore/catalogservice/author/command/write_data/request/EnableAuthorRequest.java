package com.bookstore.catalogservice.author.command.write_data.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class EnableAuthorRequest {
    private String authorId;
}
