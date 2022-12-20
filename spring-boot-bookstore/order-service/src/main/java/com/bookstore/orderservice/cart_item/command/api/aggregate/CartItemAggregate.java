package com.bookstore.orderservice.cart_item.command.api.aggregate;


import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class CartItemAggregate {
    @AggregateIdentifier
    private String id;

    private String productId;

    private String productName;

    private String productShortContent;

    private String images;

    private String cartId;

    private int quantity;

    private BigDecimal productPrice;
}
