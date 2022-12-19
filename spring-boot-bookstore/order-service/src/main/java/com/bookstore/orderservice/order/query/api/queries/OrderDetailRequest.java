package com.bookstore.orderservice.order.query.api.queries;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class OrderDetailRequest {

    private String orderCheckingId;

}
