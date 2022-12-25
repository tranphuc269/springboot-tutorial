package com.bookstore.paymentservice.domain.provider.impl;

import com.bookstore.paymentservice.application.dto.request.PaymentRequest;
import com.bookstore.paymentservice.application.dto.response.PaymentResponse;
import com.bookstore.paymentservice.domain.provider.PaymentProvider;
import com.bookstore.paymentservice.domain.service.PaymentService;
import com.bookstore.paymentservice.domain.service.impl.PaymentCODService;
import com.bookstore.paymentservice.utils.PaymentProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultPaymentProviderImpl implements PaymentProvider<PaymentService<PaymentRequest, PaymentResponse>> {
    private final ApplicationContext applicationContext;

    @Override
    @SuppressWarnings("rawtypes")
    public PaymentService getProvider(PaymentProviderType type) {
        switch (type) {
            case COD:
                return applicationContext.getBean(PaymentCODService.class);
//            case PROVIDER_TWO:
//                return applicationContext.getBean(ProviderTwoPaymentServiceImpl.class);
//            case PROVIDER_THREE:
//                return applicationContext.getBean(ProviderThreePaymentServiceImpl.class);
            default:
                throw new UnsupportedOperationException("Notification type: " + type + " not supported");
        }
    }

}