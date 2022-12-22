package com.bookstore.orderservice.order.command.api.controller;


import com.bookstore.common.application.response.dto.BaseResponse;
import com.bookstore.orderservice.order.command.api.commands.*;
import com.bookstore.orderservice.order.command.write_model.dto.*;
import com.bookstore.orderservice.utils.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@NoArgsConstructor
@RequestMapping("/order")
public class OrderCommandController {


    @Autowired
    private CommandGateway gateway;

    @PostMapping
    public BaseResponse<String> createOrder(@RequestBody CreateOrderRequest request) {
        PaymentMethod paymentMethod;
        if (request.getPaymentMethod().equals(PaymentMethod.COD.name())) {
            paymentMethod = PaymentMethod.COD;
        } else {
            paymentMethod = PaymentMethod.VNPAY;
        }
        CreateOrderCommand command = CreateOrderCommand
                .builder()
                .orderCheckingId("order" + (new Date()).getTime())
                .cartId(request.getCartId())
                .paymentMethod(paymentMethod)
                .build();
        return BaseResponse.ofSucceeded(gateway.sendAndWait(command).toString());
    }

    @PostMapping("/confirm")
    public BaseResponse<Void> confirmOrder(@RequestBody ConfirmOrderRequest request) {
        ConfirmOrderCommand command = ConfirmOrderCommand
                .builder()
                .orderCheckingId(request.getOrderCheckingId())
                .orderId(request.getOrderId())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

    @PostMapping("/cancel")
    public BaseResponse<Void> cancelOrder(@RequestBody CancelOrderRequest request) {
        CancelOrderCommand command = CancelOrderCommand
                .builder()
                .orderCheckingId(request.getOrderCheckingId())
                .orderId(request.getOrderId())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

    @PostMapping("/ship")
    public BaseResponse<Void> shipOrder(@RequestBody ShipOrderRequest request) {
        ShipOrderCommand command = ShipOrderCommand
                .builder()
                .orderCheckingId(request.getOrderCheckingId())
                .orderId(request.getOrderId())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

    @PostMapping("/return")
    public BaseResponse<Void> returnOrder(@RequestBody ReturnOrderRequest request) {
        ReturnOrderCommand command = ReturnOrderCommand
                .builder()
                .orderCheckingId(request.getOrderCheckingId())
                .orderId(request.getOrderId())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

    @PostMapping("/success")
    public BaseResponse<Void> successOrder(@RequestBody SuccessOrderRequest request) {
        SuccessOrderCommand command = SuccessOrderCommand
                .builder()
                .orderCheckingId(request.getOrderCheckingId())
                .orderId(request.getOrderId())
                .build();
        gateway.send(command);
        return BaseResponse.ofSucceeded();
    }

}
