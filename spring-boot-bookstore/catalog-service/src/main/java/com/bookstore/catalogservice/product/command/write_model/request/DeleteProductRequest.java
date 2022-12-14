package com.bookstore.catalogservice.product.command.write_model.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class DeleteProductRequest {
    private String productId;
}
