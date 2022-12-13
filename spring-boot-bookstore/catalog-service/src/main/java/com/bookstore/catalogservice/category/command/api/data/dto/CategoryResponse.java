package com.bookstore.catalogservice.category.command.api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String categoryId;
    private String categoryName;
    private String description;
    private String image;
}
