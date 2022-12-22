package com.bookstore.orderservice.shipping_address.query.api.queries;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class GetAddressByOrderIdQuery {
    private String orderId;
}
