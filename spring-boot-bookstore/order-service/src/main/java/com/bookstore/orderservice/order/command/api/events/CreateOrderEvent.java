package com.bookstore.orderservice.order.command.api.events;


import com.bookstore.common.infrastructure.axon.BaseEvent;
import com.bookstore.orderservice.utils.OrderStatus;
import com.bookstore.orderservice.utils.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderEvent extends BaseEvent<String> {
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;

    public CreateOrderEvent(String orderCheckingId, PaymentMethod paymentMethod) {
        super(orderCheckingId);
        this.orderStatus = OrderStatus.CREATED;
        this.paymentMethod = paymentMethod;
    }
}