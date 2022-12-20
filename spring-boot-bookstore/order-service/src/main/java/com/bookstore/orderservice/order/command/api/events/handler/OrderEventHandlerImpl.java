package com.bookstore.orderservice.order.command.api.events.handler;

import com.bookstore.orderservice.order.command.api.events.*;
import com.bookstore.orderservice.order.command.write_model.OrderCommandRepository;
import com.bookstore.orderservice.order.command.write_model.entity.OrderCommandEntity;
import com.bookstore.orderservice.utils.OrderStatus;
import com.bookstore.orderservice.utils.PaymentStatus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Component
public class OrderEventHandlerImpl implements IOrderEventHandler {
    @Autowired
    private OrderCommandRepository repository;

    @EventHandler
    @Override
    public void on(CreateOrderEvent event) {
        System.out.println("EventHandler : CreateOrderEvent");
        OrderCommandEntity entity = OrderCommandEntity
                .builder()
                .orderCheckingId(event.getId())
                .orderStatus(OrderStatus.CREATED.name())
                .paymentMethod(event.getPaymentMethod().name())
                .paymentStatus(PaymentStatus.PROCESSING.name())
                .build();
        entity.setStatus(1);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    @EventHandler
    @Override
    public void on(ConfirmOrderEvent event) {
        System.out.println("EventHandler : ConfirmOrderEvent : " + event.getOrderId());
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.CONFIRMED.name());
        entityOptional.get().setUpdateTime(new Date());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(CancelOrderEvent event) {
        System.out.println("EventHandler : CancelOrderEvent");
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.CANCEL.name());
        entityOptional.get().setUpdateTime(new Date());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(ShipOrderEvent event) {
        System.out.println("EventHandler : ShipOrderEvent");
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.SHIPPED.name());
        entityOptional.get().setUpdateTime(new Date());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(ReturnOrderEvent event) {
        System.out.println("EventHandler : ReturnOrderEvent");
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.RETURNED.name());
        entityOptional.get().setUpdateTime(new Date());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(SuccessOrderEvent event) {
        System.out.println("EventHandler : SuccessOrderEvent");
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.SUCCESSED.name());
        entityOptional.get().setUpdateTime(new Date());
        repository.save(entityOptional.get());
    }
}
