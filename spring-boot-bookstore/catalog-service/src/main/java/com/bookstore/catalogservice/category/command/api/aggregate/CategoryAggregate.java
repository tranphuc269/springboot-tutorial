package com.bookstore.catalogservice.category.command.api.aggregate;


import com.bookstore.catalogservice.category.command.api.commands.CreateCategoryCommand;
import com.bookstore.catalogservice.category.command.api.events.CategoryCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String categoryId;
    private String categoryName;
    private String description;
    private String image;

    public CategoryAggregate() {
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        this.categoryId = command.getCategoryId();
        this.categoryName = command.getCategoryName();
        this.description = command.getDescription();
        this.image = command.getImage();
    }

    @EventSourcingHandler
    public void on(CategoryCreateEvent event){
        this.categoryId = event.getCategoryId();
        this.categoryName = event.getCategoryName();
        this.description = event.getDescription();
        this.image = event.getImage();
    }
}
