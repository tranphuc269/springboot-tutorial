package com.bookstore.catalogservice.product.command.api.commands;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class DeleteProductCommand {
    private String productId;
}
