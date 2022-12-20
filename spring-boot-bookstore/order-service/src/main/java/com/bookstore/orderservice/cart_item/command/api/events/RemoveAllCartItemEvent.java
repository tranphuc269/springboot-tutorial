package com.bookstore.orderservice.cart_item.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveAllCartItemEvent extends BaseEvent<String> {
    private String cartId;

    public RemoveAllCartItemEvent(String id, String cartId) {
        super(id);
        this.cartId = cartId;
    }
}
