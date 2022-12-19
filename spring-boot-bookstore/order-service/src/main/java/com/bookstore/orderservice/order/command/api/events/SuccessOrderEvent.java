package com.bookstore.orderservice.order.command.api.events;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SuccessOrderEvent {
    private String orderCheckingId;
    private String orderId;
}
