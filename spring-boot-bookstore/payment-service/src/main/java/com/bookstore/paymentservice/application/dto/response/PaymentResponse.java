package com.bookstore.paymentservice.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PaymentResponse {
    @Builder.Default
    private String someText = "someText";
}