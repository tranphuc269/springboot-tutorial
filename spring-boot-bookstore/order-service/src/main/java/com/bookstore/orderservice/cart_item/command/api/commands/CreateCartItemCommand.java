package com.bookstore.orderservice.cart_item.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateCartItemCommand extends BaseCommand<String> {
    private String cartId;
    private int quantity;
    private String productId;
    private String productName;
    private String images;
    private String productShortDescription;

    private BigDecimal productPrice;

    public CreateCartItemCommand(String id,
                                 String cartId,
                                 int quantity,
                                 String productName,
                                 String productShortDescription,
                                 String images,
                                 String productId,
                                 BigDecimal productPrice
    ) {
        super(id);
        this.cartId = cartId;
        this.quantity = quantity;
        this.productName = productName;
        this.productShortDescription = productShortDescription;
        this.images = images;
        this.productPrice = productPrice;
        this.productId = productId;
    }
}
