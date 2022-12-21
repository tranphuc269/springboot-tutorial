package com.bookstore.catalogservice.product.query.read_model.query_request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductAddToCartRequest {
    private String productId;
}
