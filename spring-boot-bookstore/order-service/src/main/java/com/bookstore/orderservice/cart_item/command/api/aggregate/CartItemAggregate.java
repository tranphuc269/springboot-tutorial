package com.bookstore.orderservice.cart_item.command.api.aggregate;


import com.bookstore.orderservice.cart_item.command.api.commands.AddCartItemCommand;
import com.bookstore.orderservice.cart_item.command.api.commands.RemoveAllCartItemCommand;
import com.bookstore.orderservice.cart_item.command.api.commands.RemoveCartItemCommand;
import com.bookstore.orderservice.cart_item.command.api.events.AddCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveAllCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveCartItemEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


import java.util.UUID;

@Aggregate
public class CartItemAggregate {
    @AggregateIdentifier
    private String id;
    private String cartItemId;
    private String cartId;
    private String productId;
    private String productName;
    private String productShortContent;
    private String images;
    private int quantity;
    private double productPrice;

    public CartItemAggregate() {
    }

    // add cart item
    @CommandHandler
    public CartItemAggregate(AddCartItemCommand command) {
        AddCartItemEvent event = new AddCartItemEvent(
                UUID.randomUUID().toString(),
                command.getCartId(),
                command.getProductId(),
                command.getProductName(),
                command.getProductShortDescription(),
                command.getImages(),
                command.getProductPrice()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AddCartItemEvent event) {
        this.id = event.getId();
        this.cartId = event.getCartId();
        this.productId = event.getProductId();
        this.images = event.getImages();
        this.quantity = event.getQuantity();
        this.productPrice = event.getProductPrice();
        this.productShortContent = event.getProductShortDescription();
    }

    // remove cart item
    @CommandHandler
    public CartItemAggregate(RemoveCartItemCommand command) {
        RemoveCartItemEvent event = new RemoveCartItemEvent(UUID.randomUUID().toString(),
                command.getCartItemId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(RemoveCartItemEvent event) {
        this.id = event.getId();
        this.cartItemId = event.getCartItemId();
    }

    // delete all cart
    @CommandHandler
    public CartItemAggregate(RemoveAllCartItemCommand command) {
        RemoveAllCartItemEvent event = new RemoveAllCartItemEvent(UUID.randomUUID().toString(),
                command.getCartId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(RemoveAllCartItemEvent event) {
        this.id = event.getId();
        this.cartId = event.getCartId();
    }

}
