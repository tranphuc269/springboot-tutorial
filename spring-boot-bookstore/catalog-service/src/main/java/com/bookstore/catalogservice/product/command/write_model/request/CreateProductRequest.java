package com.bookstore.catalogservice.product.command.write_model.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
@Builder
public class CreateProductRequest {
    private String name;

    private String shortContent;

    private String description;

    private BigDecimal price;

    private int availableItemCount;

    private String images;

    private String authorId;

    private String categoryId;
}
