package com.bookstore.orderservice.cart.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateCartItemEvent extends BaseEvent<String> {

    private String userId;

    private String cartItemId;
    private int quantity;

    public UpdateCartItemEvent(String cartId, String userId, String cartItemId, int quantity) {
        super(cartId);
        this.userId = userId;
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }
}
