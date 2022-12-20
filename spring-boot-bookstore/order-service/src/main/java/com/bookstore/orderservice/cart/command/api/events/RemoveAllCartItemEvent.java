package com.bookstore.orderservice.cart.command.api.events;


import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveAllCartItemEvent extends BaseEvent<String> {
    private String userId;
    public RemoveAllCartItemEvent(String id, String userId) {
        super(id);
        this.userId = userId;
    }
}
