package com.bookstore.orderservice.order.command.write_model.dto;


import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessOrderRequest {
    private String orderId;
    private String orderCheckingId;
}
