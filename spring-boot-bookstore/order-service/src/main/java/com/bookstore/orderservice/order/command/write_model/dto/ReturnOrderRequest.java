package com.bookstore.orderservice.order.command.write_model.dto;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnOrderRequest {
    private String orderId;
    private String orderCheckingId;
}
