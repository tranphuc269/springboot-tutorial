package com.bookstore.paymentservice.domain.service.impl;

import com.bookstore.paymentservice.application.dto.request.PaymentCODRequest;
import com.bookstore.paymentservice.application.dto.response.PaymentCODResponse;
import com.bookstore.paymentservice.domain.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentCODService implements PaymentService<PaymentCODRequest,
        PaymentCODResponse> {

    @Override
    public PaymentCODResponse pay(PaymentCODRequest request) {
        // To do payment logic with provider 1 ...
        return new PaymentCODResponse();
    }

}