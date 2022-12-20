package com.bookstore.orderservice.cart.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CreateCartCommand extends BaseCommand<String> {
    private String userId;
    private BigDecimal totalPrice;


    public CreateCartCommand(String id, String userId, BigDecimal totalPrice) {
        super(id);
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
}
