package com.bookstore.orderservice.order.command.api.commands;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReturnOrderCommand {
    private String orderCheckingId;
    private String orderId;
}
