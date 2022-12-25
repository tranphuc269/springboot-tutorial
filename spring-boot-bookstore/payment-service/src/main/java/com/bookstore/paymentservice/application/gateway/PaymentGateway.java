package com.bookstore.paymentservice.application.gateway;

import com.bookstore.paymentservice.application.dto.request.PaymentRequest;
import com.bookstore.paymentservice.application.dto.response.PaymentResponse;

public interface PaymentGateway<T extends PaymentResponse, K extends PaymentRequest> {
    T makePayment(K paymentRequest);
}