package com.bookstore.orderservice.application.es.order.command.write_model.dto;


import com.bookstore.orderservice.utils.PaymentMethod;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private String paymentMethod;

    private String cartId;
}