package com.bookstore.catalogservice.product.command.api.events;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
@Builder
public class EnableProductEvent {
    private String productId;
}
