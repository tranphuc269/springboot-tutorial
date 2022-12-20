package com.bookstore.orderservice.cart_item.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RemoveCartItemCommand extends BaseCommand<String> {

    private String cartItemId;
    public RemoveCartItemCommand(String id,
                                 String cartItemId) {
        super(id);
        this.cartItemId = cartItemId;
    }
}
