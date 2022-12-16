package com.bookstore.catalogservice.product.command.api.events;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@Data
public class CreateProductEvent {
    private String name;

    private String shortContent;

    private String description;

    private BigDecimal price;

    private int availableItemCount;

    private String images;

    private int quantitySold;

    private String authorId;

    private String categoryId;
}
