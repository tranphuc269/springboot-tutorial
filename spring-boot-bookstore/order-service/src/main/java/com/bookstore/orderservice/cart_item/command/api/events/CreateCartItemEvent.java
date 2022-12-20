package com.bookstore.orderservice.cart_item.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateCartItemEvent extends BaseEvent<String> {
    private String cartId;
    private int quantity;

    private String productId;
    private String productName;
    private String images;
    private String productShortDescription;

    private BigDecimal productPrice;

    public CreateCartItemEvent(String id,
                               String cartId,
                               String productId,
                               String productName,
                               String productShortDescription,
                               String images,
                               BigDecimal productPrice) {
        super(id);
        this.quantity = 1;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productShortDescription = productShortDescription;
        this.images = images;
        this.productPrice = productPrice;
    }
}
