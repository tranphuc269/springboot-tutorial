package com.bookstore.orderservice.cart.command.api.events;


import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveCartItemEvent extends BaseEvent<String> {
    private String userId;
    private String cartItemId;
    public RemoveCartItemEvent(String id, String userId, String cartItemId) {
        super(id);
        this.userId = userId;
        this.cartItemId = cartItemId;
    }
}
