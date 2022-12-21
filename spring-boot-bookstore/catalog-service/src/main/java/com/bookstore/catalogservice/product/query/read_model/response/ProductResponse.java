package com.bookstore.catalogservice.product.query.read_model.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;



@Data
@Builder
@Getter
@Setter
public class ProductResponse {
    private String productId;

    private String name;

    private String shortContent;
    private double price;

    private int availableItemCount;
    private String images;

    private int quantitySold;
}
