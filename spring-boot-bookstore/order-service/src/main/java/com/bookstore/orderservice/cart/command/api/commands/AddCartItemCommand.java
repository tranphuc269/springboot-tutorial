package com.bookstore.orderservice.cart.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddCartItemCommand extends BaseCommand<String> {
    private String userId;
    private String productId;

    private int quantity;

    public AddCartItemCommand(String id, String productId, String userId, int quantity) {
        super(id);
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }
}
