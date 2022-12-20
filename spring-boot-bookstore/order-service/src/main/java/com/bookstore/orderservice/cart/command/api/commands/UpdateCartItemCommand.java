package com.bookstore.orderservice.cart.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateCartItemCommand extends BaseCommand<String> {
    private String userId;

    private String cartItemId;

    private int quantity;
    public UpdateCartItemCommand(String id, String userId, int quantity, String cartItemId) {
        super(id);
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.quantity = quantity;
    }
}
