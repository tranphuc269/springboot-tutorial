package com.learn.cqrs_pattern.command.api.aggregate;

import com.learn.cqrs_pattern.command.api.commands.CreateProductCommand;
import com.learn.cqrs_pattern.command.api.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {
    }
    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        // you can perform all the validations
        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent
                .builder()
                .productId(command.getProductId())
                .name(command.getProductId())
                .quantity(command.getQuantity())
                .price(command.getPrice())
                .build();
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        this.name = event.getName();
        this.productId = event.getProductId();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
    }

}
