package com.bookstore.orderservice.order_item.query.api.queries;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class GetOrderItemsByOrderIdQuery {
    private String orderId;
}
