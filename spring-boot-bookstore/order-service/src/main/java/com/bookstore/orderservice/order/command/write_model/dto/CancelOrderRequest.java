package com.bookstore.orderservice.order.command.write_model.dto;


import lombok.*;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderRequest {
    private String orderId;
    private String orderCheckingId;
}
