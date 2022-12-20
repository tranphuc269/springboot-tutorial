package com.bookstore.orderservice.order.command.api.commands;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
@Setter
public class SuccessOrderCommand {

    @TargetAggregateIdentifier
    private String orderCheckingId;
    private String orderId;
}
