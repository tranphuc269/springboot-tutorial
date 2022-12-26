package com.bookstore.orderservice.application.es.order.command.api.commands;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
@Setter
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private String orderCheckingId;
    private String orderId;
}
