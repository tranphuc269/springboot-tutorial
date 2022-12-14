package com.bookstore.catalogservice.attribute.command.write_data.request;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class CreateAttributeValueRequest {
    private String attributeValueId;
    private String attributeId;

    private String value;

    private String productId;
}
