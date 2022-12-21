package com.bookstore.orderservice.cart.query.model.dto;


import com.bookstore.orderservice.cart_item.query.model.dto.CartItemResponse;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Data
@Builder
@Getter
@Setter
public class CartResponse {
    private String cartId;
    private double totalPrice;

    List<CartItemResponse> items;
}
