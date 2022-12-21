package com.bookstore.orderservice.cart_item.command.model.dto;


import lombok.*;



@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductToCartRequest {
    private String cartId;
    private int quantity;
    private String productId;
    private String productName;
    private String images;
    private String productShortDescription;
    private double productPrice;
}
