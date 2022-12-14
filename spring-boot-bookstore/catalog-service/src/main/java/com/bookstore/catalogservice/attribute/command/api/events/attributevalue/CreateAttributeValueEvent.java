package com.bookstore.catalogservice.attribute.command.api.events.attributevalue;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttributeValueEvent {
    private String id;
    private String attributeId;

    private String value;

    private String productId;
}
