package com.bookstore.orderservice.order.query.api.controller;

import com.bookstore.common.application.response.dto.BaseResponse;
import com.bookstore.orderservice.order.query.api.queries.OrderHistoryRequestCheckingQuery;
import lombok.NoArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/order")
public class OrderQueryController {


    @Autowired
    private QueryGateway gateway;

    @GetMapping("/{orderCheckingId}")
    public Object checkingOrderHistoryStatus(@PathVariable String orderCheckingId) {
        OrderHistoryRequestCheckingQuery query = OrderHistoryRequestCheckingQuery
                .builder()
                .orderCheckingId(orderCheckingId)
                .build();
        return gateway.query(query,
                        ResponseTypes.multipleInstancesOf(Object.class))
                .join();
    }

}
