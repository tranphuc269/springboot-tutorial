package com.bookstore.paymentservice.domain.service;

import com.bookstore.paymentservice.application.dto.request.PaymentRequest;
import com.bookstore.paymentservice.application.dto.response.PaymentResponse;

public interface PaymentService<T extends PaymentRequest, R extends PaymentResponse> {
    R pay(T request);
}