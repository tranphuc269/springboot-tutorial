package com.bookstore.orderservice.cart.command.model.dto;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveAllCartRequest {
    private String cartId;
}
