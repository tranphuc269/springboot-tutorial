package com.bookstore.orderservice.cart.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class CreateCartCommand extends BaseCommand<String> {
    private String userId;

    public CreateCartCommand(String id, String userId) {
        super(id);
        this.userId = userId;
    }
}
