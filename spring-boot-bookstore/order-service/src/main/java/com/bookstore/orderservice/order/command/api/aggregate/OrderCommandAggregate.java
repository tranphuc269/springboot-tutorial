package com.bookstore.orderservice.order.command.api.aggregate;


import com.bookstore.orderservice.order.command.api.commands.*;
import com.bookstore.orderservice.order.command.api.events.*;
import com.bookstore.orderservice.utils.OrderStatus;
import com.bookstore.orderservice.utils.PaymentMethod;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderCommandAggregate {

    @AggregateIdentifier
    private String orderCheckingId;

    private String orderId;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    protected OrderCommandAggregate(){}


    // create order
    @CommandHandler
    public OrderCommandAggregate(CreateOrderCommand command){
        CreateOrderEvent event = CreateOrderEvent
                .builder()
                .orderCheckingId(command.getOrderCheckingId())
                .paymentMethod(command.getPaymentMethod())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateOrderEvent event){
        this.orderCheckingId = event.getOrderCheckingId();
        this.orderStatus = OrderStatus.CREATED;
        this.paymentMethod = event.getPaymentMethod();
    }

    // confirm
    @CommandHandler
    public OrderCommandAggregate(ConfirmOrderCommand command){
        ConfirmOrderEvent event = ConfirmOrderEvent
                .builder()
                .orderCheckingId(command.getOrderCheckingId())
                .orderId(command.getOrderId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ConfirmOrderEvent event){
//        this.orderCheckingId = event.getOrderCheckingId();
        this.orderId = event.getOrderId();
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    // cancel order
    @CommandHandler
    public OrderCommandAggregate(CancelOrderCommand command){
        CancelOrderEvent event = CancelOrderEvent
                .builder()
                .orderCheckingId(command.getOrderCheckingId())
                .orderId(command.getOrderId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CancelOrderEvent event){
//        this.orderCheckingId = event.getOrderCheckingId();
        this.orderId = event.getOrderId();
        this.orderStatus = OrderStatus.CANCEL;
    }

    // ship order
    @CommandHandler
    public OrderCommandAggregate(ShipOrderCommand command){
        ShipOrderEvent event = ShipOrderEvent
                .builder()
                .orderCheckingId(command.getOrderCheckingId())
                .orderId(command.getOrderId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ShipOrderEvent event){
//        this.orderCheckingId = event.getOrderCheckingId();
        this.orderId = event.getOrderId();
        this.orderStatus = OrderStatus.SHIPPED;
    }
    // success order
    @CommandHandler
    public OrderCommandAggregate(SuccessOrderCommand command){
        SuccessOrderEvent event = SuccessOrderEvent
                .builder()
                .orderCheckingId(command.getOrderCheckingId())
                .orderId(command.getOrderId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(SuccessOrderEvent event){
//        this.orderCheckingId = event.getOrderCheckingId();
        this.orderId = event.getOrderId();
        this.orderStatus = OrderStatus.SUCCESSED;
    }

    // return order
    @CommandHandler
    public OrderCommandAggregate(ReturnOrderCommand command){
        ReturnOrderEvent event = ReturnOrderEvent
                .builder()
                .orderCheckingId(command.getOrderCheckingId())
                .orderId(command.getOrderId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ReturnOrderEvent event){
//        this.orderCheckingId = event.getOrderCheckingId();
        this.orderId = event.getOrderId();
        this.orderStatus = OrderStatus.SUCCESSED;
    }
}
