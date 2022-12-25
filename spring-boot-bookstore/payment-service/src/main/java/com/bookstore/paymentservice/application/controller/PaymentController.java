package com.bookstore.paymentservice.application.controller;

import com.bookstore.paymentservice.application.dto.request.PaymentRequest;
import com.bookstore.paymentservice.application.dto.request.PaymentCODRequest;
import com.bookstore.paymentservice.application.dto.response.PaymentResponse;
import com.bookstore.paymentservice.application.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentGateway<PaymentResponse, PaymentRequest> paymentGateway;

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestBody PaymentCODRequest paymentRequest) {
        return ResponseEntity.ok(paymentGateway.makePayment(paymentRequest));
    }

}