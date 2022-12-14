package com.bookstore.catalogservice.attribute.command.write_data.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateAttributeRequest {
    private String name;
}
