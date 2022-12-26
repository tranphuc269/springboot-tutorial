package com.bookstore.orderservice.domain.service.notification;

import com.bookstore.common.infrastructure.kafka.notification.KafkaNotificationDeviceRequest;
import com.bookstore.common.infrastructure.kafka.notification.KafkaNotificationEmailRequest;
import com.bookstore.common.infrastructure.kafka.notification.KafkaNotificationRequest;

public interface NotificationService {
    public void send(KafkaNotificationRequest request);
}
