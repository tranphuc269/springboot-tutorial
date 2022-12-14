package com.bookstore.catalogservice.attribute.command.api.commands;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@Builder
@Setter
@Getter
public class CreateAttributeCommand {
    @TargetAggregateIdentifier
    private String attributeId;
    private String name;
}
