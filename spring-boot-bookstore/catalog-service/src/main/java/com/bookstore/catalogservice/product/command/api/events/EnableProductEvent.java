package com.bookstore.catalogservice.product.command.api.events;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class EnableProductEvent {
    private String productId;
}
