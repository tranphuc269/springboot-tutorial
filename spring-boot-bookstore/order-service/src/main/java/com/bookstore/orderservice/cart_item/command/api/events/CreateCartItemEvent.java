package com.bookstore.orderservice.cart_item.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartItemEvent extends BaseEvent<String> {
    private String cartId;
    private int quantity;

    private String productId;
    private String productName;
    private String image;
    private String productShortDescription;

    public CreateCartItemEvent(String id) {
        super(id);
        this.quantity = 1;
    }
}
