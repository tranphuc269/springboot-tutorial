package com.bookstore.orderservice.cart_item.query.api.queries;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@Data
public class GetListCartItemQuery {
    private String cartId;
}
