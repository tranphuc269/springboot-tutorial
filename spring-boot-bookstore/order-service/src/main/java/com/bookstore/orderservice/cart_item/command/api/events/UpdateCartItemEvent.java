package com.bookstore.orderservice.cart_item.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateCartItemEvent extends BaseEvent<String> {
    private String cartItemId;
    private int quantity;
    public UpdateCartItemEvent(String id,
                               String cartItemId,
                               int quantity) {
        super(id);
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }
}
