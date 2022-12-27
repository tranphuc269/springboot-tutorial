package com.bookstore.catalogservice.application.es.attribute.command.api.events.attributevalue;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttributeValueEvent {
    private String attributeValueId;
    private String attributeId;

    private String value;

    private String productId;
}
