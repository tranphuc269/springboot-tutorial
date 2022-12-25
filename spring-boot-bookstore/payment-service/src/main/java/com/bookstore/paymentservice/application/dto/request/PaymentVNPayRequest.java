package com.bookstore.paymentservice.application.dto.request;

import com.bookstore.paymentservice.utils.PaymentProviderType;

public class PaymentVNPayRequest extends PaymentRequest{
    @Override
    public PaymentProviderType getPaymentProviderType() {
        return PaymentProviderType.VNPAY;
    }
}
