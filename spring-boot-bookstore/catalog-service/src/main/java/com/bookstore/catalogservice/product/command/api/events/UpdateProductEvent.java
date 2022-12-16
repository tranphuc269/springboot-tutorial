package com.bookstore.catalogservice.product.command.api.events;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
@Builder
public class UpdateProductEvent {
    private String productId;
    private String name;

    private String shortContent;

    private String description;

    private BigDecimal price;

    private int availableItemCount;

    private String images;

    private String authorId;

    private String categoryId;
}
