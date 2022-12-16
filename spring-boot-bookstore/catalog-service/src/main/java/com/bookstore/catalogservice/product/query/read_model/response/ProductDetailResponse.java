package com.bookstore.catalogservice.product.query.read_model.response;


import com.bookstore.catalogservice.author.data.dto.AuthorResponse;
import com.bookstore.catalogservice.category.data.CategoryRepository;
import com.bookstore.catalogservice.category.data.dto.CategoryResponse;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    private String productId;
    private String name;

    private BigDecimal price;
    private String shortContent;
    private String description;
    private int availableItemCount;
    private List<String> images;
    private int quantitySold;

    private List<AttributeProductResponse> attributes;

    CategoryResponse category;
    AuthorResponse author;
}
