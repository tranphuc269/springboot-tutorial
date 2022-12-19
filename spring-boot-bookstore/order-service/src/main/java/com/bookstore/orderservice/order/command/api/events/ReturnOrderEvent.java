package com.bookstore.orderservice.order.command.api.events;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReturnOrderEvent {
    private String orderCheckingId;
    private String orderId;
}
