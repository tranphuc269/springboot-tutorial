package com.bookstore.orderservice.application.es.order.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import com.bookstore.orderservice.utils.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfirmOrderEvent extends BaseEvent<String> {
    private String orderId;
    private OrderStatus status;

    public ConfirmOrderEvent(String orderCheckingId, String orderId) {
        super(orderCheckingId);
        this.status = OrderStatus.CONFIRMED;
        this.orderId = orderId;
    }
}
