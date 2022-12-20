package com.bookstore.orderservice.cart_item.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class UpdateCartItemCommand extends BaseCommand<String> {
    private String cartItemId;
    private int quantity;

    public UpdateCartItemCommand(String id,
                                 String cartItemId,
                                 int quantity) {
        super(id);
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }
}
