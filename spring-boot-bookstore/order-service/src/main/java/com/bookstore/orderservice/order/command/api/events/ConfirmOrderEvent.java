package com.bookstore.orderservice.order.command.api.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ConfirmOrderEvent {
    private String orderCheckingId;
    private String orderId;
}
