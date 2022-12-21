package com.bookstore.orderservice.cart.command.model.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCartRequest {
    private String userId;
}
