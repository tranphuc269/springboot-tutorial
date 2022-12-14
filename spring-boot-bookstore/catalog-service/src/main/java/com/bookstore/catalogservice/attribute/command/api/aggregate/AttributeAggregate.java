package com.bookstore.catalogservice.attribute.command.api.aggregate;


import com.bookstore.catalogservice.attribute.command.api.commands.CreateAttributeCommand;
import com.bookstore.catalogservice.attribute.command.api.events.attribute.CreateAttributeEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AttributeAggregate {


    @AggregateIdentifier
    private String id;
    private String name;

    public AttributeAggregate() {
    }

    @CommandHandler
    public AttributeAggregate(CreateAttributeCommand command){
        CreateAttributeEvent event = CreateAttributeEvent
                .builder()
                .id(command.getId())
                .name(command.getName())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateAttributeEvent event){
        this.id = event.getId();
        this.name = event.getName();
    }
}
