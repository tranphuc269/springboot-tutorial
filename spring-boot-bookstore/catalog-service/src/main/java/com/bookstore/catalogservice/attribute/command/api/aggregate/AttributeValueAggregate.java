package com.bookstore.catalogservice.attribute.command.api.aggregate;


import com.bookstore.catalogservice.attribute.command.api.commands.CreateAttributeValueCommand;
import com.bookstore.catalogservice.attribute.command.api.events.attributevalue.CreateAttributeValueEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AttributeValueAggregate {

    @AggregateIdentifier
    private String id;
    private String attributeId;

    private String value;

    private String productId;

    protected AttributeValueAggregate() {

    }

    @CommandHandler
    public AttributeValueAggregate(CreateAttributeValueCommand command) {
        CreateAttributeValueEvent event = CreateAttributeValueEvent
                .builder()
                .attributeId(command.getAttributeId())
                .id(command.getId())
                .value(command.getValue())
                .productId(command.getProductId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateAttributeValueEvent event) {
        this.attributeId = event.getAttributeId();
        this.id = event.getId();
        this.value = event.getValue();
        this.productId = event.getProductId();

    }
}
