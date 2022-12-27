package com.bookstore.catalogservice.application.es.product.command.api.commands;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SellProductCommand {
    private String productId;
    private int quantity;
}
