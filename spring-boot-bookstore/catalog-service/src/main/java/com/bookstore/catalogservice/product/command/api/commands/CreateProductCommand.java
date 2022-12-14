package com.bookstore.catalogservice.product.command.api.commands;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
public class CreateProductCommand {
    private String name;

    private String shortContent;

    private String description;

    private BigDecimal price;

    private int availableItemCount;

    private String images;
}
