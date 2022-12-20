package com.bookstore.orderservice.cart.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RemoveAllCartCommand extends BaseCommand<String> {
    private String userId;
    public RemoveAllCartCommand(String id, String userId) {
        super(id);
        this.userId = userId;
    }
}
