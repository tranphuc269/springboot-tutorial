package com.bookstore.orderservice.cart.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveCartItemCommand extends BaseCommand<String> {
    private String userId;
    private String cartItemId;
    public RemoveCartItemCommand(String id, String userId, String cartItemId) {
        super(id);
        this.userId = userId;
        this.cartItemId = cartItemId;

    }
}
