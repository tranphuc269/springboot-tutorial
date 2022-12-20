package com.bookstore.orderservice.cart.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartItemEvent extends BaseEvent<String> {

    private String userId;
    private String productId;

    public AddCartItemEvent(String id, String userId, String productId) {
        super(id);
        this.productId = productId;
        this.userId = userId;
    }
}
