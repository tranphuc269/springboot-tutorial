package com.bookstore.orderservice.order.command.api.commands;


import com.bookstore.orderservice.utils.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateOrderCommand {
    private String orderCheckingId;
    private PaymentMethod paymentMethod;

}
