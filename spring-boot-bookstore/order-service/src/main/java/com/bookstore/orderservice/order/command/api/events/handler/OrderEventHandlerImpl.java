package com.bookstore.orderservice.order.command.api.events.handler;

import com.bookstore.orderservice.order.command.api.events.*;
import com.bookstore.orderservice.order.command.write_model.OrderCommandRepository;
import com.bookstore.orderservice.order.command.write_model.entity.OrderCommandEntity;
import com.bookstore.orderservice.utils.OrderStatus;
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
        OrderCommandEntity entity = OrderCommandEntity
                .builder()
                .orderCheckingId(event.getOrderCheckingId())
                .orderStatus(OrderStatus.CREATED.name())
                .paymentMethod(event.getPaymentMethod())
                .build();
        entity.setStatus(1);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    @EventHandler
    @Override
    public void on(ConfirmOrderEvent event) {
        System.out.println(
                "event" + event.toString()
        );
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
//        if (entityOptional.isEmpty()) {
//            throw new RuntimeException();
//        }
        entityOptional.get().setOrderStatus(OrderStatus.CONFIRMED.name());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(CancelOrderEvent event) {
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.CANCEL.name());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(ShipOrderEvent event) {
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.SHIPPED.name());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(ReturnOrderEvent event) {
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.RETURNED.name());
        repository.save(entityOptional.get());
    }

    @EventHandler
    @Override
    public void on(SuccessOrderEvent event) {
        Optional<OrderCommandEntity> entityOptional = repository.findById(event.getOrderId());
        if (entityOptional.isEmpty()) {
            throw new RuntimeException();
        }
        entityOptional.get().setOrderStatus(OrderStatus.SUCCESSED.name());
        repository.save(entityOptional.get());
    }
}
