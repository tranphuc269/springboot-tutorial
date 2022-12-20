package com.bookstore.orderservice.cart.command.api.aggregate;


import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class CartCommandAggregate {

    @AggregateIdentifier
    private String id;

    private String cartId;

    private String userId;

    private BigDecimal totalPrice;
}
