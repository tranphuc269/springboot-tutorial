package com.bookstore.orderservice.order.command.api.events.handler;

import com.bookstore.orderservice.cart_item.command.api.commands.RemoveAllCartItemCommand;
import com.bookstore.orderservice.cart_item.query.api.queries.GetListCartItemQuery;
import com.bookstore.orderservice.cart_item.query.model.dto.CartItemResponse;
import com.bookstore.orderservice.order.command.api.events.*;
import com.bookstore.orderservice.order.command.write_model.OrderCommandRepository;
import com.bookstore.orderservice.order.command.write_model.entity.OrderCommandEntity;
import com.bookstore.orderservice.order_item.command.api.commands.CreateOrderItemCommand;
import com.bookstore.orderservice.order_item.command.model.entity.OrderItemCommandEntity;
import com.bookstore.orderservice.shipping_address.command.api.commands.CreateShippingAddressCommand;
import com.bookstore.orderservice.utils.OrderStatus;
import com.bookstore.orderservice.utils.PaymentStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class OrderEventHandlerImpl implements IOrderEventHandler {
    @Autowired
    private OrderCommandRepository repository;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;

    @Override
    @EventHandler
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
        OrderCommandEntity save = repository.save(entity);
        createOrderItem(event.getCartId(), save.getOrderId());
        createShippingAddress(save.getOrderId());
    }

    private void createOrderItem(String cartId, String orderId) {
        List<CartItemResponse> cartItemResponses = queryGateway.query(GetListCartItemQuery
                        .builder()
                        .cartId(cartId)
                        .build(), ResponseTypes.multipleInstancesOf(CartItemResponse.class))
                .join();
        commandGateway.send(new RemoveAllCartItemCommand(UUID.randomUUID().toString(), cartId));
        List<OrderItemCommandEntity> entities = cartItemResponses
                .stream().map(cart -> OrderItemCommandEntity
                        .builder()
                        .productId(cart.getProductId())
                        .orderId(orderId)
                        .productName(cart.getProductName())
                        .productPrice(cart.getProductPrice())
                        .productShortContent(cart.getProductShortContent())
                        .images(cart.getImages().toString())
                        .quantity(cart.getQuantity())
                        .build())
                .collect(Collectors.toList());
        commandGateway
                .send(new CreateOrderItemCommand(UUID.randomUUID().toString(), entities));

    }

    private void createShippingAddress(String orderId) {
        CreateShippingAddressCommand command = new CreateShippingAddressCommand(
                UUID.randomUUID().toString(),
                "city",
                "detail",
                "district",
                orderId,
                "0383916526",
                "province",
                "Tran Van Phuc",
                "userId"
        );
        commandGateway.send(command);
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
