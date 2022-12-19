package com.bookstore.orderservice.order.command.api.commands;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ShipOrderCommand {
    private String orderCheckingId;
    private String orderId;
}
